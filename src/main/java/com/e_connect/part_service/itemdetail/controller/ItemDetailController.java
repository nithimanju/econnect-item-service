package com.e_connect.part_service.itemdetail.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.e_connect.part_service.itemdetail.service.ItemDetailService;
import com.e_connect.part_service.model.ItemDetail;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ItemDetailController {
    
    private final ItemDetailService itemDetailService;

    @GetMapping(value = "/part-detail/{id}")
    public ResponseEntity<ItemDetail> get(@PathVariable String id){
        
        return new ResponseEntity<>(itemDetailService.get(id), HttpStatus.OK);
    } 
}
