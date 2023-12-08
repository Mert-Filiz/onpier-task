package com.onpier.library.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BookResponseDTO {
    private String title;

    private String author;

    private String genre;

    private String publisher;
}
