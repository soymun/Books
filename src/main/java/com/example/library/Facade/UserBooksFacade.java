package com.example.library.Facade;

import com.example.library.Dto.Book.BookDto;
import com.example.library.Dto.User.UserDto;
import com.example.library.Dto.User.UserUpdateDto;
import com.example.library.Dto.UserBook.UserBookCreateDto;
import com.example.library.Exeption.BadValues;
import com.example.library.Service.Imp.BasketServiceImp;
import com.example.library.Service.Imp.BookServiceImp;
import com.example.library.Service.Imp.UserBooksServiceImpl;
import com.example.library.Service.Imp.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserBooksFacade {

    private final BasketServiceImp basketServiceImp;

    private final BookServiceImp bookServiceImp;

    private final UserServiceImp userServiceImp;

    private final UserBooksServiceImpl userBooksService;

    @Transactional
    @BatchSize(size = 5)
    public ResponseEntity<?> saveUserBooks(UserBookCreateDto userBookCreateDto){
        BookDto bookDto = bookServiceImp.getBookById(userBookCreateDto.getBookId());
        UserDto userDto = userServiceImp.getUserById(userBookCreateDto.getUserId());
        if(userDto.getSummary() < bookDto.getPrice()){
            throw new BadValues("Цена книги больше, чем у вас");
        }

        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setId(userDto.getId());
        userUpdateDto.setSummary(userDto.getSummary() - bookDto.getPrice());
        userServiceImp.updateUser(userUpdateDto);

        basketServiceImp.deleteBasket(userBookCreateDto.getBasketId());
        userBooksService.saveUserBooks(userBookCreateDto);
        return ResponseEntity.status(201).build();
    }

    public ResponseEntity<?> getUserBooksByUserId(Long userId){
        return ResponseEntity.ok(userBooksService.getUserBooksByUserId(userId));
    }
}
