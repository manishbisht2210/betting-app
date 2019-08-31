package com.outliers.paymentintegrationservice.repository;

import com.paypal.api.payments.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends CrudRepository<Payment, String> {

  Payment findByTxnId(String txnid);
}
