package com.oss.lab.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppConfig{
    private List<History> measurements;
    private List<Client> clients;
    public static AppConfig deserialize(String filepath){
        AppConfig generalConfig = new AppConfig();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            InputStream file = new FileInputStream(filepath);
            generalConfig.clients = objectMapper.readValue(file, new TypeReference<List<Client>>() {
            });
        }catch(IOException e){
            e.printStackTrace();
        }

        return generalConfig;
    }

    public void serialize(String filepath){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        File file = new File(filepath);

        try {
            mapper.writeValue(file, this.clients);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deserializeMeasurment(String filepath){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            InputStream file = new FileInputStream(filepath);
            this.measurements = objectMapper.readValue(file, new TypeReference<List<History>>() {
            });
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}
