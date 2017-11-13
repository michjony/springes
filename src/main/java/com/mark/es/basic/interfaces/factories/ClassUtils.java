package com.mark.es.basic.interfaces.factories;

public class ClassUtils {

	public String getClassNameAndMethodName(){
		return getClass().getName() + ":" 
				+ Thread.currentThread().getStackTrace()[1].getMethodName();
	}
	
	public static void main(String[] args) {
		//System.out.println(new ClassUtils().getClassNameAndMethodName());
		//CA.print();
		ClassUtils classUtils = new ClassUtils();
		CB cb = classUtils.new CB();
		cb.print();
	}
	
	private static class CA{
		public static void print(){
			System.out.println(new ClassUtils().getClassNameAndMethodName());
		}
	}
	
	class CB{
		public void print(){
			System.out.println(getClass().getName() + ":" 
					+ Thread.currentThread().getStackTrace()[1].getMethodName());
			System.out.println(new ClassUtils().getClassNameAndMethodName());
		}
	}
}
