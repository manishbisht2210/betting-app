package com.bettingservice.service;

import com.bettingservice.model.betting.Betting;

import java.util.List;

/**
 * Created by manish.bisht on 3/8/2019.
 */
public interface BettingService {

  Betting placeBet(Betting betting) throws Exception;

  List<Betting> getBetsForUser(String userId);
}
