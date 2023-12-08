package com.onpier.library.controller;

import com.onpier.library.dto.BookResponseDTO;
import com.onpier.library.dto.BorrowingResponseDTO;
import com.onpier.library.entites.Borrowing;
import com.onpier.library.service.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/getAvailableBooks")
    public List<BookResponseDTO> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }
}
