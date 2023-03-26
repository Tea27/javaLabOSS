package com.oss.lab.repository;


import com.oss.lab.models.Device;
import com.oss.lab.models.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    @Query(value = "SELECT d FROM Device d WHERE d.name = ?1")
    Device findByName(String name);
}
