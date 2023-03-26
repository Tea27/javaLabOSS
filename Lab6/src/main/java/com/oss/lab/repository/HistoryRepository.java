package com.oss.lab.repository;

import com.oss.lab.models.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query("SELECT h FROM History h WHERE h.month = ?1 AND h.year = ?2 AND h.device.id = ?3")
    History findByMonth(String month, int year, long id);
    @Query(value = "SELECT h FROM History h WHERE h.device.id = ?1 and h.year = ?2")
    List<History> findByYear(long id, int year);

    @Query(value = "SELECT Sum(h.energyConsumption) as total FROM History h WHERE h.year = ?1 AND h.device.id = ?2")
    Integer consumptionPerYear(int year, long id);

    @Query(value = "SELECT h FROM History h WHERE h.device.id = ?1")
    History findById(long id);
}
