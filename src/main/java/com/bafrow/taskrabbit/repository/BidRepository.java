package com.bafrow.taskrabbit.repository;

import com.bafrow.taskrabbit.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @Author BaFr0w
 */
@Repository
public interface BidRepository extends JpaRepository<Bid, UUID>, JpaSpecificationExecutor<Bid> {
  List<Bid> findAllByTaskerId(UUID taskerId);
}
