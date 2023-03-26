package com.oss.lab.controller;

import com.oss.lab.exception.MyException;
import com.oss.lab.models.Client;
import com.oss.lab.models.Device;
import com.oss.lab.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @PutMapping("devices/{id}")
    public ResponseEntity<Device> updateDevice(@RequestBody Device device, @PathVariable("id") long id){
        return ResponseEntity.ok(deviceService.updateDevice(device, id));
    }

    @DeleteMapping("devices/{id}")
    public ResponseEntity<Long> deleteDevice(@PathVariable Long id) throws MyException {

        var isRemoved = deviceService.delete(id);

        if (!isRemoved) {
            throw new MyException("Device doesn't exist !!");
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
