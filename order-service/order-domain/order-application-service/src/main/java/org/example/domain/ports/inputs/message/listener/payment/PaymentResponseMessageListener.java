package org.example.domain.ports.inputs.message.listener.payment;

import org.example.domain.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {

	void paymentComplete(PaymentResponse paymentResponse);
	void paymentCancelled(PaymentResponse paymentResponse);
}
