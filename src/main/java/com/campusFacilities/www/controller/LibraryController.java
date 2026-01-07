package com.campusFacilities.www.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusFacilities.www.model.Library.BookCategory;
import com.campusFacilities.www.model.Library.BookIssueRecord;
import com.campusFacilities.www.model.Library.BookIssueRecord.IssueRequest;
import com.campusFacilities.www.model.Library.BookReservation;
import com.campusFacilities.www.model.Library.Books;
import com.campusFacilities.www.model.Library.LibraryFine;
import com.campusFacilities.www.model.Library.LibraryMember;
import com.campusFacilities.www.model.Library.LibrarySettings;
import com.campusFacilities.www.service.Imp.LibraryServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class LibraryController {

    @Autowired
    private LibraryServiceImpl libraryService;

    // ============= BOOKS ===================//
    
    @PostMapping("/books")
    @PreAuthorize("hasAuthority('BOOK_CREATE')")
    public ResponseEntity<?> addBook(@RequestBody Books book) {
        return ResponseEntity.ok(libraryService.addBook(book));
    }

    @GetMapping("/books")
    @PreAuthorize("hasAuthority('BOOK_VIEW')")
    public ResponseEntity<?> getAllBooks() {
        return ResponseEntity.ok(libraryService.getAllBooks());
    }

    @PutMapping("/books/{id}")
    @PreAuthorize("hasAuthority('BOOK_UPDATE')")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Books book) {
        return ResponseEntity.ok(libraryService.updateBook(id, book));
    }

    @DeleteMapping("/books/{id}")
    @PreAuthorize("hasAuthority('BOOK_DELETE')")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        libraryService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully!");
    }

    @PatchMapping("/books/{id}")
    @PreAuthorize("hasAuthority('BOOK_UPDATE')")
    public ResponseEntity<?> patchBook(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(libraryService.patchBook(id, updates));
    }


    // ================ BOOK CATEGORIES ================//
    
    @PostMapping("/categories")
    @PreAuthorize("hasAuthority('BOOK_CATEGORY_CREATE')")
    public ResponseEntity<?> addCategory(@RequestBody BookCategory category) {
        return ResponseEntity.ok(libraryService.addCategory(category));
    }

    @GetMapping("/categories")
    @PreAuthorize("hasAuthority('BOOK_CATEGORY_VIEW')")
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(libraryService.getAllCategories());
    }

    @PutMapping("/categories/{id}")
    @PreAuthorize("hasAuthority('BOOK_CATEGORY_UPDATE')")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody BookCategory category) {
        return ResponseEntity.ok(libraryService.updateCategory(id, category));
    }

    @DeleteMapping("/categories/{id}")
    @PreAuthorize("hasAuthority('BOOK_CATEGORY_DELETE')")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        libraryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully!");
    }

    @PatchMapping("/categories/{id}")
    @PreAuthorize("hasAuthority('CATEGORY_UPDATE')")
    public ResponseEntity<?> patchCategory(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(libraryService.patchCategory(id, updates));
    }

    
    // ================= ISSUE RECORDS ================//
    
    @PostMapping("/issue")
    @PreAuthorize("hasAuthority('BOOK_ISSUE_CREATE')")
    public ResponseEntity<?> issueBook(@RequestBody IssueRequest request) {
        return ResponseEntity.ok(libraryService.issueBook(request.getBookId(), request.getMemberId()));
    }

    @GetMapping("/issues")
    @PreAuthorize("hasAuthority('BOOK_ISSUE_VIEW')")
    public List<BookIssueRecord> getAllIssuedBooks() {
        return libraryService.getAllIssueRecords();
    }


    @PutMapping("/return/{id}")
    @PreAuthorize("hasAuthority('BOOK_ISSUE_UPDATE')")
    public ResponseEntity<?> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(libraryService.returnBook(id));
    }

    @DeleteMapping("/issues/{id}")
    @PreAuthorize("hasAuthority('BOOK_ISSUE_DELETE')")
    public ResponseEntity<?> deleteIssueRecord(@PathVariable Long id) {
        libraryService.deleteIssueRecord(id);
        return ResponseEntity.ok("Issue record deleted successfully!");
    }
    @PatchMapping("/issues/{id}")
    @PreAuthorize("hasAuthority('ISSUE_UPDATE')")
    public ResponseEntity<?> patchIssueRecord(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(libraryService.patchIssueRecord(id, updates));
    }


    
    // =================== RESERVATIONS ========================//
    
    @PostMapping("/reservations")
    @PreAuthorize("hasAuthority('BOOK_RESERVATION_CREATE')")
    public ResponseEntity<?> addReservation(@RequestBody BookReservation reservation) {
        return ResponseEntity.ok(libraryService.addReservation(reservation));
    }

    @GetMapping("/reservations")
    @PreAuthorize("hasAuthority('BOOK_RESERVATION_VIEW')")
    public ResponseEntity<?> getAllReservations() {
        return ResponseEntity.ok(libraryService.getAllReservations());
    }

    @PutMapping("/reservations/{id}")
    @PreAuthorize("hasAuthority('BOOK_RESERVATION_UPDATE')")
    public ResponseEntity<?> updateReservation(@PathVariable Long id, @RequestBody BookReservation reservation) {
        return ResponseEntity.ok(libraryService.updateReservation(id, reservation));
    }

    @DeleteMapping("/reservations/{id}")
    @PreAuthorize("hasAuthority('BOOK_RESERVATION_DELETE')")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        libraryService.deleteReservation(id);
        return ResponseEntity.ok("Reservation deleted successfully!");
    }
    @PatchMapping("/reservations/{id}")
    @PreAuthorize("hasAuthority('RESERVATION_UPDATE')")
    public ResponseEntity<?> patchReservation(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(libraryService.patchReservation(id, updates));
    }

    // ================== FINES ======================//
    
    @PostMapping("/fines")
    @PreAuthorize("hasAuthority('LIBRARY_FINE_CREATE')")
    public ResponseEntity<?> addFine(@RequestBody LibraryFine fine) {
        return ResponseEntity.ok(libraryService.addFine(fine));
    }

    @GetMapping("/fines")
    @PreAuthorize("hasAuthority('LIBRARY_FINE_VIEW')")
    public ResponseEntity<?> getAllFines() {
        return ResponseEntity.ok(libraryService.getAllFines());
    }
    

    @PutMapping("/fines/{id}")
    @PreAuthorize("hasAuthority('LIBRARY_FINE_UPDATE')")
    public ResponseEntity<?> updateFine(@PathVariable Long id, @RequestBody LibraryFine fine) {
        return ResponseEntity.ok(libraryService.updateFine(id, fine));
    }

    @DeleteMapping("/fines/{id}")
    @PreAuthorize("hasAuthority('LIBRARY_FINE_DELETE')")
    public ResponseEntity<?> deleteFine(@PathVariable Long id) {
        libraryService.deleteFine(id);
        return ResponseEntity.ok("Fine deleted successfully!");
    }
    
    @PatchMapping("/fines/{id}")
    @PreAuthorize("hasAuthority('FINE_UPDATE')")
    public ResponseEntity<?> patchFine(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(libraryService.patchFine(id, updates));
    }

    
    //==================Members====================//
    
    @PostMapping("/members")
    @PreAuthorize("hasAuthority('LIBRARY_MEMBER_CREATE')")
    public ResponseEntity<?> createMember(@RequestBody LibraryMember member) {
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.addMember(member));
    }

    @GetMapping("/members")
    @PreAuthorize("hasAuthority('LIBRARY_MEMBER_VIEW')")
    public ResponseEntity<?> getAllMembers() {
        return ResponseEntity.ok(libraryService.getAllMembers());
    }

    @GetMapping("/members/{id}")
    @PreAuthorize("hasAuthority('LIBRARY_MEMBER_VIEW')")
    public ResponseEntity<?> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(libraryService.getMemberById(id));
    }

    @PutMapping("/members/{id}")
    @PreAuthorize("hasAuthority('LIBRARY_MEMBER_UPDATE')")
    public ResponseEntity<?> updateMember(@PathVariable Long id, @RequestBody LibraryMember member) {
        return ResponseEntity.ok(libraryService.updateMember(id, member));
    }

    @DeleteMapping("/members/{id}")
    @PreAuthorize("hasAuthority('LIBRARY_MEMBER_DELETE')")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        libraryService.deleteMember(id);
        return ResponseEntity.ok("Library member deleted successfully");
    }
    @PatchMapping("/members/{id}")
    @PreAuthorize("hasAuthority('MEMBER_UPDATE')")
    public ResponseEntity<?> patchMember(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(libraryService.patchMember(id, updates));
    }

    // ================== SETTINGS ==================//
    
    @PostMapping("/settings")
    @PreAuthorize("hasAuthority('LIBRARY_SETTINGS_CREATE')")
    public ResponseEntity<?> addSettings(@RequestBody LibrarySettings settings) {
        return ResponseEntity.ok(libraryService.addSettings(settings));
    }

    @GetMapping("/settings")
    @PreAuthorize("hasAuthority('LIBRARY_SETTINGS_VIEW')")
    public ResponseEntity<?> getSettings() {
        return ResponseEntity.ok(libraryService.getAllSettings());
    }

    @PutMapping("/settings/{id}")
    @PreAuthorize("hasAuthority('LIBRARY_SETTINGS_UPDATE')")
    public ResponseEntity<?> updateSettings(@PathVariable Long id, @RequestBody LibrarySettings settings) {
        return ResponseEntity.ok(libraryService.updateSettings(id, settings));
    }

    @DeleteMapping("/settings/{id}")
    @PreAuthorize("hasAuthority('LIBRARY_SETTINGS_DELETE')")
    public ResponseEntity<?> deleteSettings(@PathVariable Long id) {
        libraryService.deleteSettings(id);
        return ResponseEntity.ok("Settings deleted successfully!");
    }
   

}