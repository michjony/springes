package com.mark.es.basic.interfaces;

public class Ex3 {

	public static void main(String[] args) {
		ConsolePrint consolePrint = new ConsolePrint();
		consolePrint.print();
	}
}

abstract class Print{
	abstract void print();
	Print(){
		print();
	}
} 

class ConsolePrint extends Print{
	private int a = 1 ; //构造器之后才会初始化a
	@Override
	void print() {
		System.out.println("a:" + a);
	}
	
}