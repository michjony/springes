package com.mark.es.basic.serializer.ibm.developerworks;

import java.io.IOException;
import java.io.Serializable;

/**
 * 自定义序列化方式 重写WriteObject和ReadObject方法
 */
public class PersonV2 implements Serializable {
	private static final long serialVersionUID = -4292678404265653154L;

	public PersonV2(String fn, String ln, int a, Gender sex) {
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

	public PersonV2 getSpouse() {
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

	public void setSpouse(PersonV2 value) {
		spouse = value;
	}

	@Override
	public String toString() {
		return "[Person: firstName=" + firstName + " lastName=" + lastName + " gender=" + sex + " age=" + age
				+ " spouse=" + spouse.getFirstName() + "]";
	}

	private String firstName;
	private String lastName;
	private int age;
	private PersonV2 spouse;

	// 新增sex字段，serialVersionUID不变的情况下，依然可以从之前的序列化文件中，反序列化原始对象，新增的字段，根据其类型，反序列是给出初始值
	private Gender sex;

	public Gender getSex() {
		return sex;
	}

	public void setSex(Gender sex) {
		this.sex = sex;
	}

	private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
		age = age << 2;
		stream.defaultWriteObject();
	}

	private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
		
	}

}
