package com.bettingservice.repository;

import com.bettingservice.model.voucher.Voucher;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by manish.bisht on 3/8/2019.
 */
@Repository
public interface VoucherRepository extends MongoRepository<Voucher, String> {
  Voucher findByVoucherCode(String voucherCode);
}
