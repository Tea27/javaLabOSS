package com.oss.lab.service;

import com.oss.lab.models.Client;
import com.oss.lab.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getClients(){
        return new ArrayList<>(clientRepository.findAll());
    }

    public Client saveClient(Client client){
        return clientRepository.save(client);
    }

    public Client update(Client client) {
        if (clientRepository.findById(client.getId()).isPresent()){
            return clientRepository.save(client);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (clientRepository.findById(id).isPresent() ){
            var client = clientRepository.findById(id).get();
            clientRepository.delete(client);
            return true;
        }
        return false;
    }

    public Client getByDevice(Long id){
        return clientRepository.findByDevice(id);
    }

}
