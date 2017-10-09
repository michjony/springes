package com.mark.es.basic.clone;

public class MyClone {

	public static void main(String[] args) throws CloneNotSupportedException {
		Student s1 = new Student("mark", 1);
		//浅拷贝：拷贝的是对象的引用
		Student s2 = (Student) s1.clone();
		s1.setNumber(2);
		System.out.println(s1);
		System.out.println(s2);
		Address addr  = new Address("深圳","南山");
		AddressV2 addrv2 = new AddressV2("荆州","江陵");
		StudentV2 stu = new StudentV2("mark",11,addr,addrv2);
		//深拷贝：同时拷贝了对象属性的指向
		StudentV2 stu2 = (StudentV2) stu.clone();
		System.out.println("stu : "+stu);
		System.out.println("stu2 : "+stu2);
		//修改stu2对象的addr的值，会同时修改stu的对象和stu2对象的addr
		addr.setTown("宝安");
		//修改addrv2的值，只修改了stu对象的addressv2(因为addrv2指向stu)，未修改复制的stu2对象的addressv2
		addrv2.setTown("马家寨");
		System.out.println("stu : "+stu);
		System.out.println("stu2 : "+stu2);
	}
}
