package com.oss.lab.controller;

import com.oss.lab.exception.MyException;
import com.oss.lab.models.Address;
import com.oss.lab.models.Client;
import com.oss.lab.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("")
    public ResponseEntity<List<Address>> getAddress(){
        return ResponseEntity.ok(addressService.getClients());
    }

    @PostMapping("")
    public ResponseEntity<Address> saveAddress(@RequestBody Address address){
        return ResponseEntity.ok(addressService.save(address));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody Address address){
        return ResponseEntity.ok(addressService.update(address));
    }
//
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteAddress(@PathVariable Long id) throws MyException {

        var isRemoved = addressService.delete(id);

        if (!isRemoved) {
            throw new MyException("Address doesn't exist !!");
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }


}
