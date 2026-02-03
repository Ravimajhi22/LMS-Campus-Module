package com.campusFacilities.www.service.Imp;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.campusFacilities.www.model.Library.BookBarcode;
import com.campusFacilities.www.model.Library.BookCategory;
import com.campusFacilities.www.model.Library.BookIssueRecord;
import com.campusFacilities.www.model.Library.BookReservation;
import com.campusFacilities.www.model.Library.Books;
import com.campusFacilities.www.model.Library.FineSlab;
import com.campusFacilities.www.model.Library.LibraryFine;
import com.campusFacilities.www.model.Library.LibrarySettings;
import com.campusFacilities.www.repository.Library.BookBarcodeRepository;
import com.campusFacilities.www.repository.Library.BookCategoryRepository;
import com.campusFacilities.www.repository.Library.BookIssueRecordRepository;
import com.campusFacilities.www.repository.Library.BookReservationRepository;
import com.campusFacilities.www.repository.Library.BooksRepository;
import com.campusFacilities.www.repository.Library.LibraryFineRepository;
import com.campusFacilities.www.repository.Library.LibrarySettingsRepository;


@Service
public class LibraryServiceImpl {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;


    @Autowired
    private BookIssueRecordRepository issueRepository;

    @Autowired
    private BookReservationRepository bookReservationRepository;

    @Autowired
    private LibraryFineRepository libraryFineRepository;

    @Autowired
    private LibrarySettingsRepository librarySettingsRepository;

     @Autowired
     private BookBarcodeRepository bookBarcodeRepository;
     
     
     @Autowired
     private FineCalculationService fineCalculationService;

    // ================= CATEGORY =================

    public BookCategory createCategory(BookCategory category) {
        if (category.getCategoryName() == null || category.getCategoryName().isBlank()) {
            throw new IllegalArgumentException("Category name is required");
        }
        return bookCategoryRepository.save(category);
    }

    public List<BookCategory> getCategories() {
        return bookCategoryRepository.findByIsDeletedFalse();
    }

    public BookCategory updateCategory(Long id, BookCategory data) {
        BookCategory category = bookCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setCategoryName(data.getCategoryName());
        category.setDescription(data.getDescription());
        category.setStatus(data.getStatus());

        return bookCategoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        BookCategory category = bookCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setIsDeleted(true);
        bookCategoryRepository.save(category);
    }

    // ================= BOOK =================

