package com.mark.es.basic.polymorphism;

/**
 * 构造器内部存在多态方法行为
 * 在构造器中，不要加入非final修饰的方法
 */
public class PolyConstructors {

	public static void main(String[] args) {
		new B(3);
	}
}

class A {
	void draw() {
		System.out.println("A drawing");
	}

	A() {
		System.out.println("A draw() start ");
		draw();
		System.out.println("A draw() stop ");
	}
}

class B extends A {
	void draw() {
		//radius 打印的不是实例化B的值，而是未初始化的值0
		System.out.println("B drawing of radius " + radius);
	}

	private int radius = 1;

	B() {
		System.out.println("B default : radius " + radius);
	}

	B(int radius) {
		this.radius = radius;
		System.out.println("B : radius " + radius);
	}
}
