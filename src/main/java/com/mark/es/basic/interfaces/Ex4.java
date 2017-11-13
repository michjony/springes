package com.mark.es.basic.interfaces;

public class Ex4 {
	public static void testPrint(Dad d) {
		((Son)d).print();
	}
	
	public static void testPrint(SecondDad d) {
		d.print();
	}
	
	public static void main(String[] args) {
		testPrint(new Son());
		testPrint(new SecondSon());
	}
}

abstract class Dad {
}

class Son extends Dad {
	protected void print() { System.out.println("Son"); }
}

/**
 * 在基类中加入abstract 即可不需要向上转型
 */
abstract class SecondDad {
	abstract protected void print();
}

class SecondSon extends SecondDad {
	protected void print() { System.out.println("SecondSon"); }
}