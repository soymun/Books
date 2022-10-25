package com.example.library.Controllers;

import com.example.library.Dto.Basket.BasketListDto;
import com.example.library.Dto.Basket.BasketSaveDto;
import com.example.library.Dto.Response.FactoryResponse.FactoryResponse;
import com.example.library.Dto.Response.ResponseDto;
import com.example.library.Exeption.BadValues;
import com.example.library.Service.Imp.BasketServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sibrary")
@Slf4j
public class BasketController {

    private final BasketServiceImp basketServiceImp;

    private final FactoryResponse factoryResponse;

    @Autowired
    public BasketController(BasketServiceImp basketServiceImp, FactoryResponse factoryResponse) {
        this.basketServiceImp = basketServiceImp;
        this.factoryResponse = factoryResponse;
    }

    @GetMapping("/basket/all/{id}")
    public ResponseEntity<?> getBasket(@PathVariable Long id){
        if(id == null){
            log.debug("Id with basket null");
            throw new BadValues("Basket not found");
        }
        log.info("Get basket in controller");
        BasketListDto bask = new BasketListDto();
        bask.setBookDtoGetAlls(basketServiceImp.getAllByUserId(id));
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse(bask);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/basket")
    public ResponseEntity<?> saveBasket(@RequestBody BasketSaveDto basketSaveDto){
        log.info("Save basket");
        basketServiceImp.saveBasket(basketSaveDto);
        ResponseDto responseDto = factoryResponse.getResponse("Save basket suggest");
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/basket/{id}")
    public ResponseEntity<?> deleteBasket(@PathVariable Long id){
        log.info("Basket delete");
        basketServiceImp.deleteBasket(id);
        ResponseDto responseDto = factoryResponse.getResponse("Delete basket suggest");
        return ResponseEntity.ok(responseDto);
    }

}
