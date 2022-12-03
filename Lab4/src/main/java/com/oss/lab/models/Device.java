package com.oss.lab.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "device_id")
    private long id;

    @Column(name = "c_month")
    private String month;

    @Column(name = "c_year")
    private int year;

    @Column(name = "energy_consumption")
    private int energyConsumption;

}
