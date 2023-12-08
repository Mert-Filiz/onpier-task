package com.onpier.library.service.impl;
import com.onpier.library.dto.BorrowingResponseDTO;
import com.onpier.library.entites.Borrowing;
import com.onpier.library.repositories.BorrowingRepository;
import com.onpier.library.service.interfaces.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {
    private final BorrowingRepository borrowingRepository;
    @Override
    public List<BorrowingResponseDTO> getBorrowedBooksByUserInDate(String name, LocalDate startDate, LocalDate endDate) {
        List<Borrowing> borrowingList= borrowingRepository.findBooksBorrowedByUserInDateRange(name,startDate,endDate);
        return mapToDTOList(borrowingList);
    }

    private List<BorrowingResponseDTO> mapToDTOList(List<Borrowing> borrowingList){
        List<BorrowingResponseDTO> borrowingResponseDTOList = new ArrayList<>();
        if(!borrowingList.isEmpty()){
            for (Borrowing borrowing: borrowingList
            ) {
                BorrowingResponseDTO responseDTO = new BorrowingResponseDTO();
                if(borrowing.getBookName() != null && borrowing.getBorrower() != null && borrowing.getBorrowedFrom() != null && borrowing.getBorrowedTo() != null)
                {
                    responseDTO.setBookName(borrowing.getBookName());
                    responseDTO.setBorrower(borrowing.getBorrower());
                    responseDTO.setBorrowedFrom(borrowing.getBorrowedFrom());
                    responseDTO.setBorrowedTo(borrowing.getBorrowedTo());
                    borrowingResponseDTOList.add(responseDTO);
                }
            }
        }
        return borrowingResponseDTOList;
    }
}
