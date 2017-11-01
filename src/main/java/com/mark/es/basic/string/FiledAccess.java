package com.mark.es.basic.string;

/**
 * 普通的方法访问才有多态，直接访问某个域，这个域将在编译期进行解析。
 * @author mqzhao
 *
 */
public class FiledAccess {

	public static void main(String[] args) {
		Super sup = new Sub();
		System.out.println("field:" + sup.field + ";sup.getField:" + sup.getField());
		Sub sub = new Sub();
		System.out.println("field:" + sub.field + ";sub.getField:" + sub.getField());
		System.out.println("sub.getSuperField:" +sub.getSuperField());
	}

}
class Super{
	public int field = 0;
	public int getField(){
		return field;
	}
}
class Sub extends Super{
	public int field = 1;
	public int getField(){
		return field;
	}
	public int getSuperField(){
		return super.field;
	}
}
