package com.mark.es.basic.clone;

public class AddressV2 implements Cloneable{
	private String city;
	private String town;

	public AddressV2(String city, String town) {
		super();
		this.city = city;
		this.town = town;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (AddressV2)super.clone();
	}

	@Override
	public String toString() {
		return "AddressV2 [city=" + city + ", town=" + town + "]";
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


}
