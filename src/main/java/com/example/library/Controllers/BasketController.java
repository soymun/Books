package com.example.library.Controllers;

import com.example.library.Dto.Basket.BasketSaveDto;
import com.example.library.Exeption.BadValues;
import com.example.library.Service.Imp.BasketServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library")
@Slf4j
@RequiredArgsConstructor
public class BasketController {

    private final BasketServiceImp basketServiceImp;

    @GetMapping("/basket/all/{id}")
    public ResponseEntity<?> getBasketByUserId(@PathVariable Long id){

        if(id == null){
            throw new BadValues("Basket not found");
        }

        return ResponseEntity.ok(basketServiceImp.getBasketsByUserId(id));
    }

    @GetMapping("/basket/{id}")
    public ResponseEntity<?> getBasketById(@PathVariable Long id){

        if(id == null){
            throw new BadValues("Basket not found");
        }

        return ResponseEntity.ok(basketServiceImp.getBasketById(id));
    }

    @PostMapping("/basket")
    public ResponseEntity<?> saveBasket(@RequestBody BasketSaveDto basketSaveDto){
        log.info("Save basket");
        basketServiceImp.saveBasket(basketSaveDto);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/basket/{id}")
    public ResponseEntity<?> deleteBasket(@PathVariable Long id){
        log.info("Basket delete");
        basketServiceImp.deleteBasket(id);
        return ResponseEntity.noContent().build();
    }
}
