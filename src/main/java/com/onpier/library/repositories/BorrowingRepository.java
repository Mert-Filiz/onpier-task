package com.onpier.library.repositories;
import com.onpier.library.entites.Book;
import com.onpier.library.entites.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    @Query("SELECT b FROM Borrowing b WHERE b.borrower = :name AND b.borrowedFrom >= :startDate AND b.borrowedTo <= :endDate")
    List<Borrowing> findBooksBorrowedByUserInDateRange(
            @Param("name") String name,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}

