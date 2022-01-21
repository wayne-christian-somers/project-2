package com.mlmstorenow.api.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlmstorenow.api.config.ConfigProperties;
import com.mlmstorenow.api.models.Shipment;
import com.shipengine.ShipEngine;

@Service
public class ShipmentService {
	
	@Autowired
	Shipment shipmod;

	private ShipEngine shipengine = new ShipEngine(ConfigProperties.getConfigProp("se_api_key"));
	
	public Map<String, String> getShippingLabel(String user, Shipment ship, ArrayList<String> productNames) {
		shipmod= ship;
		
		Map<String, Object> shipmnt = new HashMap<String, Object>();
		shipmnt.put("carrier_id", "se-1234");
		shipmnt.put("service_code", "usps_first_class_mail");
		shipmnt.put("external_order_id", null );
		shipmnt.put("item", productNames);
		shipmnt.put("tax_identifiers", null);
		
		Map<String, Object> shipment = new HashMap<String, Object>();
		shipment.put("Shipment", shipmnt);
		shipment.put( "external_shipment_id", shipmod.getId()+"-"+user);
		shipment.put("ship_date", java.time.LocalDateTime.now());
		shipment.put( "ship_to", shipmod.getTo());
		shipment.put("ship_from", shipmod.getFrom());
		
		
		
		
		return shipengine.createLabelFromShipmentDetails(shipment);
	}
}
