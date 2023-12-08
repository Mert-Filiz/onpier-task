package com.onpier.library.service.impl;

import com.onpier.library.dto.BookResponseDTO;
import com.onpier.library.dto.BorrowingResponseDTO;
import com.onpier.library.entites.Book;
import com.onpier.library.entites.Borrowing;
import com.onpier.library.repositories.BookRepository;
import com.onpier.library.repositories.BorrowingRepository;
import com.onpier.library.service.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    @Override
    public List<BookResponseDTO> getAvailableBooks() {
        List<Book> bookList = bookRepository.findAvailableBooksForToday();
        return mapToDTOList(bookList);
    }

    private List<BookResponseDTO> mapToDTOList(List<Book> bookList){
        List<BookResponseDTO> bookDtoList = new ArrayList<>();
        if(!bookList.isEmpty()){
            for (Book book: bookList
            ) {
                BookResponseDTO responseDTO = new BookResponseDTO();
                if(book.getAuthor() != null && book.getTitle() != null && book.getGenre() != null && book.getPublisher() != null){
                    responseDTO.setAuthor(book.getAuthor());
                    responseDTO.setTitle(book.getTitle());
                    responseDTO.setGenre(book.getGenre());
                    responseDTO.setPublisher(book.getPublisher());
                    bookDtoList.add(responseDTO);
                }
            }
        }
        return bookDtoList;
    }
}
