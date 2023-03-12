package com.techreturners.bookmanager.controller;

import com.techreturners.bookmanager.model.Book;
import com.techreturners.bookmanager.service.BookManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookManagerController {

    @Autowired
    BookManagerService bookManagerService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookManagerService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping({"/{bookId}"})
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
      if(!bookManagerService.existId(bookId)){
          return new ResponseEntity<>(bookManagerService.getBookById(bookId), HttpStatus.OK);
      }else {
          return new ResponseEntity<>( HttpStatus.OK);
      }

    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        if(!bookManagerService.existBook(book)){

            Book newBook = bookManagerService.insertBook(book);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("book", "/api/v1/book/" + newBook.getId().toString());
            return new ResponseEntity<>(newBook, httpHeaders, HttpStatus.CREATED);
        }else{
            System.out.println("Already Exists");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    //User Story 4 - Update Book By Id Solution
    @PutMapping({"/{bookId}"})
    public ResponseEntity<Book> updateBookById(@PathVariable("bookId") Long bookId, @RequestBody Book book) {
        bookManagerService.updateBookById(bookId, book);
        return new ResponseEntity<>(bookManagerService.getBookById(bookId), HttpStatus.OK);
    }

    //User Story 5 - Delete Book By Id Solution
    @DeleteMapping({"/{bookId}"})
    public ResponseEntity<Book> deleteBookById(@PathVariable Long bookId) {
        bookManagerService.deleteBookId(bookId);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
