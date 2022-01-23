package com.mlmstorenow.api.models;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="shipments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Shipmnt {

	@Id
	@Column(name="shipment_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView({ JsonViewProfiles.User.class})
	private int id;
	
	@Column(name = "service")
	@NotBlank
	private String Service;
	
	@Column(name = "address_from")
	@NotBlank
	private HashMap<String, String> from;
	
	@Column(name = "address_to")
	@NotBlank
	private HashMap<String, String> to;
	
	@Column(name = "weight")
	@NotBlank
	private int weight;
	
	@Column(name = "weight_unit")
	@NotBlank
	private String weight_unit;
	
	@Column(name = "dimensions")
	@NotBlank
	private HashMap<String, Integer> dimensions;
}
