package com.bettingservice.controller;

import com.bettingservice.model.betting.Betting;
import com.bettingservice.service.BettingService;
import com.bettingservice.util.BettingValidator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by manish.bisht on 3/8/2019.
 */
@RestController
@RequestMapping("/v1/bet")
public class BettingController {

  @Autowired
  private BettingValidator bettingValidator;

  @Autowired
  private BettingService bettingService;

  @PostMapping("/place")
  public ResponseEntity<Betting> placeBet(@RequestBody Betting betting) throws Exception {

    bettingValidator.validateAndSetBet(betting);
    return new ResponseEntity<>(bettingService.placeBet(betting), HttpStatus.OK);
  }

  @GetMapping("/get/{userId}")
  public ResponseEntity<List<Betting>> getAllBets(@PathVariable("userId") String userId) {
    try {
      return new ResponseEntity<>(bettingService.getBetsForUser(userId),HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