    public Books createBook(Books book) {
        if (book.getCategory() == null || book.getCategory().getId() == null) {
            throw new IllegalArgumentException("Category is required");
        }

        BookCategory category = bookCategoryRepository.findById(book.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Invalid category"));

        book.setCategory(category);

        // Ensure totalCopies is set (default to 1 for Digital if missing)
        if (book.getTotalCopies() == null) {
            book.setTotalCopies(1);
        }
        book.setAvailableCopies(book.getTotalCopies());

        // Handle empty ISBN for Digital resources
        if (book.getIsbn() != null && book.getIsbn().trim().isEmpty()) {
            book.setIsbn(null);
        }

        Books savedBook = booksRepository.save(book);

        // Automatically generate barcodes for the new book
        if (savedBook.getTotalCopies() != null && savedBook.getTotalCopies() > 0) {
            generateBarcodes(savedBook.getId(), savedBook.getTotalCopies());
        }

        return savedBook;
    }

    public List<Books> getBooks() {
        return booksRepository.findByIsDeletedFalse();
    }

    public List<Books> getBooksByCategory(Long categoryId) {
        return booksRepository.findByCategory_IdAndIsDeletedFalse(categoryId);
    }

    public Books updateBook(Long id, Books data) {
        Books book = booksRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(data.getTitle());
        book.setAuthor(data.getAuthor());
        book.setPublisher(data.getPublisher());
        book.setEdition(data.getEdition());
        book.setLanguage(data.getLanguage());
        book.setTotalCopies(data.getTotalCopies());
        book.setStatus(data.getStatus());

        if (data.getCategory() != null) {
            BookCategory category = bookCategoryRepository.findById(data.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Invalid category"));
            book.setCategory(category);
        }

        return booksRepository.save(book);
    }

    public void deleteBook(Long id) {
        Books book = booksRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setIsDeleted(true);
        booksRepository.save(book);
    }

    // ================= ISSUE / RETURN =================

    public BookIssueRecord issueBook(Long bookId, Long userId, String memberRole) {
        Books book = booksRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // RESERVATION CHECK
        List<BookReservation> userReservations = bookReservationRepository.findByBookIdAndUserIdAndIsDeletedFalse(bookId,
                userId);

        // Find ANY active reservation (Held OR just Reserved)
        BookReservation activeRes = userReservations.stream()
                .filter(r -> r.getStatus() == BookReservation.Status.AVAILABLE
                        || r.getStatus() == BookReservation.Status.RESERVED)
                .findFirst().orElse(null);

        // A "Hold" is specifically when status is AVAILABLE (copy already set aside)
        boolean isHeldReservation = (activeRes != null && activeRes.getStatus() == BookReservation.Status.AVAILABLE);

        // If not holding a specific copy, we need a generic available copy
        if (!isHeldReservation && book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book not available");
        }

        LibrarySettings settings = librarySettingsRepository.findByMemberRoleAndIsDeletedFalse(memberRole)
                .orElse(null);

        // Fallback defaults if settings are missing
        int issueDuration = 14;
        int maxBooks = 3;
        
        if (settings != null) 
        {
            if (settings.getIssueDurationDays() != null) {
                issueDuration = settings.getIssueDurationDays();
            }
            if (settings.getMaxBooks() != null) {
                maxBooks = settings.getMaxBooks();
            }
        }
        long issuedCount = issueRepository
                .countByUserIdAndStatus(userId, BookIssueRecord.Status.ISSUED);

        if (issuedCount >= maxBooks) {
            throw new RuntimeException("Book limit exceeded (Max: " + maxBooks + ")");
        }

        // Only decrement copies if we are NOT fulfilling a PRE-HELD reservation
        // (RESERVED status means we are taking a fresh copy from shelf)
        if (!isHeldReservation) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            if (book.getAvailableCopies() == 0) {
                book.setStatus(Books.Status.UNAVAILABLE);
            }
        }
        
        // Close reservation (Both types)
        if (activeRes != null) {
            activeRes.setStatus(BookReservation.Status.COLLECTED);
            bookReservationRepository.save(activeRes);
        }

        BookIssueRecord issue = new BookIssueRecord();
        issue.setBook(book);
        issue.setUserId(userId);
        issue.setUserCategory(memberRole);
        issue.setIssueDate(LocalDate.now());
        issue.setDueDate(LocalDate.now().plusDays(issueDuration));
        return issueRepository.save(issue);
    }

    public BookIssueRecord issueBookWithBarcode(Long bookId, Long userId, String barcode, String memberRole) {
        Books book = booksRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // RESERVATION CHECK
        List<BookReservation> userReservations = bookReservationRepository.findByBookIdAndUserIdAndIsDeletedFalse(bookId,
                userId);

        // Find ANY active reservation (Held OR just Reserved)
        BookReservation activeRes = userReservations.stream()
                .filter(r -> r.getStatus() == BookReservation.Status.AVAILABLE
                        || r.getStatus() == BookReservation.Status.RESERVED)
                .findFirst().orElse(null);

        boolean isHeldReservation = (activeRes != null && activeRes.getStatus() == BookReservation.Status.AVAILABLE);

        if (!isHeldReservation && book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book not available");
        }

        LibrarySettings settings = librarySettingsRepository.findByMemberRoleAndIsDeletedFalse(memberRole)
                .orElse(null);

        // Fallback defaults if settings are missing
        int issueDuration = 14;
        int maxBooks = 3;

        if (settings != null) {
            if (settings.getIssueDurationDays() != null)
                issueDuration = settings.getIssueDurationDays();
            if (settings.getMaxBooks() != null)
                maxBooks = settings.getMaxBooks();
        }

        long issuedCount = issueRepository.countByUserIdAndStatus(userId, BookIssueRecord.Status.ISSUED);
        if (issuedCount >= maxBooks) {
            throw new RuntimeException("Book limit exceeded (Max: " + maxBooks + ")");
        }

        if (barcode != null) {
          
            BookBarcode bc = book.getBarcodes().stream()
                    .filter(b -> b.getBarcodeValue().equals(barcode))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Barcode not found"));

            if (bc.getIsIssued()) {
                throw new RuntimeException("This copy is already issued");
            }

            bc.setIsIssued(true);
        }

        // Only decrement copies if we are NOT fulfilling a PRE-HELD reservation
        if (!isHeldReservation) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            if (book.getAvailableCopies() == 0) {
                book.setStatus(Books.Status.UNAVAILABLE);
            }
        }

        booksRepository.saveAndFlush(book);

        // Close reservation
        if (activeRes != null) {
            activeRes.setStatus(BookReservation.Status.COLLECTED);
            bookReservationRepository.save(activeRes);
        }

        BookIssueRecord issue = new BookIssueRecord();
        issue.setBook(book);
        issue.setUserId(userId);
        issue.setUserCategory(memberRole);
        issue.setBarcodeValue(barcode);
        issue.setIssueDate(LocalDate.now());
        issue.setDueDate(LocalDate.now().plusDays(issueDuration));

        return issueRepository.save(issue);
    }

