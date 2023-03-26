package com.oss.lab.service;

import com.oss.lab.models.Address;
import com.oss.lab.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getClients(){
        return new ArrayList<>(addressRepository.findAll());
    }

    public Address save(Address address){
        return addressRepository.save(address);
    }
    public Address update(Address address) {
        if(addressRepository.findById(address.getId()).isPresent()){
            var update = addressRepository.findById(address.getId()).get();
            return addressRepository.save(update);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (addressRepository.findById(id).isPresent() ){
            var address = addressRepository.findById(id).get();
            addressRepository.delete(address);
        }
        return false;
    }

}
