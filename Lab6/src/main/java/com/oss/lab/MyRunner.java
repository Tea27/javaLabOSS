package com.oss.lab;

import com.oss.lab.models.*;
import com.oss.lab.repository.AddressRepository;
import com.oss.lab.repository.ClientRepository;
import com.oss.lab.repository.DeviceRepository;
import com.oss.lab.repository.HistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    private final ClientRepository clientRepository;
    private final DeviceRepository deviceRepository;
    private final HistoryRepository historyRepository;
    private final AddressRepository addressRepository;

    public MyRunner(ClientRepository clientRepository, DeviceRepository deviceRepository, HistoryRepository historyRepository, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.deviceRepository = deviceRepository;
        this.historyRepository = historyRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        AppConfig appConfig = AppConfig.deserialize("confg.json");
        appConfig.deserializeMeasurment("measurments.json");
        List<History> histories = appConfig.getMeasurements();
        histories.forEach(history -> {
            //
            if(!deviceRepository.findById(history.getDevice().getId()).isPresent()){
                deviceRepository.save(history.getDevice());
            }
            historyRepository.save(history);
        });
        appConfig.getClients().forEach(client -> {
            addressRepository.save(client.getAddress());
            clientRepository.save(client);
        });


    }
}
