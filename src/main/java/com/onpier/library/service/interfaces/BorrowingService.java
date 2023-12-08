package com.onpier.library.service.interfaces;

import com.onpier.library.dto.BorrowingResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface BorrowingService {
    List<BorrowingResponseDTO> getBorrowedBooksByUserInDate(String name, LocalDate startDate, LocalDate endDate);
}
