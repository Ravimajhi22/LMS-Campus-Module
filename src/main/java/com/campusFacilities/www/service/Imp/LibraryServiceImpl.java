package com.campusFacilities.www.service.Imp;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusFacilities.www.model.Library.BookCategory;
import com.campusFacilities.www.model.Library.BookIssueRecord;
import com.campusFacilities.www.model.Library.BookReservation;
import com.campusFacilities.www.model.Library.Books;
import com.campusFacilities.www.model.Library.LibraryFine;
import com.campusFacilities.www.model.Library.LibraryMember;
import com.campusFacilities.www.model.Library.LibrarySettings;
import com.campusFacilities.www.repository.Library.BookCategoryRepository;
import com.campusFacilities.www.repository.Library.BookIssueRecordRepository;
import com.campusFacilities.www.repository.Library.BookReservationRepository;
import com.campusFacilities.www.repository.Library.BooksRepository;
import com.campusFacilities.www.repository.Library.LibraryFineRepository;
import com.campusFacilities.www.repository.Library.LibraryMemberRepository;
import com.campusFacilities.www.repository.Library.LibrarySettingsRepository;

@Service
public class LibraryServiceImpl {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    private LibraryMemberRepository memberRepository;

    @Autowired
    private BookIssueRecordRepository issueRepository;

    @Autowired
    private BookReservationRepository bookReservationRepository;

    @Autowired
    private LibraryFineRepository libraryFineRepository;

    @Autowired
    private LibrarySettingsRepository librarySettingsRepository;

    // ================= BOOK =================

