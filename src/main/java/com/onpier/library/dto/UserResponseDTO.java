package com.onpier.library.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@RequiredArgsConstructor
public class UserResponseDTO {
    private String firstName;
    private String surName;

    private LocalDate memberSince;

    private LocalDate memberTill;

    private String gender;
}
