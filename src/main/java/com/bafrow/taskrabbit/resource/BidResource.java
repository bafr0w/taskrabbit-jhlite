package com.bafrow.taskrabbit.resource;

/**
 * @Author BaFr0w
 */
import com.bafrow.taskrabbit.service.BidService;
import com.bafrow.taskrabbit.service.dto.BidDTO;
import com.bafrow.taskrabbit.service.dto.BidReqDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Bids")
@RequestMapping("/api/v1/bids")
public class BidResource {
  private final BidService bidService;

  public BidResource(BidService bidService) {
    this.bidService = bidService;
  }

  @PostMapping
  @Operation(summary = "Add a new Bid")
  @ApiResponse(description = "Bid added to the pool", responseCode = "201")
  public ResponseEntity<BidDTO> createBid(@Validated @RequestBody BidReqDTO bidDTO) {
    BidDTO createdBid = bidService.createBid(bidDTO);
    return new ResponseEntity<>(createdBid, HttpStatus.CREATED);
  }

  @GetMapping("/{bidId}")
  @Operation(summary = "Find one Bid from the pool")
  @ApiResponse(description = "Bid found from the pool", responseCode = "200")
  public ResponseEntity<BidDTO> getBidById(@PathVariable UUID bidId) {
    BidDTO bidDTO = bidService.getBidById(bidId);
    if (bidDTO != null) {
      return new ResponseEntity<>(bidDTO, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping
  @Operation(summary = "Find all Bids from the pool")
  @ApiResponse(description = "List of all Bids", responseCode = "200")
  public ResponseEntity<List<BidDTO>> getAllBids() {
    List<BidDTO> bids = bidService.getAllBids();
    return new ResponseEntity<>(bids, HttpStatus.OK);
  }

  @PutMapping("/{bidId}")
  @Operation(summary = "update one Bid in the pool")
  @ApiResponse(description = "Bid updated into the pool", responseCode = "200")
  public ResponseEntity<BidDTO> updateBid(@PathVariable UUID bidId, @Validated @RequestBody BidReqDTO updatedBidDTO) {
    BidDTO updatedBid = bidService.updateBid(bidId, updatedBidDTO);
    if (updatedBid != null) {
      return new ResponseEntity<>(updatedBid, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{bidId}")
  @Operation(summary = "Delete one Bid from the pool")
  @ApiResponse(description = "Bid deleted from the pool", responseCode = "200")
  public ResponseEntity<Void> deleteBid(@PathVariable UUID bidId) {
    bidService.deleteBid(bidId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

