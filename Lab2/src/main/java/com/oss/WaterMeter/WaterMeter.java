package com.oss.WaterMeter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oss.MeterType.MeterType;
import com.oss.MqttPublisher.MqttPublisher;
import lombok.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class WaterMeter{
    List<MeterType> meterTypes;

    public WaterMeter(){
        meterTypes = new ArrayList(Arrays.asList(
                new MeterType("Trenutna temperatura vode", "int16", 10, -3266.8f, 3266.8f, "C", 0),
                new MeterType("Trenutni tlak vode", "uint16", 1000, 0f, 65.336f, "Bar", 0),
                new MeterType("Potrošnja u zadnjih 1 min, 10 min, 1 sat, 1 dan", "uint16", 0, 0f, 65336f, "litra", 0),
                new MeterType("Potrošnja u zadnjih 1 tjedan, 1 mjesec, 1 godinu", "uint16", 10, 0f, 6533.6f, "m3", 0))
        );
    }

    public WaterMeter(String filepath){
        this.meterTypes = this.deserialize(filepath).meterTypes;
    }

    public WaterMeter newWaterMeter(String ...topics){
        WaterMeter waterMeter = new WaterMeter();
        List<MeterType> newMeterTypes = new ArrayList<>();

        for (String topic : topics){
            meterTypes.stream()
                    .filter(item -> item.isQualified(topic))
                    .forEach(newMeterTypes::add);
        }

        waterMeter.meterTypes = newMeterTypes;
        return waterMeter;
    }

    public void publish(MqttPublisher publisher){
        List<MeterType> wMeterValueSet = meterTypes.stream().map(MeterType::setRandomCurrentVal).toList();
        wMeterValueSet.forEach(meter -> publisher.publish(meter.toString(), meter.getTopic()));

    }

    public void serialize(String filepath) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filepath);
        try {
            // Serialize Java object info JSON file.
            mapper.writeValue(file, this.meterTypes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WaterMeter deserialize(String filepath){
        ObjectMapper objectMapper = new ObjectMapper();
        WaterMeter waterMeter = new WaterMeter();
        try {
            InputStream file = new FileInputStream(filepath);
            waterMeter.meterTypes = objectMapper.readValue(file, new TypeReference<List<MeterType>>() {
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return waterMeter;
    }

    public String toString(){
        return this.meterTypes.toString();
    }
}
