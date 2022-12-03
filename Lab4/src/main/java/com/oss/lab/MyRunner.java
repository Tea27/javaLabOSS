package com.oss.lab;

import com.oss.lab.models.AppConfig;
import com.oss.lab.models.Client;
import com.oss.lab.repository.ClientRepository;
import com.oss.lab.repository.DeviceRepository;
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

    public MyRunner(ClientRepository clientRepository, DeviceRepository deviceRepository) {
        this.clientRepository = clientRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        AppConfig appConfig = AppConfig.deserialize("appCnfg.json");

        List<Client> clients = appConfig.getClients();

        clients.forEach(clientic -> {
            deviceRepository.save(clientic.getDevice());
            clientRepository.save(clientic);
        });

    }

}
