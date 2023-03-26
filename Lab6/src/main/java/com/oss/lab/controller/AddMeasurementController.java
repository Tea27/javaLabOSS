package com.oss.lab.controller;

import com.oss.lab.models.Client;
import com.oss.lab.models.History;
import com.oss.lab.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/measurement")
public class AddMeasurementController {
    @Autowired
    private HistoryService historyService;

    @GetMapping("/add")
    public String getMeasurement(History history) {
        return "template"; //view
    }

    @PostMapping("/post")
    public String postMeasurement(@ModelAttribute("history") History history) {
        historyService.saveHistory(history);
        return "created"; //view
    }

    @GetMapping( "/pagination")
    public String pagination(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<History> clientPage = historyService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        List<Client> client = historyService.clientByHistory();

        model.addAttribute("clientPage", clientPage);
        model.addAttribute("clients", client);

        int totalPages = clientPage.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "pagination";
    }
}
