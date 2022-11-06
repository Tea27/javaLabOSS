package com.oss;

import com.oss.MeterType.MeterType;
import com.oss.MqttPublisher.MqttPublisher;
import com.oss.WaterMeter.WaterMeter;
import com.oss.config.GeneralConfig;


public class Main {
    public static void main(String[] args) {
        //LAB2
//        MqttPublisher publisher = new MqttPublisher("tcp://localhost:1883", "mqttClient");
//
//        WaterMeter waterMeterFromFile = new WaterMeter("watermeter.json");
//        WaterMeter waterMeter = new WaterMeter();
//
//        waterMeter.serialize("watermeter.json");
//
//        WaterMeter newWaterMeter = new WaterMeter();
//        WaterMeter serialized = newWaterMeter.deserialize("watermeter.json");
//
//        WaterMeter userChosenWaterMeter = waterMeter.newWaterMeter( "Trenutna temperatura vode");
//
//        publisher.connect();
//
//        userChosenWaterMeter.publish(publisher);
//        publisher.disconnect();

        //LAB3
        GeneralConfig appConfig = new GeneralConfig("config.json");

        MqttPublisher publisher = new MqttPublisher(appConfig.getBroker(), appConfig.getClientID());

        WaterMeter waterMeter = appConfig.getWaterMeter();
        publisher.connect();

        waterMeter.publish(publisher);

        publisher.disconnect();
    }
}