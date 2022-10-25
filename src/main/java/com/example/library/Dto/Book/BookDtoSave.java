package com.example.library.Dto.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDtoSave {

    private Long id;

    private Long authorId;

    private String name;

    private String about;

    private Long price;
}
