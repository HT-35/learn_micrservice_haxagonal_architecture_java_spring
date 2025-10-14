package org.example.domain.ports;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.dto.message.PaymentResponse;
import org.example.domain.ports.inputs.message.listener.payment.PaymentResponseMessageListener;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Slf4j
@Service
@Validated
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {

    @Override
    public void paymentComplete(PaymentResponse paymentResponse) {

    }

    @Override
    public void paymentCancelled(PaymentResponse paymentResponse) {

    }
}
