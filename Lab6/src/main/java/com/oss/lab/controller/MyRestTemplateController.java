package com.oss.lab.controller;

import com.oss.lab.models.Client;
import com.oss.lab.models.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class MyRestTemplateController {
    @Autowired
    private RestTemplate restTemplate;
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/history1")
    public String getDummyData(Model model){
        History[] history = restTemplate.getForObject("http://localhost:9090/history", History[].class);
        model.addAttribute("history", history);
        return "template";
    }

}
