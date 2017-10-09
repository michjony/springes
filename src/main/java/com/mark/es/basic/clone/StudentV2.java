package com.mark.es.basic.clone;

/**
 * 浅拷贝实例，重写clone方法，必须要实现Cloneable接口
 * 
 * @author mqzhao
 *
 */
public class StudentV2 implements Cloneable {

	private String name;
	private int number;
	private Address address;
	private AddressV2 addressv2;
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		StudentV2 stu = (StudentV2) super.clone(); // 浅复制
		AddressV2 addr  = (AddressV2) addressv2.clone();//深复制
		stu.setAddressv2(addr);
		return stu;
	}

	@Override
	public String toString() {
		return "StudentV2 [name=" + name + ", number=" + number + ", address=" + address + 
				", addressv2=" + addressv2
				+ "]";
	}

	public StudentV2(String name, int number, Address address, AddressV2 addressv2) {
		super();
		this.name = name;
		this.number = number;
		this.address = address;
		this.addressv2 = addressv2;
	}

	public StudentV2(String name, int number, Address address) {
		super();
		this.name = name;
		this.number = number;
		this.address = address;
	}

	public AddressV2 getAddressv2() {
		return addressv2;
	}

	public void setAddressv2(AddressV2 addressv2) {
		this.addressv2 = addressv2;
	}


}
