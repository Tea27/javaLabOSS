package com.oss.MqttPublisher;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

@Setter
@Getter
public class MqttPublisher {
        private MqttClient mqttClient;

        public MqttPublisher(String broker, String clientId){
            MemoryPersistence persistence = new MemoryPersistence();

            try {
                mqttClient = new MqttClient(broker, clientId, persistence);
            } catch(Exception exception) {
                exception.printStackTrace();
            }
        }

        public void connect(){
            try {
                MqttConnectOptions connOpts = new MqttConnectOptions();
                connOpts.setCleanSession(true);
                System.out.println("Connecting to broker: ");

                mqttClient.connect(connOpts);
                System.out.println("Connected");

            }catch(MqttException exception){
                printExceptionMessage(exception);
                exception.printStackTrace();
                //String error = exception.getMessage();
                //System.out.println("EXCEPTION string\n" + error);
                Runtime.getRuntime().exit(1);
            }
        }

        public void disconnect(){
                try {
                    mqttClient.disconnect();

                    System.out.println("Disconnected");
                }catch(MqttException exception){
                    printExceptionMessage(exception);
                    exception.printStackTrace();
                    Runtime.getRuntime().exit(1);
                }
            }
        public void publish(String content, String topic){
                int qos = 2;
                try {
                    System.out.println("\nPublishing message: " + content);

                    MqttMessage message = new MqttMessage(content.getBytes());
                    message.setQos(qos);
                    mqttClient.publish(topic, message);

                    System.out.println("Message published");
                }catch(MqttException exception){
                    printExceptionMessage(exception);
                    exception.printStackTrace();
                    Runtime.getRuntime().exit(1);
                }
            }


        public void printExceptionMessage(MqttException message) {
            System.out.println("reason: " + message.getReasonCode()
                    + "\nmessage: " + message.getMessage()
                    + "\nlocalized: " + message.getLocalizedMessage()
                    + "\ncause: " + message.getCause()
                    + "\nexception: " + message);
        }


}
