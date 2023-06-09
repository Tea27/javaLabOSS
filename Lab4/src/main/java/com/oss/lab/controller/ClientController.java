package com.oss.lab.controller;

import com.oss.lab.models.Client;
import com.oss.lab.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("")
    public ResponseEntity<List<Client>> getClients(){
        return ResponseEntity.ok(clientService.getClients());
    }

    @PostMapping("")
    public ResponseEntity<Client> saveClient(Client client){
        return ResponseEntity.ok(clientService.saveClient(client));
    }


}
