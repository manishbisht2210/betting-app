package com.bettingservice.model.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by manish.bisht on 3/8/2019.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction implements Serializable {

  private String time = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date());
  private TransactionStatusEnum transactionStatus;
  private TransactionModeEnum transactionMode;
  private Double amount;
  private String amountUnit;
  private String buyerId;
  private String paymentId;
  private String voucherCode;
  private PaypalTransaction paypalTransaction;
}