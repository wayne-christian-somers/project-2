package com.mlmstorenow.api.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;

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
import com.mlmstorenow.api.services.EmailService;
import com.mlmstorenow.api.services.JwtService;
import com.mlmstorenow.api.services.PaymentService;
import com.mlmstorenow.api.services.ShipmentService;
import com.mlmstorenow.api.services.UserService;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.model.Address;

import freemarker.template.TemplateException;

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
	@Autowired
	EmailService email;

	@SuppressWarnings("unchecked")
	@PostMapping("/order/submit")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<?> submitOrder(@RequestBody() Map<String, Object> paymentMap
	// ,@RequestHeader("authorization") String auth
	) {
		Map<String, String> pmap = (Map<String, String>) paymentMap.get("payment");

		String transact = pserv.processTransaction(pmap.get("charge_amount"), pmap.get("nonce"),
				pmap.get("deviceData"));

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
					prod.setAmount(Double.parseDouble(prodMap.get(s)));
					prod.setUserid(user.getId());

					user.getOrderHistory().add(prod);
				}

				ArrayList<String> products = new ArrayList<String>();

				Map<String, Object> shpmt = shipserv.getShippingLabel((String) addy.getName(), (String) addy.getEmail(),
						addy);

				Map<String, Object> templateModel = new HashMap<String, Object>();
				templateModel.put("name", addy.getName());
				templateModel.put("invoice_num", new Random().nextInt(234567890));
				templateModel.put("date", java.util.Calendar.getInstance().getTime().toString());
				templateModel.put("products", products);
				templateModel.put("total", pmap.get("charge_amount"));
				templateModel.put("tracking", shpmt.get("tracking_number"));

				try {
					email.sendMessageUsingFreemarkerTemplate(addy.getEmail().toString(),
							"Thank You For Shopping MLMStore", templateModel);
				} catch (IOException | TemplateException | MessagingException e) {
					e.printStackTrace();
				}
				return ResponseEntity.ok().body(shpmt);
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

				Map<String, Object> shpmt = shipserv.getShippingLabel((String) addy.getName(), (String) addy.getEmail(),
						addy);

				ArrayList<Product> products = new ArrayList<Product>();
				ArrayList<LinkedHashMap<String, Object>> prodMap = (ArrayList<LinkedHashMap<String, Object>>) paymentMap
						.get("items");

				for (int i = 0; i < prodMap.size(); i++) {
					Product prod = new Product();

					for (String s : prodMap.get(i).keySet()) {
						if (s.equals("name")) {
							prod.setProductName(prodMap.get(i).get(s).toString());
						}

						if (s.equals("amount")) {
							prod.setAmount(Double.parseDouble(prodMap.get(i).get(s).toString()));
						}
					}

					products.add(prod);
				}

				Map<String, Object> templateModel = new HashMap<String, Object>();
				templateModel.put("name", addy.getName());
				templateModel.put("invoice_num", new Random().nextInt(234567890));
				templateModel.put("date", java.util.Calendar.getInstance().getTime().toString());
				templateModel.put("products", products);
				templateModel.put("total", pmap.get("charge_amount"));
				templateModel.put("tracking", shpmt.get("tracking_number"));

				try {
					email.sendMessageUsingFreemarkerTemplate(addy.getEmail().toString(),
							"Thank You For Shopping MLMStore", templateModel);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (TemplateException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				}

				return ResponseEntity.ok().body(shpmt);

			}

		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
}
