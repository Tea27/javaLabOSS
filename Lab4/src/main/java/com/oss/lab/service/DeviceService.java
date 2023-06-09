package com.oss.lab.service;

import com.oss.lab.models.Device;
import com.oss.lab.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository repository;

    public List<Device> getDevices(){
        List<Device> devices = new ArrayList<Device>();
        repository.findAll().forEach(device -> devices.add(device));

        return devices;
    }

    public Device saveDevice(Device device){
        return repository.save(device);
    }

    public Device updateDevice(Device device, long id){
        if(repository.findById(id).isPresent()){
            Device newDevice = repository.findById(id).get();
//            newDevice.setMonth(device.getMonth());
//            newDevice.setYear(device.getYear());
//            newDevice.setEnergyConsumption(device.getEnergyConsumption());
            device.setId(newDevice.getId());
            repository.save(device);
        }
        return null;
    }
}
