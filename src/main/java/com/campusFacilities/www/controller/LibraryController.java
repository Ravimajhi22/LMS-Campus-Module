package com.campusFacilities.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusFacilities.www.model.Library.BookCategory;
import com.campusFacilities.www.model.Library.BookIssueRecord;
import com.campusFacilities.www.model.Library.BookReservation;
import com.campusFacilities.www.model.Library.Books;
import com.campusFacilities.www.model.Library.LibraryFine;
import com.campusFacilities.www.model.Library.LibraryMember;
import com.campusFacilities.www.model.Library.LibrarySettings;
import com.campusFacilities.www.service.Imp.LibraryServiceImpl;

import lombok.Data;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryServiceImpl libraryService;

    // ====== BOOKS ===================
    
    @PostMapping("/books")
    public Books addBook(@RequestBody Books book) {
        return libraryService.addBook(book);
    }

    @GetMapping("/books")
    public List<Books> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @PutMapping("/books/{id}")
    public Books updateBook(@PathVariable Long id, @RequestBody Books book) {
        return libraryService.updateBook(id, book);
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable Long id) {
        libraryService.deleteBook(id);
        return "Book deleted successfully!";
    }

    // ====== BOOK CATEGORIES ======
    
    @PostMapping("/categories")
    public BookCategory addCategory(@RequestBody BookCategory category) {
        return libraryService.addCategory(category);
    }
    @GetMapping("/categories")
    public List<BookCategory> getAllCategories() {
        return libraryService.getAllCategories();
    }

    @PutMapping("/categories/{id}")
    public BookCategory updateCategory(@PathVariable Long id, @RequestBody BookCategory category) {
        return libraryService.updateCategory(id, category);
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable Long id) {
        libraryService.deleteCategory(id);
        return "Category deleted successfully!";
    }
   
    // ====== ISSUE RECORDS ======
    
    @PostMapping("/issue")
    public BookIssueRecord issueBook(@RequestBody IssueRequest request) {
        return libraryService.issueBook(request.getBookId(), request.getMemberId());
    }
 
    @Data
    public static class IssueRequest {
        private Long bookId;
        private Long memberId;
    }

    @GetMapping("/issues")
    public List<Books> getAllIssuedBooks() {
        return libraryService.getAllBooks();
    }
    @PutMapping("/return/{id}")
    public BookIssueRecord returnBook(@PathVariable Long id) {
        return libraryService.returnBook(id);
    }

    @DeleteMapping("/issues/{id}")
    public String deleteIssueRecord(@PathVariable Long id) {
        libraryService.deleteIssueRecord(id);
        return "Issue record deleted successfully!";
    }

    
    // ====== RESERVATIONS ======
    @PostMapping("/reservations")
    public BookReservation addReservation(@RequestBody BookReservation reservation) {
        return libraryService.addReservation(reservation);
    }

    @GetMapping("/reservationses")
    public List<BookReservation> getAllReservations() {
        return libraryService.getAllReservations();
    }

    @PutMapping("/reservations/{id}")
    public BookReservation updateReservation(@PathVariable Long id, @RequestBody BookReservation reservation) {
        return libraryService.updateReservation(id, reservation);
    }

    @DeleteMapping("/reservations/{id}")
    public String deleteReservation(@PathVariable Long id) {
        libraryService.deleteReservation(id);
        return "Reservation deleted successfully!";
    }

    // ====== FINES ======
    @PostMapping("/fines")
    public LibraryFine addFine(@RequestBody LibraryFine fine) {
        return libraryService.addFine(fine);
    }

    @GetMapping("/fines")
    public List<LibraryFine> getAllFines() {
        return libraryService.getAllFines();
    }

    @PutMapping("/fines/{id}")
    public LibraryFine updateFine(@PathVariable Long id, @RequestBody LibraryFine fine) {
        return libraryService.updateFine(id, fine);
    }

    @DeleteMapping("/fines/{id}")
    public String deleteFine(@PathVariable Long id) {
        libraryService.deleteFine(id);
        return "Fine deleted successfully!";
    }

    //========Members========
    
    // CREATE a new library member
    @PostMapping("/member")
    public ResponseEntity<LibraryMember> createMember(@RequestBody LibraryMember member) {
        LibraryMember createdMember = libraryService.addMember(member);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }

    // GET all library members
    @GetMapping("/members")
    public ResponseEntity<List<LibraryMember>> getAllMembers() {
        List<LibraryMember> members = libraryService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    // GET a single member by ID
    @GetMapping("/members/{id}")
    public ResponseEntity<LibraryMember> getMemberById(@PathVariable Long id) {
        LibraryMember member = libraryService.getMemberById(id);
        return ResponseEntity.ok(member);
    }

    // UPDATE an existing member
    @PutMapping("/members/{id}")
    public ResponseEntity<LibraryMember> updateMember(
            @PathVariable Long id,
            @RequestBody LibraryMember member) {
        LibraryMember updatedMember = libraryService.updateMember(id, member);
        return ResponseEntity.ok(updatedMember);
    }

    // DELETE a member
    @DeleteMapping("/members/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        libraryService.deleteMember(id);
        return ResponseEntity.ok("Library member deleted successfully");
    }
    // ====== SETTINGS ======
    @PostMapping("/settings")
    public LibrarySettings addSettings(@RequestBody LibrarySettings settings) {
        return libraryService.addSettings(settings);
    }

    @GetMapping("/settings")
    public List<LibrarySettings> getSettings() {
        return libraryService.getAllSettings();
    }

    @PutMapping("/settings/{id}")
    public LibrarySettings updateSettings(@PathVariable Long id, @RequestBody LibrarySettings settings) {
        return libraryService.updateSettings(id, settings);
    }

    @DeleteMapping("/settings/{id}")
    public String deleteSettings(@PathVariable Long id) {
        libraryService.deleteSettings(id);
        return "Settings deleted successfully!";
    }
}
