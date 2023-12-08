package com.onpier.library.service.interfaces;

import com.onpier.library.dto.UserResponseDTO;
import java.time.LocalDate;
import java.util.List;

public interface UserService {
    List<UserResponseDTO> getUsersWhoBorrowedBefore();
    List<UserResponseDTO> findNonTerminatedUsersWithNoBorrowings();
    List<UserResponseDTO> findUsersByBorrowedFrom(LocalDate borrowedFrom);
}
