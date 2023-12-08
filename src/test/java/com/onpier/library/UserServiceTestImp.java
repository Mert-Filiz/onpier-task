package com.onpier.library;

import com.onpier.library.dto.UserResponseDTO;
import com.onpier.library.entites.User;
import com.onpier.library.repositories.UserRepository;
import com.onpier.library.service.impl.UserServiceImpl;
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

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUsersWhoBorrowedBefore() {
        // Mock data
        User user1 = new User();
        user1.setFirstName("John");
        user1.setName("Doe");
        user1.setGender("Male");
        user1.setMemberSince(LocalDate.of(2020, 1, 1));

        User user2 = new User();
        user2.setFirstName("Jane");
        user2.setName("Doe");
        user2.setGender("Female");
        user2.setMemberSince(LocalDate.of(2019, 1, 1));

        List<User> mockUserList = Arrays.asList(user1, user2);

        when(userRepository.findUserWhoBorrowedBefore()).thenReturn(mockUserList);

        // Call the service method
        List<UserResponseDTO> result = userService.getUsersWhoBorrowedBefore();

        assertEquals(2, result.size());

        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Doe", result.get(0).getSurName());
        assertEquals("Male", result.get(0).getGender());
        assertEquals(LocalDate.of(2020, 1, 1), result.get(0).getMemberSince());

        assertEquals("Jane", result.get(1).getFirstName());
        assertEquals("Doe", result.get(1).getSurName());
        assertEquals("Female", result.get(1).getGender());
        assertEquals(LocalDate.of(2019, 1, 1), result.get(1).getMemberSince());
    }

    @Test
    void findNonTerminatedUsersWithNoBorrowings() {
        User user1 = new User();
        user1.setFirstName("John");
        user1.setName("Doe");
        user1.setGender("Male");
        user1.setMemberSince(LocalDate.of(2020, 1, 1));

        User user2 = new User();
        user2.setFirstName("Jane");
        user2.setName("Doe");
        user2.setGender("Female");
        user2.setMemberSince(LocalDate.of(2019, 1, 1));

        List<User> mockUserList = Arrays.asList(user1, user2);

        when(userRepository.findNonTerminatedUsersWithNoBorrowings()).thenReturn(mockUserList);

        List<UserResponseDTO> result = userService.findNonTerminatedUsersWithNoBorrowings();

        assertEquals(2, result.size());

        // You can add more specific assertions based on your actual logic
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Doe", result.get(0).getSurName());
        assertEquals("Male", result.get(0).getGender());
        assertEquals(LocalDate.of(2020, 1, 1), result.get(0).getMemberSince());

        assertEquals("Jane", result.get(1).getFirstName());
        assertEquals("Doe", result.get(1).getSurName());
        assertEquals("Female", result.get(1).getGender());
        assertEquals(LocalDate.of(2019, 1, 1), result.get(1).getMemberSince());
    }

    @Test
    void findUsersByBorrowedFrom() {
        // Mock data
        LocalDate borrowedFrom = LocalDate.of(2021, 1, 1);

        User user1 = new User();
        user1.setFirstName("John");
        user1.setName("Doe");
        user1.setGender("Male");
        user1.setMemberSince(LocalDate.of(2020, 1, 1));

        User user2 = new User();
        user2.setFirstName("Jane");
        user2.setName("Doe");
        user2.setGender("Female");
        user2.setMemberSince(LocalDate.of(2019, 1, 1));

        List<User> mockUserList = Arrays.asList(user1, user2);

        when(userRepository.findUsersByBorrowedFrom(borrowedFrom)).thenReturn(mockUserList);
        List<UserResponseDTO> result = userService.findUsersByBorrowedFrom(borrowedFrom);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Doe", result.get(0).getSurName());
        assertEquals("Male", result.get(0).getGender());
        assertEquals(LocalDate.of(2020, 1, 1), result.get(0).getMemberSince());

        assertEquals("Jane", result.get(1).getFirstName());
        assertEquals("Doe", result.get(1).getSurName());
    }
}
