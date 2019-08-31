package com.bettingservice.model.voucher;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by manish.bisht on 3/8/2019.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "voucher")
public class Voucher {
  @Id
  private String id;
  private String voucherCode;
  private VoucherStatusEnum status;
  private String userId;
  private LocalDate usedDate;
  private LocalDate expireDate;
  private double amount;
}
