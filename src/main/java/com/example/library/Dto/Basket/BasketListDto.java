package com.example.library.Dto.Basket;

import com.example.library.Dto.Book.BookDtoGetAll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketListDto {

    private List<BookDtoGetAll> bookDtoGetAlls;
}
