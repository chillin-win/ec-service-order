package com.winnie.ecserviceorder.repository;

import com.winnie.ecserviceorder.entity.ScheduledJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledJobRepository extends JpaRepository<ScheduledJob, String> {
}
