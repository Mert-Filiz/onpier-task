package com.onpier.library;

import com.onpier.library.dto.BorrowingResponseDTO;
import com.onpier.library.entites.Borrowing;
import com.onpier.library.repositories.BorrowingRepository;
import com.onpier.library.service.impl.BorrowingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BorrowingServiceImplTest {

    @Mock
    private BorrowingRepository borrowingRepository;

    @InjectMocks
    private BorrowingServiceImpl borrowingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBorrowedBooksByUserInDate() {
        String name = "John Doe";
        LocalDate startDate = LocalDate.of(2021, 1, 1);
        LocalDate endDate = LocalDate.of(2021, 12, 31);

        Borrowing borrowing1 = new Borrowing();
        borrowing1.setBookName("Book 1");
        borrowing1.setBorrower(name);
        borrowing1.setBorrowedFrom(startDate);
        borrowing1.setBorrowedTo(endDate);

        Borrowing borrowing2 = new Borrowing();
        borrowing2.setBookName("Book 2");
        borrowing2.setBorrower(name);
        borrowing2.setBorrowedFrom(startDate);
        borrowing2.setBorrowedTo(endDate);

        List<Borrowing> mockBorrowingList = Arrays.asList(borrowing1, borrowing2);
        when(borrowingRepository.findBooksBorrowedByUserInDateRange(name, startDate, endDate)).thenReturn(mockBorrowingList);

        List<BorrowingResponseDTO> result = borrowingService.getBorrowedBooksByUserInDate(name, startDate, endDate);

        assertEquals(2, result.size());

        assertEquals("Book 1", result.get(0).getBookName());
        assertEquals(name, result.get(0).getBorrower());
        assertEquals(startDate, result.get(0).getBorrowedFrom());
        assertEquals(endDate, result.get(0).getBorrowedTo());

        assertEquals("Book 2", result.get(1).getBookName());
        assertEquals(name, result.get(1).getBorrower());
        assertEquals(startDate, result.get(1).getBorrowedFrom());
        assertEquals(endDate, result.get(1).getBorrowedTo());
    }
}

