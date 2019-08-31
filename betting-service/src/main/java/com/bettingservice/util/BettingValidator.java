package com.bettingservice.util;

import com.bettingservice.exception.InvalidBettingException;
import com.bettingservice.model.transaction.TransactionModeEnum;
import com.bettingservice.model.transaction.TransactionStatusEnum;
import com.bettingservice.model.betting.Betting;
import com.bettingservice.model.betting.BettingItem;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Created by manish.bisht on 3/8/2019.
 */
@Component
public class BettingValidator {

  public void validateAndSetBet(Betting betting) throws InvalidBettingException {
    if(null == betting.getUserId() || "".equals(betting.getUserId())) {
      throw new InvalidBettingException("User id is empty");
    }
    if(betting.getBettingItems().isEmpty()) {
      throw new InvalidBettingException("Betting items are empty");
    }
    if(null == betting.getUnitValue() || "".equals(betting.getUnitValue())) {
      betting.setUnitValue("USD");
    }
    Double totalAmount = validateAndSetBetItems(betting.getBettingItems());
    if(totalAmount != betting.getTotalAmount()) {
      betting.setTotalAmount(totalAmount);
    }
    validateAndSetTransaction(betting);
  }

  private void validateAndSetTransaction(Betting betting) throws InvalidBettingException {
    betting.getTransaction().setTransactionStatus(TransactionStatusEnum.PENDING);
    betting.getTransaction().setAmount(betting.getTotalAmount());
    betting.getTransaction().setAmountUnit(betting.getUnitValue());
    if(TransactionModeEnum.VOUCHER.equals(betting.getTransaction().getTransactionMode())) {
      if (null == betting.getTransaction().getVoucherCode() || "".equals(betting.getTransaction().getVoucherCode())) {
        throw new InvalidBettingException("Voucher code is empty");
      }
    }
  }

  private Double validateAndSetBetItems(List<BettingItem> bettingItems) throws InvalidBettingException {
    double totalAmount = 0;
    for(BettingItem bettingItem : bettingItems) {
      if(0 >= bettingItem.getAmount()) {
        throw new InvalidBettingException("Betting amount is invalid");
      }
      if(null == bettingItem.getPlayerId() || "".equals(bettingItem.getPlayerId())) {
        throw new InvalidBettingException("Player id is empty");
      }
      if(null == bettingItem.getPlayerType() || "".equals(bettingItem.getPlayerType()) || null == bettingItem.getSport() || "".equals(bettingItem.getSport())) {
        bettingItem.setSport("Horse Racing");
        bettingItem.setPlayerType("Horse");
      }
      totalAmount += bettingItem.getAmount();
    }
    return totalAmount;
  }
}
