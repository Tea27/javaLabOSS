package com.oss.lab.service;

import com.oss.lab.models.Client;
import com.oss.lab.models.Device;
import com.oss.lab.models.History;
import com.oss.lab.repository.ClientRepository;
import com.oss.lab.repository.DeviceRepository;
import com.oss.lab.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Controller
public class HistoryService {
    @Autowired
    private HistoryRepository repository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public List<History> getHistory(){
        List<History> history = new ArrayList<History>();
        repository.findAll().forEach(measure -> history.add(measure));

        return history;
    }

    public History saveHistory(History history){
        Device device = deviceRepository.findByName(history.getDevice().getName());
        History newDev = null;
        if(device != null){
            newDev = repository.findByMonth(history.getMonth(), history.getYear(), device.getId());
            history.setDevice(device);
        }
        else{
            newDev = repository.findByMonth(history.getMonth(), history.getYear(), history.getDevice().getId());
        }

        if(newDev != null && repository.findById(device.getId()) != null ){
            System.out.println("AAAAAAAAAAAAAAAAA");
            newDev.setId(device.getId());
            return repository.save(newDev);
        }



        return repository.save(history);
    }

    public boolean delete(Long id) {
        if (repository.findById(id).isPresent() ){
            var history = repository.findById(id).get();
            repository.delete(history);
            return true;
        }
        return false;
    }

    public HashMap<String, Integer> getByYear(Long id, int year){
        HashMap<String, Integer> map = new HashMap<>();

        var histories = repository.findByYear(id, year);
        histories.forEach(history ->{
            map.put(history.getMonth(), history.getEnergyConsumption());
        }  );
        return map;
    }

    public History getByMonth(Long id, int year, String month){
        return repository.findByMonth(month, year, id);
    }

    public HashMap<Integer, Integer> consumption(Long id, int year){
        var consumption = repository.consumptionPerYear(year, id);

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(year, consumption);

        return map;
    }

    public Page<History> findPaginated(Pageable pageable) {
        List<History> allHistory = repository.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<History> list;
        if (allHistory.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, allHistory.size());
            list = allHistory.subList(startItem, toIndex);
        }

        Page<History> bookPage
                = new PageImpl<History>(list, PageRequest.of(currentPage, pageSize), allHistory.size());

        return bookPage;
    }

    public List<Client> clientByHistory(){
        List<History> allHistory = repository.findAll();
        List<Client> clients = new ArrayList();
        allHistory.forEach(history -> {
            long id = history.getDevice().getId();
            clients.add(clientRepository.findByDevice(id));

        });

        return clients;
    }

}