    public boolean validateEligibility(Long userId, String memberRole) {
        LibrarySettings settings = librarySettingsRepository.findByMemberRoleAndIsDeletedFalse(memberRole)
                .orElse(null);

        // Fail safe if settings missing for role
        if (settings == null) {
            // Fallback: If no settings exist yet, allow up to 3 books by default
            long issuedCount = issueRepository.countByUserIdAndStatus(userId, BookIssueRecord.Status.ISSUED);
            return issuedCount < 3;
        }

        long issuedCount = issueRepository.countByUserIdAndStatus(userId, BookIssueRecord.Status.ISSUED);
        return issuedCount < settings.getMaxBooks();
    }

    public BookIssueRecord returnBook(Long issueId) {

        BookIssueRecord issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue record not found"));

        issue.setReturnDate(LocalDate.now());
        issue.setStatus(BookIssueRecord.Status.RETURNED);

        if (issue.getReturnDate().isAfter(issue.getDueDate())) {

            long days = java.time.temporal.ChronoUnit.DAYS
                    .between(issue.getDueDate(), issue.getReturnDate());

           // =========================================================
            

            LibrarySettings settings = librarySettingsRepository
                    .findFirstByIsDeletedFalse()
                    .orElse(null);

            if (settings != null) {
                double fineAmount = fineCalculationService
                        .calculateFine(settings.getSettingId(), (int) days);

                if (fineAmount > 0) {
                    LibraryFine fine = new LibraryFine();
                    fine.setIssueRecord(issue);
                    fine.setUserId(issue.getUserId());
                    fine.setFineAmount(fineAmount);
                    libraryFineRepository.save(fine);
                }
            }
        }

        Books book = issue.getBook();

        List<BookReservation> reservations =
                bookReservationRepository.findByBookIdAndIsDeletedFalse(book.getId());

        BookReservation pendingReservation = reservations.stream()
                .filter(r -> r.getStatus() == BookReservation.Status.RESERVED)
                .min((r1, r2) -> r1.getReservedAt().compareTo(r2.getReservedAt()))
                .orElse(null);

        if (pendingReservation != null) {

            pendingReservation.setStatus(BookReservation.Status.AVAILABLE);
            pendingReservation.setAdminHoldFrom(LocalDate.now());

            int holdDays = 5;
            LibrarySettings settings =
                    librarySettingsRepository
                            .findByMemberRoleAndIsDeletedFalse("Student")
                            .orElse(null);

            if (settings != null && settings.getReservationDurationDays() != null) {
                holdDays = settings.getReservationDurationDays();
            }

            pendingReservation.setAdminHoldUntil(LocalDate.now().plusDays(holdDays));
            bookReservationRepository.save(pendingReservation);

        } else {
            book.setAvailableCopies(book.getAvailableCopies() + 1);
        }

        if (book.getAvailableCopies() > 0) {
            book.setStatus(Books.Status.AVAILABLE);
        }

        booksRepository.saveAndFlush(book);

        if (issue.getBarcodeValue() != null) {
            bookBarcodeRepository.findByBarcodeValue(issue.getBarcodeValue())
                    .ifPresent(bc -> {
                        bc.setIsIssued(false);
                        bookBarcodeRepository.save(bc);
                    });
        }

        return issueRepository.save(issue);
    }
        
