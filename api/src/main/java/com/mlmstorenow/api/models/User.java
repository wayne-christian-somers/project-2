package com.mlmstorenow.api.models;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.shippo.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ JsonViewProfiles.User.class })
	private int id;

	@Column(name = "email", unique = true)
	@Email
	@NotBlank
	private String email;

	@Column(name = "passwrd")
	@NotBlank
	private String password;

	@Column(name = "address_list")
	private ArrayList<Address> Addresses = new ArrayList<Address>();

	@Column(name = "cart")
	private ArrayList<Product> cart = new ArrayList<Product>();

	@Column(name = "order_history")
	private ArrayList<Product> orderHistory = new ArrayList<Product>();

	public User(@Email @NotBlank String email, @NotBlank String password) {
		super();
		this.email = email;
		this.password = password;
	}

}
