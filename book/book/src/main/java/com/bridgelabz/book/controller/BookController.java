package com.bridgelabz.book.controller;

import com.bridgelabz.book.dto.BookDTO;
import com.bridgelabz.book.dto.ResponseDTO;
import com.bridgelabz.book.entity.Book;
import com.bridgelabz.book.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    IBookService service;

    //to insert book in bookstore app
    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertBook(@Valid @RequestBody BookDTO dto) {
        Book book = service.insertBook(dto);
        ResponseDTO response = new ResponseDTO("book Registered to bookstore", book);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }

    //to get list of books from in book store app
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllBooks() {
        List<Book> list = service.getAllBooks();
        ResponseDTO response = new ResponseDTO("List of books in bookstore", list);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }

    //find book by BookId
    @GetMapping("/findbyid/{bookId}")
    public ResponseEntity<ResponseDTO> getbookByID(@PathVariable Integer bookId) {
        Book book = service.getbookByID(bookId);
        ResponseDTO response = new ResponseDTO("Requested book : ", book);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }

    //find book by Book Name
    @GetMapping("/findbybookname/{bookName}")
    public ResponseEntity<ResponseDTO> getbookByBookName(@PathVariable String bookName) {
        Book book = service.getbookByBookName(bookName);
        ResponseDTO response = new ResponseDTO("Requested book : ", book);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }

    //to get list of books using a global key
    @GetMapping("/get")
    public ResponseEntity<String> retrieveBooks() {
        List<Book> book = service.retrieveBooks();
        ResponseDTO response = new ResponseDTO("books : :", book);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //to update the book through token (using .findByBookId & .isPresent)
    @PutMapping("/update/{BookId}")
    public ResponseEntity<ResponseDTO> updateById(@PathVariable Integer BookId, @Valid @RequestBody BookDTO dto) {
        Book book = service.updateById(BookId, dto);
        ResponseDTO response = new ResponseDTO("book updated : ", book);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }

    //to delete book(using .findByBookId & .isEmpty)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable Integer id) {
        Book book = service.deleteById(id);
        ResponseDTO response = new ResponseDTO("book deleted successfully", book);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }

    // to sort book records in ascending order
    @GetMapping("/sortascending")
    public ResponseEntity<ResponseDTO> sortBooksAsc() {
        List<Book> book = service.sortBooksAsc();
        ResponseDTO dto = new ResponseDTO("Books in ascending order :", book);
        return new ResponseEntity<ResponseDTO>(dto, HttpStatus.OK);
    }

    //to sort book records in descending order
    @GetMapping("/sortdescending")
    public ResponseEntity<ResponseDTO> sortBooksDesc() {
        List<Book> book = service.sortBooksDesc();
        ResponseDTO dto = new ResponseDTO("Books in  descending order :", book);
        return new ResponseEntity<ResponseDTO>(dto, HttpStatus.OK);
    }

    //to update the quantity of book
    @PutMapping("/updatequantity/{bookId}/{bookQuantity}")
    public ResponseEntity<ResponseDTO> updateBookQuantity(@PathVariable int bookId, @PathVariable int bookQuantity) {
        Book updateBookQuantity = service.updateBookQuantity(bookId, bookQuantity);
        ResponseDTO response = new ResponseDTO("Book Quantity Update is success for id " + bookId, updateBookQuantity);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }

    //---------------------------API for rest template---------------------------//

    @GetMapping("/getBook/{bookId}")
    public Book getbookByIdAPI(@PathVariable Integer bookId){
        System.out.println("Test");
        Book book = service.getbookByIdAPI(bookId);
        return book;
    }

}