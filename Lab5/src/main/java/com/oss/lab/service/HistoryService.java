package com.oss.lab.service;

import com.oss.lab.models.History;
import com.oss.lab.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class HistoryService {
    @Autowired
    private HistoryRepository repository;
    public List<History> getHistory(){
        List<History> history = new ArrayList<History>();
        repository.findAll().forEach(measure -> history.add(measure));

        return history;
    }

    public History saveHistory(History history){
        History newDev = repository.findByMonth(history.getMonth(), history.getYear(), history.getDevice().getId());
        if(newDev != null){
            return repository.save(newDev);
        }
        return repository.save(history);
    }

    public boolean delete(Long id) {
        if (repository.findById(id).isPresent() ){
            var history = repository.findById(id).get();
            repository.delete(history);
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

}
