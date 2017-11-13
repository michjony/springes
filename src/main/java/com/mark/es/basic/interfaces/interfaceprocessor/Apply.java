package com.mark.es.basic.interfaces.interfaceprocessor;


public class Apply {
	
	public static void process(Processor p ,Object input){
		System.out.println("name:"+p.name());
		System.out.println("process:"+p.process(input));
	}

}
