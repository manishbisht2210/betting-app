package com.outliers.paymentintegrationservice.service;

import com.outliers.paymentintegrationservice.model.PaymentCallback;
import com.paypal.api.payments.PaymentDetail;

public interface PaymentService {

  PaymentDetail proceedPayment(PaymentDetail paymentDetail);

  com.outliers.paymentintegrationservice.model.PaymentDetail proceedPayment(
      com.outliers.paymentintegrationservice.model.PaymentDetail paymentDetail);

  String payuCallback(PaymentCallback paymentResponse);
}
