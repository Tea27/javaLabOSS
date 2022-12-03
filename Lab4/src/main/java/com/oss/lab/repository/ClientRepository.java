package com.oss.lab.repository;

import com.oss.lab.models.Address;
import com.oss.lab.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Address> {
}
