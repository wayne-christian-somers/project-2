package com.mlmstorenow.api.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.mlmstorenow.api.config.ConfigProperties;
import com.mlmstorenow.api.models.User;

@Service
public class PaymentService {

	
	// creates static braintreegateway object
	public static BraintreeGateway getBraintreeGateway() {

			BraintreeGateway gateway = new BraintreeGateway(
					Environment.SANDBOX, 
					ConfigProperties.getConfigProp("mechant_id"),
					ConfigProperties.getConfigProp("public_key"), 
					ConfigProperties.getConfigProp("private_key"));
			return gateway;
	}

	// pass clientToken to your front-end
	public String generateClientToken(String customerId) {
		ClientTokenRequest clientTokenRequest = new ClientTokenRequest().customerId(customerId);
		String clientToken = getBraintreeGateway().clientToken().generate(clientTokenRequest);

		return clientToken;
	}

	// Transaction processor
	public String processTransaction(String chargeAmount, String nonce, String deviceData) {

		BigDecimal bd = BigDecimal.valueOf(Integer.parseInt(chargeAmount));
		TransactionRequest request = new TransactionRequest().amount(bd)
				.paymentMethodNonce(nonce).deviceData(deviceData).options().submitForSettlement(true).done();

		Result<Transaction> transactionResult = getBraintreeGateway().transaction().sale(request);
		Transaction transaction;

		if (transactionResult.isSuccess()) {
			transaction = transactionResult.getTarget();

			return transaction.getId();

		}
		return null;

	}

	public String generateCustomerId(User user) {
		CustomerRequest request = new CustomerRequest().firstName("Mark").lastName("Jones").email(user.getEmail());
		Result<Customer> result = getBraintreeGateway().customer().create(request);

		if (result.isSuccess()) {
			return result.getTarget().getId();
		}
		return null;
	}
}
