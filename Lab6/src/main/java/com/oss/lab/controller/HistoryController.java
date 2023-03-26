package com.oss.lab.controller;

import com.oss.lab.exception.MyException;
import com.oss.lab.models.History;
import com.oss.lab.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping("")
    public ResponseEntity<List<History>> getDevices() {
        return ResponseEntity.ok( historyService.getHistory());
    }
    @PostMapping("")
    public ResponseEntity<History> saveDevice(@RequestBody History history){
        return ResponseEntity.ok(historyService.saveHistory(history));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteDevice(@PathVariable Long id) throws MyException {
        var isRemoved = historyService.delete(id);

        if (!isRemoved) {
            throw new MyException("Measurement doesn't exist !!");
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/{id}/{year}")
    public HashMap<String, Integer> getDeviceByYear(@PathVariable Long id, @PathVariable Integer year) {
        return historyService.getByYear(id, year);
    }

    @GetMapping("/{id}/{year}/{month}")
    public ResponseEntity<History> getDeviceByMonth(@PathVariable Long id, @PathVariable int year, @PathVariable String month) {
        return ResponseEntity.ok( historyService.getByMonth(id, year, month));
    }

    @GetMapping("/consumption/{id}/{year}")
    public HashMap<Integer, Integer> consumption(@PathVariable Long id, @PathVariable Integer year) {
//        System.out.println("POZIV CONTROLERU");
        return historyService.consumption(id, year);
    }
}
