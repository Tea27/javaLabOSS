package com.oss.lab.repository;

import com.oss.lab.models.Client;
import com.oss.lab.models.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c WHERE c.device.id = ?1")
    Client findByDevice(long id);
}
