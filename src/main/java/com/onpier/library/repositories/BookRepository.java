package com.onpier.library.repositories;

import com.onpier.library.entites.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b " +
            "WHERE b.title NOT IN (" +
            "   SELECT br.bookName FROM Borrowing br " +
            "   WHERE CURRENT_DATE BETWEEN br.borrowedFrom AND br.borrowedTo" +
            ")")
    List<Book> findAvailableBooksForToday();
}
