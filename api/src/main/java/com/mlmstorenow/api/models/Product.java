package com.mlmstorenow.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="produts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Product {

	@Id
	@Column(name="product_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView({ JsonViewProfiles.User.class})
	private int id;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_amount")
	private Double amount;
	
	@Column(name="user_id")
	private int Userid;
}
