package com.mark.es.basic.serializer.ibm.developerworks;

import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = -4292678404265653154L;

	public Person(String fn, String ln, int a,Gender sex) {
		this.firstName = fn;
		this.lastName = ln;
		this.age = a;
		this.sex = sex;
	}	

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public Person getSpouse() {
		return spouse;
	}

	public void setFirstName(String value) {
		firstName = value;
	}

	public void setLastName(String value) {
		lastName = value;
	}

	public void setAge(int value) {
		age = value;
	}

	public void setSpouse(Person value) {
		spouse = value;
	}

	@Override
	public String toString()
	{
		return "[Person: firstName=" + firstName + " lastName=" + lastName + " gender=" + sex + " age=" + age + " spouse=" + spouse.getFirstName() +"]";
	}
	
	private String firstName;
	private String lastName;
	private int age;
	private Person spouse;
	
	//新增sex字段，serialVersionUID不变的情况下，依然可以从之前的序列化文件中，反序列化原始对象，新增的字段，根据其类型，反序列是给出初始值
	private Gender sex;

	public Gender getSex() {
		return sex;
	}

	public void setSex(Gender sex) {
		this.sex = sex;
	}

}