    public List<BookIssueRecord> getIssues() {
        return issueRepository.findAll();
    }

    // ================= FINES =================

    public List<LibraryFine> getFines() {
        return libraryFineRepository.findAll();
    }

    public List<LibraryFine> getFinesByUserId(Long userId) {
        return libraryFineRepository.findByUserId(userId);
    }

    public LibraryFine payFine(Long fineId) {
        LibraryFine fine = libraryFineRepository.findById(fineId)
                .orElseThrow(() -> new RuntimeException("Fine not found"));
        fine.setPaidStatus(LibraryFine.Status.PAID);
        return libraryFineRepository.save(fine);
    }

    // ================= BARCODES =================

    public List<BookBarcode> generateBarcodes(Long bookId, int count) {
        Books book = booksRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        java.util.List<BookBarcode> barcodes = new java.util.ArrayList<>();
        for (int i = 0; i < count; i++) {
            BookBarcode bc = new BookBarcode();
            bc.setBook(book);
            String prefix = (book.getIsbn() != null) ? book.getIsbn() : "BK-" + book.getId();
            bc.setBarcodeValue(prefix + "-" + System.currentTimeMillis() + "-" + i);
            bc.setIsIssued(false);
            barcodes.add(bookBarcodeRepository.save(bc));
        }
        return barcodes;
    }

    // ================= RESERVATION =================

