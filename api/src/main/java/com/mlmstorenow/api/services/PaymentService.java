package com.mlmstorenow.api.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;

@Service
public class PaymentService {

	// creates static braintreegateway object
	public static BraintreeGateway getBraintreeGateway() {

		Properties prop = new Properties();
		try {
			prop.load(new FileReader("src\\main\\resources\\Config.properties"));
			BraintreeGateway gateway = new BraintreeGateway(Environment.SANDBOX, prop.getProperty("merchant_id"),
					prop.getProperty("public_key"), prop.getProperty("private_key"));
			return gateway;
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}

		return null;
	}

	// pass clientToken to your front-end
	public String generateClientToken(String id) {
		ClientTokenRequest clientTokenRequest = new ClientTokenRequest().customerId(id);
		String clientToken = getBraintreeGateway().clientToken().generate(clientTokenRequest);

		return clientToken;
	}

	// Transaction processor
	public String processTransaction(String chargeAmount, String deviceData) {

		TransactionRequest request = new TransactionRequest().amount(new BigDecimal(chargeAmount))
				.paymentMethodNonce("fake-valid-nonce").deviceData(deviceData).options().submitForSettlement(true).done();

		Result<Transaction> transactionResult = getBraintreeGateway().transaction().sale(request);
		Transaction transaction;

		if (transactionResult.isSuccess()) {
			transaction = transactionResult.getTarget();

			return transaction.getId();

		}
		return null;

	}
}
