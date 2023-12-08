package com.onpier.library;

import com.onpier.library.dto.BookResponseDTO;
import com.onpier.library.entites.Book;
import com.onpier.library.repositories.BookRepository;
import com.onpier.library.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAvailableBooks() {
        Book book1 = new Book();
        book1.setTitle("Book 1");
        book1.setAuthor("Author 1");
        book1.setGenre("Genre 1");
        book1.setPublisher("Publisher 1");
        Book book2 = new Book();
        book2.setTitle("Book 2");
        book2.setAuthor("Author 2");
        book2.setGenre("Genre 2");
        book2.setPublisher("Publisher 2");
        List<Book> mockBookList = Arrays.asList(book1, book2);
        when(bookRepository.findAvailableBooksForToday()).thenReturn(mockBookList);
        List<BookResponseDTO> result = bookService.getAvailableBooks();
        assertEquals(2, result.size());
        assertEquals("Book 1", result.get(0).getTitle());
        assertEquals("Author 1", result.get(0).getAuthor());
        assertEquals("Genre 1", result.get(0).getGenre());
        assertEquals("Publisher 1", result.get(0).getPublisher());
        assertEquals("Book 2", result.get(1).getTitle());
        assertEquals("Author 2", result.get(1).getAuthor());
        assertEquals("Genre 2", result.get(1).getGenre());
        assertEquals("Publisher 2", result.get(1).getPublisher());
    }
}

