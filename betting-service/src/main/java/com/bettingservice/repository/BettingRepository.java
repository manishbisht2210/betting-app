package com.bettingservice.repository;

import com.bettingservice.model.betting.Betting;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by manish.bisht on 3/8/2019.
 */
@Repository
public interface BettingRepository extends MongoRepository<Betting, String> {

  List<Betting> findAllByUserId(String userId);
}
