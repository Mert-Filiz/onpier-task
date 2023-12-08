package com.onpier.library.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@RequiredArgsConstructor
public class BorrowingResponseDTO {
    private String borrower;

    private String bookName;

    private LocalDate borrowedFrom;
    private LocalDate borrowedTo;

}
