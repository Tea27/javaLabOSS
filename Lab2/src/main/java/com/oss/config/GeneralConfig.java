package com.oss.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oss.WaterMeter.WaterMeter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@Data
@NoArgsConstructor
public class GeneralConfig {
    private String broker;
    private String clientID;
    private WaterMeter waterMeter;

    public GeneralConfig(String filepath){
        broker = deserialize(filepath).getBroker();
        clientID = deserialize(filepath).getClientID();
        waterMeter = deserialize(filepath).getWaterMeter();
    }

    public GeneralConfig(String broker, String clientID, WaterMeter waterMeter){
        this.broker = broker;
        this.clientID = clientID;
        this.waterMeter = waterMeter;
    }


    public void serialize(String filepath){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        File file = new File(filepath);

        try {
            mapper.writeValue(file, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GeneralConfig deserialize(String filepath){
        GeneralConfig generalConfig = new GeneralConfig();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            InputStream file = new FileInputStream(filepath);
            generalConfig = objectMapper.readValue(file, GeneralConfig.class);
        }catch(IOException e){
            e.printStackTrace();
        }

        return generalConfig;
    }


}
