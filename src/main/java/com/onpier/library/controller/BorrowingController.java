package com.onpier.library.controller;

import com.onpier.library.dto.BorrowingResponseDTO;
import com.onpier.library.service.interfaces.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/borrowings")
@RequiredArgsConstructor
public class BorrowingController {
    private final BorrowingService borrowingService;
    @GetMapping("/getBorrowedBooksByUserInDate")
    public List<BorrowingResponseDTO> getBorrowedBooksByUserInDate(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        String name = lastName.concat(",").concat(firstName);
        return borrowingService.getBorrowedBooksByUserInDate(name, startDate, endDate);
    }
}