    public Books addBook(Books book) {
        book.setAvailableCopies(book.getTotalCopies());
        book.setStatus(Books.Status.AVAILABLE);
        return booksRepository.save(book);
    }

    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }
    

    public Books updateBook(Long bookId, Books updatedBook) {

        Books existingBook = booksRepository.findById(bookId)
                .orElseThrow(() ->
                        new RuntimeException("Book not found with id: " + bookId));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setEdition(updatedBook.getEdition());
        existingBook.setPublisher(updatedBook.getPublisher());
        existingBook.setTotalCopies(updatedBook.getTotalCopies());
        existingBook.setAvailableCopies(updatedBook.getAvailableCopies());
        existingBook.setStatus(updatedBook.getStatus());

        return booksRepository.save(existingBook);
    }

    public void deleteBook(Long bookId) {

        Books book = booksRepository.findById(bookId)
                .orElseThrow(() ->
                        new RuntimeException("Book not found with id: " + bookId));

        booksRepository.delete(book);
    }
    

    // ================= BOOK CATEGORY =================

    public BookCategory addCategory(BookCategory category) {
        category.setIsDeleted(false);
        return bookCategoryRepository.save(category);
    }

    public List<BookCategory> getAllCategories() {
        return bookCategoryRepository.findByIsDeletedFalse();
    }

    public BookCategory updateCategory(Long id, BookCategory updatedCategory) {

        BookCategory existing = bookCategoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found with id: " + id));

        existing.setCategoryName(updatedCategory.getCategoryName());
        existing.setDescription(updatedCategory.getDescription());
        existing.setStatus(updatedCategory.getStatus());

        return bookCategoryRepository.save(existing);
    }

    public void deleteCategory(Long id) {

        BookCategory category = bookCategoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found with id: " + id));

        category.setIsDeleted(true);
        bookCategoryRepository.save(category);
    }
  
    public BookCategory patchCategory(Long categoryId, Map<String, Object> updates) {
        BookCategory category = bookCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        if (updates.containsKey("categoryName"))
            category.setCategoryName((String) updates.get("categoryName"));
        if (updates.containsKey("description"))
            category.setDescription((String) updates.get("description"));

        return bookCategoryRepository.save(category);
    }

    // ================= ISSUE BOOK =================

 
    public BookIssueRecord issueBook(Long bookId, Long memberId) {

        Books book = booksRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book not available");
        }

        LibraryMember member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        booksRepository.save(book);

        BookIssueRecord issue = new BookIssueRecord();
        issue.setBook(book);
        issue.setMember(member);
        issue.setIssueDate(LocalDate.now());
        issue.setDueDate(LocalDate.now().plusDays(14));
        issue.setStatus(BookIssueRecord.Status.ISSUED);

        return issueRepository.save(issue);
    }
    
    public List<BookIssueRecord> getAllIssueRecords() {
        return issueRepository.findAll();
    }


    public void deleteIssueRecord(Long id) {
        issueRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Issue record not found with id: " + id));
    }
   
    public BookIssueRecord patchIssueRecord(Long issueId, Map<String, Object> updates) {
        BookIssueRecord issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue record not found with id: " + issueId));

        if (updates.containsKey("status"))
            issue.setStatus(BookIssueRecord.Status.valueOf((String) updates.get("status")));
        if (updates.containsKey("dueDate"))
            issue.setDueDate(LocalDate.parse((String) updates.get("dueDate")));

        return issueRepository.save(issue);
    }

    // ================= BOOK RESERVATION =================

    public BookReservation addReservation(BookReservation reservation) {
        reservation.setIsDeleted(false);
        return bookReservationRepository.save(reservation);
    }

    public List<BookReservation> getAllReservations() {
        return bookReservationRepository.findByIsDeletedFalse();
    }

    public BookReservation updateReservation(Long id, BookReservation updatedReservation) {

        BookReservation existing = bookReservationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Reservation not found with id: " + id));

        existing.setBook(updatedReservation.getBook());
        existing.setMember(updatedReservation.getMember());
        existing.setReservationDate(updatedReservation.getReservationDate());
        existing.setStatus(updatedReservation.getStatus());

        return bookReservationRepository.save(existing);
    }

    public void deleteReservation(Long id) {

        BookReservation reservation = bookReservationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Reservation not found with id: " + id));

        reservation.setIsDeleted(true);
        bookReservationRepository.save(reservation);
    }
    public BookReservation patchReservation(Long reservationId, Map<String, Object> updates) {
        BookReservation reservation = bookReservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + reservationId));

        if (updates.containsKey("reservationDate"))
            reservation.setReservationDate(LocalDate.parse((String) updates.get("reservationDate")));

        return bookReservationRepository.save(reservation);
    }


    // ================= FINES =================

    public LibraryFine addFine(LibraryFine fine) {
        fine.setIsDeleted(false);
        return libraryFineRepository.save(fine);
    }

    public List<LibraryFine> getAllFines() {
        return libraryFineRepository.findByIsDeletedFalse();
    }

    public LibraryFine updateFine(Long id, LibraryFine updatedFine) {

        LibraryFine existing = libraryFineRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Fine not found with id: " + id));

        existing.setFineAmount(updatedFine.getFineAmount());
        existing.setPaidStatus(updatedFine.getPaidStatus());

        return libraryFineRepository.save(existing);
    }

    public void deleteFine(Long id) {

        LibraryFine fine = libraryFineRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Fine not found with id: " + id));

        fine.setIsDeleted(true);
        libraryFineRepository.save(fine);
    }
    public LibraryFine patchFine(Long fineId, Map<String, Object> updates) {
        LibraryFine fine = libraryFineRepository.findById(fineId)
                .orElseThrow(() -> new RuntimeException("Fine not found with id: " + fineId));

        if (updates.containsKey("fineAmount"))
            fine.setFineAmount((Double) updates.get("fineAmount"));
        if (updates.containsKey("paidStatus"))
            fine.setPaidStatus(LibraryFine.Status.valueOf((String) updates.get("paidStatus")));

        return libraryFineRepository.save(fine);
    }
    
    // ================= RETURN BOOK =================

    public BookIssueRecord returnBook(Long issueId) {

        BookIssueRecord issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue record not found"));

        issue.setReturnDate(LocalDate.now());
        issue.setStatus(BookIssueRecord.Status.RETURNED);

        Books book = issue.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        booksRepository.save(book);

        return issueRepository.save(issue);
    }

    // ================= MEMBERS =================

    public LibraryMember addMember(LibraryMember member) {
        member.setIsDeleted(false);
        return memberRepository.save(member);
    }

    public List<LibraryMember> getAllMembers() {
        return memberRepository.findByIsDeletedFalse();
    }

    public LibraryMember getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .filter(m -> !m.getIsDeleted())
                .orElseThrow(() ->
                        new RuntimeException("Member not found with id: " + memberId));
    }

    public LibraryMember updateMember(Long memberId, LibraryMember updatedMember) {

        LibraryMember existing = getMemberById(memberId);

        existing.setUserId(updatedMember.getUserId());
        existing.setMemberType(updatedMember.getMemberType());
        existing.setMaxBooksAllowed(updatedMember.getMaxBooksAllowed());
        existing.setStatus(updatedMember.getStatus());

        return memberRepository.save(existing);
    }

    public void deleteMember(Long memberId) {

        LibraryMember member = getMemberById(memberId);
        member.setIsDeleted(true);
        memberRepository.save(member);
    }
    public LibraryMember patchMember(Long memberId, Map<String, Object> updates) {
        LibraryMember member = getMemberById(memberId);

        if (updates.containsKey("userId"))
            member.setUserId((Long) updates.get("userId"));
        if (updates.containsKey("memberType"))
            member.setMemberType((String) updates.get("memberType"));
        if (updates.containsKey("maxBooksAllowed"))
            member.setMaxBooksAllowed((Integer) updates.get("maxBooksAllowed"));
        if (updates.containsKey("status"))
            member.setStatus((String) updates.get("status"));

        return memberRepository.save(member);
    }
    // ================= SETTINGS =================

    public LibrarySettings addSettings(LibrarySettings settings) {
        settings.setIsDeleted(false);
        return librarySettingsRepository.save(settings);
    }

    public List<LibrarySettings> getAllSettings() {
        return librarySettingsRepository.findByIsDeletedFalse();
    }

    public LibrarySettings updateSettings(Long id, LibrarySettings updatedSettings) {

        LibrarySettings existing = librarySettingsRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Settings not found with id: " + id));

        return librarySettingsRepository.save(existing);
    }

    public void deleteSettings(Long id) {

        LibrarySettings settings = librarySettingsRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Settings not found with id: " + id));

        settings.setIsDeleted(true);
        librarySettingsRepository.save(settings);
    }
    
}
