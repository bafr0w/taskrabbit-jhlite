package com.bafrow.taskrabbit.service;

/**
 * @Author BaFr0w
 */
import com.bafrow.taskrabbit.domain.Bid;
import com.bafrow.taskrabbit.domain.Task;
import com.bafrow.taskrabbit.domain.Tasker;
import com.bafrow.taskrabbit.repository.BidRepository;
import com.bafrow.taskrabbit.repository.TaskRepository;
import com.bafrow.taskrabbit.repository.TaskerRepository;
import com.bafrow.taskrabbit.service.dto.BidDTO;
import com.bafrow.taskrabbit.service.dto.BidReqDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BidService {
  private final BidRepository bidRepository;
  private final TaskRepository taskRepository;
  private final TaskerRepository taskerRepository;

  public BidService(BidRepository bidRepository, TaskRepository taskRepository, TaskerRepository taskerRepository) {
    this.bidRepository = bidRepository;
    this.taskRepository = taskRepository;
    this.taskerRepository = taskerRepository;
  }

  @Transactional
  public BidDTO createBid(BidReqDTO bidDTO) {
    Optional<Task> taskOptional = taskRepository.findById(bidDTO.taskId());
    Optional<Tasker> taskerOptional = taskerRepository.findById(bidDTO.taskerId());

    if (taskOptional.isPresent() && taskerOptional.isPresent()) {
      Task task = taskOptional.get();
      Tasker tasker = taskerOptional.get();

      Bid bid = new Bid(
        UUID.randomUUID(),
        task,
        tasker,
        bidDTO.amount(),
        LocalDateTime.now(),
        LocalDateTime.now()
      );

      Bid createdBid = bidRepository.save(bid);

      return convertToDTO(createdBid);
    } else {
      throw new IllegalArgumentException("Invalid task or tasker ID");
    }
  }

  public BidDTO getBidById(UUID bidId) {
    Optional<Bid> bidOptional = bidRepository.findById(bidId);
    return bidOptional.map(this::convertToDTO).orElse(null);
  }

  public List<BidDTO> getAllBids() {
    List<Bid> bids = bidRepository.findAll();
    return bids.stream()
      .map(this::convertToDTO)
      .collect(Collectors.toList());
  }

  @Transactional
  public BidDTO updateBid(UUID bidId, BidReqDTO updatedBidDTO) {
    Optional<Bid> bidOptional = bidRepository.findById(bidId);
    if (bidOptional.isPresent()) {
      Bid bid = bidOptional.get();
      bid.setAmount(updatedBidDTO.amount());
      bid.setUpdatedAt(LocalDateTime.now());

      Bid updatedBid = bidRepository.save(bid);

      return convertToDTO(updatedBid);
    } else {
      return null;
    }
  }

  @Transactional
  public void deleteBid(UUID bidId) {
    bidRepository.deleteById(bidId);
  }

  private BidDTO convertToDTO(Bid bid) {
    return new BidDTO(
      bid.getId(),
      bid.getTask().getId(),
      bid.getTasker().getId(),
      bid.getAmount(),
      bid.getCreatedAt(),
      bid.getUpdatedAt()
    );
  }
}

