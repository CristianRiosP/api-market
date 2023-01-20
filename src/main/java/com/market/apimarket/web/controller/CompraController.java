package com.market.apimarket.web.controller;

import com.market.apimarket.domain.Purchase;
import com.market.apimarket.domain.service.PurchaseService;
import com.market.apimarket.persistence.CompraRepository;
import com.market.apimarket.persistence.crud.ProductoCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
public class CompraController {
    @Autowired
    private PurchaseService purchaseService;



    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }
    @GetMapping("client/{id}")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("id") String clientId){
        return purchaseService.getByClient(clientId).map(purchases -> new ResponseEntity<>(purchases,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase){
        return new ResponseEntity<>(purchaseService.save(purchase),HttpStatus.CREATED);
    }

}
