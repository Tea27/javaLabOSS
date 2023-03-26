package com.oss.lab.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "history_id")

    private long id;
    @Column(name = "c_month")
    private String month;

    @Column(name = "c_year")
    private int year;

    @Column(name = "energy_consumption")
    private int energyConsumption;

    @ManyToOne()
    @JoinColumn(name = "device_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Device device;

    public History(String month, int year, int energyConsumption, Device device){
        this.month = month;
        this.year = year;
        this.energyConsumption = energyConsumption;
        this.device = device;
    }
}
