package com.oss.lab.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="client")
public class Client{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_fk", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "device_id_fk", referencedColumnName = "device_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Device device;

    public static Client deserialize(String filepath){
        Client client = new Client();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            InputStream file = new FileInputStream(filepath);
            client = objectMapper.readValue(file, Client.class);
        }catch(IOException e){
            e.printStackTrace();
        }

        return client;
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
}
