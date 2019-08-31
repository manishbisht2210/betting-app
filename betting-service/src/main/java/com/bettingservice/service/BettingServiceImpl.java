package com.bettingservice.service;

import com.bettingservice.model.transaction.PaypalTransaction;
import com.bettingservice.model.transaction.TransactionModeEnum;
import com.bettingservice.model.transaction.TransactionStatusEnum;
import com.bettingservice.repository.BettingRepository;
import com.bettingservice.repository.VoucherRepository;
import com.bettingservice.exception.InvalidVoucherException;
import com.bettingservice.model.betting.Betting;
import com.bettingservice.model.voucher.Voucher;
import com.bettingservice.model.voucher.VoucherStatusEnum;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by manish.bisht on 3/8/2019.
 */
@Service
public class BettingServiceImpl implements BettingService {

  @Autowired
  private BettingRepository bettingRepository;
  @Autowired
  private VoucherRepository voucherRepository;

  @Autowired
  private RestTemplate restTemplate;

  @Value("${betting.paypal.uri}")
  private String paypalUri;

  @Override
  public Betting placeBet(Betting betting) throws Exception{
   try {
     if (TransactionModeEnum.VOUCHER.equals(betting.getTransaction().getTransactionMode())) {
       try {
         validateAndSaveVoucher(betting.getTransaction().getVoucherCode(), betting.getUserId(),
             betting.getTotalAmount());
         betting.getTransaction().setTransactionStatus(TransactionStatusEnum.SUCCESS);
       } catch (Exception e) {
         betting.getTransaction().setTransactionStatus(TransactionStatusEnum.FAILED);
         throw e;
       }

     } else if (TransactionModeEnum.PAYPAL.equals(betting.getTransaction().getTransactionMode())) {
       //call transaction api
       PaypalTransaction paypalTransaction = confirmTranction(betting.getTransaction()
           .getPaymentId(), betting.getTransaction().getBuyerId());
       betting.getTransaction().setPaypalTransaction(paypalTransaction);
       String transactionStatus = null == paypalTransaction ? null :
           paypalTransaction.getStatus().toUpperCase();
       System.out.print("Transaction Status:--" + transactionStatus);
       if (null == transactionStatus || "".equals(transactionStatus)) {
         betting.getTransaction().setTransactionStatus(TransactionStatusEnum.FAILED);
       } else {
         betting.getTransaction().setTransactionStatus(TransactionStatusEnum.valueOf(transactionStatus));
       }
     }
   } catch ( Exception e) {
     bettingRepository.save(betting);
     throw e;
   }

   return bettingRepository.save(betting);

  }

  @Override
  public List<Betting> getBetsForUser(String userId) {
    return bettingRepository.findAllByUserId(userId);
  }

  private void validateAndSaveVoucher(String voucherCode, String userId, double totalAmount)
      throws InvalidVoucherException {
    Voucher voucher = voucherRepository.findByVoucherCode(voucherCode);
    if (null == voucher) {
      throw new InvalidVoucherException("Voucher not found");
    }
    if(VoucherStatusEnum.USED.equals(voucher.getStatus())) {
      throw new InvalidVoucherException("Voucher is used");
    }
    if(VoucherStatusEnum.EXPIRED.equals(voucher.getStatus())) {
      throw new InvalidVoucherException("Voucher is expired");
    }
    if(voucher.getExpireDate().isBefore(LocalDate.now())) {
      voucher.setStatus(VoucherStatusEnum.EXPIRED);
      throw new InvalidVoucherException("Voucher is expired");
    }
    if(totalAmount > voucher.getAmount()) {
      throw new InvalidVoucherException("Voucher doesn't have enough balance");
    }
    voucher.setStatus(VoucherStatusEnum.USED);
    voucher.setUsedDate(LocalDate.now());
    voucher.setUserId(userId);

    voucherRepository.save(voucher);
  }

  private PaypalTransaction confirmTranction(String paymentId, String payerId ) {
    try {
    return restTemplate.postForObject(paypalUri, null, PaypalTransaction.class, paymentId, payerId);
    } catch (Exception e) {
      //Ignore
    }
    return null;
  }

}
