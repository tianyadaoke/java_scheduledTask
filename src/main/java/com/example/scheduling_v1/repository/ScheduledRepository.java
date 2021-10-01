package com.example.scheduling_v1.repository;

import com.example.scheduling_v1.model.ScheduledTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledRepository extends JpaRepository<ScheduledTask,Integer> {
}
