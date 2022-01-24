package com.mlmstorenow.api.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mlmstorenow.api.config.ConfigProperties;
import com.mlmstorenow.api.models.Product;
import com.mlmstorenow.api.models.User;
import com.mlmstorenow.api.services.JwtService;
import com.mlmstorenow.api.services.PaymentService;
import com.mlmstorenow.api.services.ShipmentService;
import com.mlmstorenow.api.services.UserService;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.model.Address;

@RestController
public class OrderController {

	@Autowired
	PaymentService pserv;
	@Autowired
	ShipmentService shipserv;
	@Autowired
	UserService userv;
	@Autowired
	JwtService jws;

	@SuppressWarnings("unchecked")
	@PostMapping("/order/submit")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<?> submitOrder(@RequestBody() Map<String, Object> paymentMap
	// ,@RequestHeader("authorization") String auth
	) {
		System.out.println("in order submit");
		System.out.println(paymentMap.keySet());

		Map<String, String> pmap = (Map<String, String>) paymentMap.get("payment");

		String transact = pserv.processTransaction(pmap.get("charge_amount"), pmap.get("nonce"),
				pmap.get("deviceData"));

		System.out.println(pmap.get("nonce"));
		if (transact != null) {

			try {
				Optional<?> u = userv.getUserByUsername(JwtService.getUname().get(0));
				User user = (User) u.get();
				user.getAddresses().add(Address.create((HashMap<String, Object>) paymentMap.get("shipment"),
						ConfigProperties.getConfigProp("se_api_key")));
				Address addy = user.getAddresses().get(0);

				Map<String, String> prodMap = (Map<String, String>) paymentMap.get("items");

				Product prod = new Product();
				for (String s : prodMap.keySet()) {
					prod.setProductName(s);
					prod.setAmount(Integer.parseInt(prodMap.get(s)));
					prod.setUserid(user.getId());

					user.getOrderHistory().add(prod);
				}

				return ResponseEntity.ok()
						.body(shipserv.getShippingLabel((String) addy.getName(), (String) addy.getEmail(), addy));
			} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException e) {
				e.printStackTrace();
			} catch (ClassCastException | NullPointerException | IndexOutOfBoundsException te) {

				User user = new User();
				try {
					user.getAddresses().add(Address.create((HashMap<String, Object>) paymentMap.get("shipment"),
							ConfigProperties.getConfigProp("se_api_key")));
				} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException e) {
					e.printStackTrace();
				}
				Address addy = user.getAddresses().get(0);
				System.out.println(paymentMap.get("items"));
				ArrayList<LinkedHashMap<String, String>> prodMap = (ArrayList<LinkedHashMap<String, String>>) paymentMap
						.get("items");

				System.out.println(prodMap.get(0).keySet());
				Product prod = new Product();

				prodMap.stream().forEach(t -> {
					for (String s : t.keySet()) {
						prod.setProductName(s);
						prod.setAmount(Integer.parseInt(t.get(s)));
						prod.setUserid(user.getId());

						user.getOrderHistory().add(prod);
					}
				});

				return ResponseEntity.ok()
						.body(shipserv.getShippingLabel((String) addy.getName(), (String) addy.getEmail(), addy));

			}

		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
}
