package com.onpier.library.data;

import com.onpier.library.entites.Book;
import com.onpier.library.entites.Borrowing;
import com.onpier.library.entites.User;
import com.onpier.library.repositories.BookRepository;
import com.onpier.library.repositories.BorrowingRepository;
import com.onpier.library.repositories.UserRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CsvReaderConfig {
    @Value("${csv.books.path}")
    private String bookFilePath;
    @Value("${csv.users.path}")
    private String userFilePath;
    @Value("${csv.borrowings.path}")
    private String borrowedFilePAth;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    private final BorrowingRepository borrowedRepository;


    @PostConstruct
    private void readCsvOnStartup() {
        readBookCsv();
        readUserCsv();
        readBorrowed();
    }

    private void readBookCsv(){
        try (CSVReader csvReader = new CSVReader(new FileReader(bookFilePath))) {
            List<String[]> records = csvReader.readAll();
            for (int i = 1; i < records.size(); i++) {
                String[] data = records.get(i);
                if (Arrays.stream(data).allMatch(value -> value == null || value.trim().isEmpty())) {
                    continue;
                }
                Book entity = new Book();
                entity.setTitle(data[0]);
                entity.setAuthor(data[1]);
                entity.setGenre(data[2]);
                entity.setPublisher(data[3]);
                bookRepository.save(entity);
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    private void readUserCsv(){
        try (CSVReader csvReader = new CSVReader(new FileReader(userFilePath))) {
            List<String[]> records = csvReader.readAll();
            for (int i = 1; i < records.size(); i++) {
                String[] data = records.get(i);
                if (Arrays.stream(data).allMatch(value -> value == null || value.trim().isEmpty())) {
                    continue;
                }
                User entity = new User();
                entity.setName(data[0]);
                entity.setFirstName(data[1]);
                entity.setMemberSince(parseDate(data[2]));
                entity.setMemberTill(parseDate(data[3]));
                entity.setGender(data[4]);
                userRepository.save(entity);
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    private void readBorrowed(){
        try (CSVReader csvReader = new CSVReader(new FileReader(borrowedFilePAth))) {
            List<String[]> records = csvReader.readAll();
            for (int i = 1; i < records.size(); i++) {
                String[] data = records.get(i);
                if (Arrays.stream(data).allMatch(value -> value == null || value.trim().isEmpty())) {
                    continue;
                }
                Borrowing entity = new Borrowing();
                entity.setBorrower(data[0]);
                entity.setBookName(data[1]);
                entity.setBorrowedFrom(parseDate(data[2]));
                entity.setBorrowedTo(parseDate(data[3]));
                borrowedRepository.save(entity);
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    private LocalDate parseDate(String dateString) {
        if (dateString != null && !dateString.trim().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            return LocalDate.parse(dateString, formatter);
        } else {
            return null;
        }
    }
}
