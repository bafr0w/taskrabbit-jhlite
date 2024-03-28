package com.bafrow.taskrabbit.repository;

import com.bafrow.taskrabbit.domain.Tasker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @Author BaFr0w
 */
@Repository
public interface TaskerRepository extends JpaRepository<Tasker, UUID>, JpaSpecificationExecutor<Tasker> {
}
