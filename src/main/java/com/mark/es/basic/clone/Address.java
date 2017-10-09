package com.mark.es.basic.clone;

public class Address {
	private String city;
	private String town;

	public Address(String city, String town) {
		super();
		this.city = city;
		this.town = town;
	}

	@Override
	public String toString() {
		return "Address [city=" + city + ", town=" + town + "]";
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public Address() {
	}

}
