package com.oss.MeterType;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeterType {
    private String topic;
    private String dataType;
    private int factor;
    private float lowerBoundary;
    private float upperBoundary;
    private String measuringUnit;
    private int currentValue;

    @Override
    public String toString(){
        return  topic + " " + currentValue + " " + measuringUnit + "\n";
    }
    public MeterType setRandomCurrentVal()
    {
        Random random = new Random();
        currentValue = (int) (lowerBoundary*factor + random.nextFloat() * (upperBoundary - lowerBoundary*factor));
        return this;
    }

    public void serialize(String filepath) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filepath);
        try {
            // Serialize Java object info JSON file.
            mapper.writeValue(file, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MeterType deserialize(String filepath){
        ObjectMapper objectMapper = new ObjectMapper();
        MeterType meter = null;
        try {
            InputStream file = new FileInputStream(filepath);
            meter = objectMapper.readValue(file, MeterType.class);
        }catch(IOException e){
            e.printStackTrace();
        }
        return meter;
    }

    public boolean isQualified(String str) {return str == this.topic;}

}
