package com.lumata.expression.operators.pojo.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Agencies { 

	private static final Logger logger = LoggerFactory.getLogger( Agencies.class );

	public enum Fields { id, name, address, phone }

	private int id;
	private String name;
	private String address;
	private String phone;

	public Agencies() {} 

	public int getId() {

		return this.id;

	}

	public void setId( int id ) {

		this.id = id;

	}

	public String getName() {

		return this.name;

	}

	public void setName( String name ) {

		this.name = name;

	}

	public String getAddress() {

		return this.address;

	}

	public void setAddress( String address ) {

		this.address = address;

	}

	public String getPhone() {

		return this.phone;

	}

	public void setPhone( String phone ) {

		this.phone = phone;

	}

 }