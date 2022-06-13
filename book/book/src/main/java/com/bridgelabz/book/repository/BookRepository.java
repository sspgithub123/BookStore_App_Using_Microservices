package com.bridgelabz.book.repository;

import com.bridgelabz.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "SELECT * from book WHERE book_name= :bookName", nativeQuery = true)
    Optional<Book> findByBookName(String bookName);

    @Query(value = "SELECT * from book WHERE book_id= :bookId", nativeQuery = true)
    Optional<Book> findByBookId(Integer bookId);

    @Query(value = "SELECT * from book ORDER BY price", nativeQuery = true)
    List<Book> sortBooksDesc();

    @Query(value = "SELECT * from book ORDER BY price DESC", nativeQuery = true)
    List<Book> sortBooksAsc();

}