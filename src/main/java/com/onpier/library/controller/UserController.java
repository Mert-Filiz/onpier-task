package com.onpier.library.controller;

import com.onpier.library.dto.UserResponseDTO;
import com.onpier.library.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/getUsersWhoBorrowedBefore")
    public List<UserResponseDTO> getUsersWhoBorrowedBefore() {
        return userService.getUsersWhoBorrowedBefore();
    }

    @GetMapping("/findNonTerminatedUsersWithNoBorrowings")
    public List<UserResponseDTO> findNonTerminatedUsersWithNoBorrowings() {
        return userService.findNonTerminatedUsersWithNoBorrowings();
    }
    @GetMapping("/findByBorrowedFrom")
    public List<UserResponseDTO> findUsersByBorrowedFrom(
            @RequestParam("borrowedFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate borrowedFrom) {
        return userService.findUsersByBorrowedFrom(borrowedFrom);
    }
}
