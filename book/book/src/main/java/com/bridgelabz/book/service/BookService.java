package com.bridgelabz.book.service;


import com.bridgelabz.book.dto.BookDTO;
import com.bridgelabz.book.entity.Book;
import com.bridgelabz.book.exception.BookException;
import com.bridgelabz.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    BookRepository repo;


    //taking Book from controller and saving into database
    @Override
    public Book insertBook(BookDTO dto) {
        Book book = new Book(dto);
        repo.save(book);
        return book;
    }

    //calling method and returning list of all Book
    @Override
    public List<Book> getAllBooks() {
        List<Book> list = repo.findAll();
        if (list.isEmpty()) {
            throw new NullPointerException("there are no books inserted yet");
        }
        return list;
    }

    //to get book using Book id through path variable
    @Override
    public Book getbookByID(Integer bookId) {
        Optional<Book> book = repo.findByBookId(bookId);
        if (book.isEmpty()) {
            throw new BookException("There are no Books with given id");
        }
        return book.get();
    }

    //to retrieve book using book name provided
    @Override
    public Book getbookByBookName(String bookName) {
        Optional<Book> book = repo.findByBookName(bookName);
        if (book.isEmpty()) {
            throw new BookException("There are no Books with given name");
        }
        return book.get();
    }

    //only to use if there is issue with null point exception
    @Override
    public List<Book> retrieveBooks() {
        List<Book> list = repo.findAll();
        if (list.isEmpty()) {
            throw new BookException("There are no Books added");
        } else {
            return list;
        }
    }

    //update book
    @Override
    public Book updateById(Integer bookId, BookDTO dto) {
        Optional<Book> book = repo.findById(bookId);
        if (book.isPresent()) {
            Book newBook = new Book(bookId, dto);
            repo.save(newBook);
            return newBook;
        } else {
            throw new BookException("Book not found");
        }
    }

    //to delete existing book using his id
    @Override
    public Book deleteById(Integer bookId) {
        Optional<Book> book = repo.findById(bookId);
        if (book.isEmpty()) {
            throw new BookException("Invalid BookId..please input valid Id");
        }
        repo.deleteById(bookId);
        return book.get();
    }

    //list of book in ascending order
    @Override
    public List<Book> sortBooksAsc() {
        return repo.sortBooksAsc();
    }

    //list of book in descending order
    @Override
    public List<Book> sortBooksDesc() {
        return repo.sortBooksDesc();
    }

    //to update the quantity
    @Override
    public Book updateBookQuantity(int bookId, int bookQuantity) {
        Book book = this.getbookByID(bookId);
        book.setQuantity(bookQuantity);
        return repo.save(book);
    }


    //----------------------------service for rest template---------------//

    @Override
    public Book getbookByIdAPI(Integer bookId) {
        Optional<Book> book = repo.findById(bookId);
        if(book.isEmpty()) {
            throw new BookException("There are no Books with given id");
        }
        return book.get();
    }
}