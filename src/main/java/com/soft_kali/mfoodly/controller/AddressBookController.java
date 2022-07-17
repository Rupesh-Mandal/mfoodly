package com.soft_kali.mfoodly.controller;

import com.soft_kali.mfoodly.dto.user.AddressBookDto;
import com.soft_kali.mfoodly.model.AddressBookResponse;
import com.soft_kali.mfoodly.model.ApiResponse;
import com.soft_kali.mfoodly.service.AddressBookService;
import com.soft_kali.mfoodly.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/address-book")
public class AddressBookController {

    @Autowired
    AddressBookService addressBookService;


    @PostMapping("cityId/{cityId}/add-new-address")
    ResponseEntity<ApiResponse> addNewAddress(@RequestBody AddressBookDto addressBookDto, @PathVariable int cityId){
        ApiResponse apiResponse=addressBookService.addNewAddress(addressBookDto,cityId);
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }

    @GetMapping("/get-all-address")
    ResponseEntity<AddressBookResponse> getAllAddressByUser(
            @RequestParam(value = "pageSize") Optional<Integer> pageSize,
            @RequestParam(value = "pageNumber") Optional<Integer> pageNumber,
            @RequestParam(value = "sortBy") Optional<String> sortBy,
            @RequestParam(value = "sortDir") Optional<Sort.Direction> sort
    ){

        Pageable pageable = PageRequest.of(pageNumber.orElse(AppConstants.PAGE_NUMBBER),
                pageSize.orElse(AppConstants.PAGE_SIZE), sort.orElse(AppConstants.sort),
                sortBy.orElse(AppConstants.SORT_BY_FOR_ADDRESS_BOOK));

        AddressBookResponse addressBookResponse=addressBookService.getAllAddressByUser(pageable);
        return new ResponseEntity<>(addressBookResponse, HttpStatus.OK);

    }
}