    public BookReservation createReservation(BookReservation reservation) {
        if (reservation.getBook() == null || reservation.getBook().getId() == null) {
            throw new IllegalArgumentException("Book is required");
        }
        if (reservation.getUserId() == null) {
            throw new IllegalArgumentException("User ID is required");
        }

        Books book = booksRepository.findById(reservation.getBook().getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        reservation.setBook(book);

        // Use user-requested logic: Block reservation if copies are available
        if (book.getAvailableCopies() > 0) {
            throw new RuntimeException(
                    "Book is currently available. No reservation needed. Please visit the library to collect it.");
        }
        
        reservation.setReservedAt(LocalDate.now());
        reservation.setReservationDate(null);
        reservation.setReserveUntil(null);
        reservation.setAdminHoldFrom(null);
        reservation.setAdminHoldUntil(null);

        reservation.setStatus(BookReservation.Status.RESERVED);
        reservation.setIsDeleted(false);

        try {
            return librarySettingsRepository.save(reservation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save reservation: " + e.getMessage(), e);
        }
    }

    public List<BookReservation> getReservations() {
        return bookReservationRepository.findByIsDeletedFalse();
    }

    public void deleteReservation(Long id) {
        BookReservation res = bookReservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        res.setIsDeleted(true);
        bookReservationRepository.save(res);
    }

    public BookReservation updateReservationStatus(Long id, BookReservation.Status status) {
        BookReservation res =bookReservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        // Check for expiry before allowing pickup
        if (status == BookReservation.Status.COLLECTED) {
            // Check based on HOLD deadline (adminHoldUntil)
            if (res.getStatus() == BookReservation.Status.AVAILABLE &&
                    res.getAdminHoldUntil() != null &&
                    res.getAdminHoldUntil().isBefore(LocalDate.now())) {

                // Expired! Release hold
                Books book = res.getBook();
                book.setAvailableCopies(book.getAvailableCopies() + 1);
                book.setStatus(Books.Status.AVAILABLE);
                booksRepository.saveAndFlush(book);

                res.setStatus(BookReservation.Status.NO_RESPONSE); // Using NO_RESPONSE to map to Expired
                bookReservationRepository.save(res);
                throw new RuntimeException("Pickup window expired");
            }
        }

        // Logic Change: Admin puts book ON HOLD (Available for pickup)
        if (status == BookReservation.Status.AVAILABLE) {
            if (res.getStatus() != BookReservation.Status.AVAILABLE) { // Only do this once
                Books book = res.getBook();
                if (book.getAvailableCopies() <= 0) {
                    throw new RuntimeException("No copies available to hold");
                }
                book.setAvailableCopies(book.getAvailableCopies() - 1);
                if (book.getAvailableCopies() == 0) {
                    book.setStatus(Books.Status.UNAVAILABLE);
                }
                booksRepository.saveAndFlush(book);
            }
        }

        // Logic Change: User COLLECTS the book
        if (status == BookReservation.Status.COLLECTED) {
            // Count was decremented when it went to AVAILABLE (Hold)
            // So we do nothing to copies here.

            // Ideally should also create Issue Record here?
            // Leaving that part as current scope seems to focus on counts.
        }

        if (status == BookReservation.Status.CANCELLED) {
            // If a user cancels a reservation, we should check if it was holding a book.
            // If status was AVAILABLE (Held), then we must return it to shelf!
            if (res.getStatus() == BookReservation.Status.AVAILABLE) {
                Books book = res.getBook();
                book.setAvailableCopies(book.getAvailableCopies() + 1); // Release hold
                if (book.getAvailableCopies() > 0) {
                    book.setStatus(Books.Status.AVAILABLE);
                }
                booksRepository.saveAndFlush(book);
            }
        }

        res.setStatus(status);
        return bookReservationRepository.save(res);
    }

    // ================= SETTINGS =================

    public LibrarySettings saveSettings(LibrarySettings settings) {
        if (settings.getMemberRole() == null) {
            throw new IllegalArgumentException("Member Role is required");
        }

        // Ensure isDeleted is never null to prevent duplicates on lookup
        if (settings.getIsDeleted() == null) {
            settings.setIsDeleted(false);
        }

        // Check availability by Role to avoid duplicates if ID is null
        // Check availability by Role to avoid duplicates if ID is null
        LibrarySettings existingSettings = librarySettingsRepository
                .findByMemberRoleAndIsDeletedFalse(settings.getMemberRole())
                .orElse(null);

        if (existingSettings != null) {
            // Update existing settings
            existingSettings.setMaxBooks(settings.getMaxBooks());
            existingSettings.setIssueDurationDays(settings.getIssueDurationDays());
            existingSettings.setReservationDurationDays(settings.getReservationDurationDays());
            existingSettings.setIsDeleted(settings.getIsDeleted());

            // Handle Fine Slabs
            if (settings.getFineSlabs() != null) {
                // Clear existing slabs to handle removals/updates cleanly (orphanRemoval=true
                // handles delete)
                existingSettings.getFineSlabs().clear();

                // Add new slabs
                for (FineSlab slab : settings.getFineSlabs()) {
                    slab.setLibrarySettings(existingSettings);
                    slab.setMemberRole(existingSettings.getMemberRole());
                    existingSettings.getFineSlabs().add(slab);
                }
            }

            return librarySettingsRepository.save(existingSettings);
        } else {
            // Create new settings
            if (settings.getFineSlabs() != null) {
                for (FineSlab slab : settings.getFineSlabs()) {
                    slab.setLibrarySettings(settings);
                    slab.setMemberRole(settings.getMemberRole());
                }
            }
            return librarySettingsRepository.save(settings);
        }

   
    }

    public List<LibrarySettings> getSettings() {
        return librarySettingsRepository.findByIsDeletedFalse();
    }

}