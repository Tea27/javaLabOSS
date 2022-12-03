package com.oss.lab.controller;

import com.oss.lab.models.Device;
import com.oss.lab.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/devices")
    public ResponseEntity<List<Device>> getDevices() {
        return ResponseEntity.ok( deviceService.getDevices());
    }

    @PostMapping("/devices")
    public ResponseEntity<Device> saveDevice(@RequestBody Device device){
        return ResponseEntity.ok(deviceService.saveDevice(device));
    }

}
