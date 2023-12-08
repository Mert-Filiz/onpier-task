package com.onpier.library.service.impl;
import com.onpier.library.dto.UserResponseDTO;
import com.onpier.library.entites.User;
import com.onpier.library.repositories.UserRepository;
import com.onpier.library.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserResponseDTO> getUsersWhoBorrowedBefore() {
        List<User> userList = userRepository.findUserWhoBorrowedBefore();
        return mapToDtoList(userList);
    }

    @Override
    public List<UserResponseDTO> findNonTerminatedUsersWithNoBorrowings() {
        List<User> userList = userRepository.findNonTerminatedUsersWithNoBorrowings();
        return mapToDtoList(userList);
    }
    @Override
    public List<UserResponseDTO> findUsersByBorrowedFrom(LocalDate borrowedFrom) {
        List<User> userList = userRepository.findUsersByBorrowedFrom(borrowedFrom);
        return mapToDtoList(userList);
    }

    private List<UserResponseDTO> mapToDtoList(List<User> userList){
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        if(!userList.isEmpty()){
            for (User user: userList
            ) {
                UserResponseDTO responseDTO = new UserResponseDTO();
                if(user.getFirstName() != null && user.getName() != null && user.getGender() != null && user.getMemberSince() != null){
                    responseDTO.setFirstName(user.getFirstName());
                    responseDTO.setSurName(user.getName());
                    responseDTO.setGender(user.getGender());
                    responseDTO.setMemberSince(user.getMemberSince());
                    responseDTO.setMemberTill(user.getMemberTill());
                    userResponseDTOS.add(responseDTO);
                }
            }
        }
        return userResponseDTOS;
    }
}

