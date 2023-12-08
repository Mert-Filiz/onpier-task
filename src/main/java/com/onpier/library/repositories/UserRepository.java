package com.onpier.library.repositories;

import com.onpier.library.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE CONCAT(u.name, ',', u.firstName) IN (SELECT b.borrower FROM Borrowing b WHERE b.borrower IS NOT NULL)")
    List<User> findUserWhoBorrowedBefore();
    @Query("SELECT u FROM User u WHERE u.memberTill IS NULL AND CONCAT(u.name, ',', u.firstName) NOT IN (SELECT b.borrower FROM Borrowing b WHERE b.borrower IS NOT NULL)")
    List<User> findNonTerminatedUsersWithNoBorrowings();
    @Query("SELECT u FROM User u WHERE CONCAT(u.name, ',', u.firstName) IN (SELECT b.borrower FROM Borrowing b WHERE b.borrowedFrom = :borrowedFrom)")
    List<User> findUsersByBorrowedFrom(@Param("borrowedFrom") LocalDate borrowedFrom);
}
