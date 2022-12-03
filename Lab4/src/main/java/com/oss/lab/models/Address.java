package com.oss.lab.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
    @Column(name = "device_id_fk")
    private long id;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;
}
