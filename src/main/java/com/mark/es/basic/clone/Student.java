package com.mark.es.basic.clone;

/**
 * 浅拷贝实例，重写clone方法，必须要实现Cloneable接口
 * @author mqzhao
 *
 */
public class Student implements Cloneable {

	private String name;
	private int number;

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

	/**
	 * 深度复制，需要特别注意修改clone
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (Student) super.clone();
	}


	@Override
	public String toString() {
		return "Student [name=" + name + ", number=" + number + "]";
	}

	public Student(String name, int number) {
		super();
		this.name = name;
		this.number = number;
	}

	
}
