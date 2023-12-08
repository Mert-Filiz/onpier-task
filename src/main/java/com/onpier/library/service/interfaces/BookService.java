package com.onpier.library.service.interfaces;

import com.onpier.library.dto.BookResponseDTO;
import com.onpier.library.dto.BorrowingResponseDTO;
import com.onpier.library.entites.Book;
import com.onpier.library.entites.Borrowing;

import java.time.LocalDate;
import java.util.List;

public interface BookService {
    List<BookResponseDTO> getAvailableBooks();
}
