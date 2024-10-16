package org.example.newdiary.repository;

import org.example.newdiary.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    @Query("SELECT a FROM Activity a WHERE a.timeStamp BETWEEN :startDate AND :endDate ORDER BY a.timeStamp ASC")
    List<Activity> findActivitiesWithinMonths(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
