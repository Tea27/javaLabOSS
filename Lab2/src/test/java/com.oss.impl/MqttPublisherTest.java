package com.oss.impl;

import com.oss.MqttPublisher.MqttPublisher;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MqttPublisherTest {
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor() throws MqttException {
        MqttClient mqttClient = new MqttClient("noUrl", "teaa");
    }

    @Test
    public void connect(){
        try {
            MqttClient mqttClient = new MqttClient("tcp://asdfgh:1883", "teaa");
            mqttClient.connect();
        } catch(MqttException exception){
            String actualMessage = exception.getMessage();
            String actualCause = String.valueOf(exception.getCause());
            String localizedMesage = exception.getLocalizedMessage();
            var reasonCode = exception.getReasonCode();

            assertEquals("MqttException", localizedMesage);
            assertEquals(0, reasonCode);
            assertEquals("MqttException", actualMessage);
            assertEquals("java.net.UnknownHostException: asdfgh", actualCause);
            assertEquals("MqttException (0) - java.net.UnknownHostException: asdfgh", exception.toString());
        }
    }


    @Test(expected = MqttException.class)
    public void disconnect() throws MqttException{
        MqttPublisher mqttClient = new MqttPublisher("tcp://localhost:1883", "mqttClient");
        mqttClient.connect();
        mqttClient.setMqttClient(new MqttClient("tcp://asdf:1883", "mqttClinet", new MemoryPersistence()));
        mqttClient.disconnect();
    }
}
