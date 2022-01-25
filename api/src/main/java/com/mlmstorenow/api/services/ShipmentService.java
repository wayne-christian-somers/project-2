package com.mlmstorenow.api.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mlmstorenow.api.config.ConfigProperties;
import com.shippo.Shippo;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.model.Address;
import com.shippo.model.Rate;
import com.shippo.model.Shipment;
import com.shippo.model.Transaction;

@Service
public class ShipmentService {

	public Map<String, Object> getShippingLabel(String user, String email, Address addy) {
		try {

			Shippo.setApiKey(ConfigProperties.getConfigProp("se_api_key"));
			// Shippo.setDEBUG(true);

			Address toaddress = Address.validate(addy.getObjectId());
			Map<String, Object> fromAddressMap = new HashMap<String, Object>();
			fromAddressMap.put("name", "John Clark");
			fromAddressMap.put("company", "shopmlmstore");
			fromAddressMap.put("street1", "11730 Plaza America Drive");
			fromAddressMap.put("city", "Reston");
			fromAddressMap.put("state", "VA");
			fromAddressMap.put("zip", "20190");
			fromAddressMap.put("country", "US");
			fromAddressMap.put("phone", "+1 222 439 9612");
			fromAddressMap.put("email", "mlmstorenow@gmail.com");
			fromAddressMap.put("IsComplete", true);

			Address fromaddress = Address.create(fromAddressMap);

			toaddress.setIsComplete(true);
			fromaddress = Address.validate(fromaddress.getObjectId());
			fromaddress.setIsComplete(true);

			Map<String, Object> parcelMap = new HashMap<String, Object>();
			parcelMap.put("length", "5");
			parcelMap.put("width", "5");
			parcelMap.put("height", "5");
			parcelMap.put("distance_unit", "in");
			parcelMap.put("weight", "2");
			parcelMap.put("mass_unit", "lb");
			List<Map<String, Object>> parcels = new ArrayList<Map<String, Object>>();
			parcels.add(parcelMap);

			Map<String, Object> shipmentMap = new HashMap<String, Object>();
			shipmentMap.put("address_to", toaddress);
			shipmentMap.put("address_from", fromaddress);
			shipmentMap.put("parcels", parcels);
			shipmentMap.put("async", false);

			Shipment shipment = Shipment.create(shipmentMap);

			// select shipping rate according to your business logic
			// we select the first rate in this example
			List<Rate> rates = shipment.getRates();
			Rate rate = rates.get(0);

			Map<String, Object> transParams = new HashMap<String, Object>();
			transParams.put("rate", rate.getObjectId());
			transParams.put("async", false);
			transParams.put("wasTest", true);
			Transaction transaction = Transaction.create(transParams);

			Map<String, Object> label = new HashMap<String, Object>();
			if (transaction.getStatus().equals("SUCCESS")) {
				label.put("label_url", transaction.getLabelUrl());
				label.put("tracking_number", transaction.getTrackingNumber());
			} else {
				label.put("error", transaction.getMessages());
			}
			return label;
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException e) {
			e.printStackTrace();
		}

		return null;
	}
}
