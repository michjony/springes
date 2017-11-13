package com.mark.es.basic.interfaces.classprocessor;

import java.util.Arrays;

/**
 * 策略设计模式
 *
 */
public class Apply {

	/**
	 * 策略模式的对外方法，传递不同的实现类，实现不同的策略
	 * @param p
	 * @param input
	 */
	public static void process(Processor p ,Object input){
		System.out.println("name:"+p.name());
		System.out.println("process:"+p.process(input));
	}
	public static void main(String[] args) {
		String str = "i love china as i love it.";
		process(new Upcase(),str);
		process(new Lowercase(),str);
		process(new Spilter(),str);
	}
}

class Processor{
	public String name(){
		return this.getClass().getSimpleName();
	}
	Object process(Object input){
		return input;
	}
}

/**
 * 大写策略
 */
class Upcase extends Processor{
	@Override
	String process(Object input){
		return ((String)input).toUpperCase();
	}
}

/**
 * 小写策略
 */
class Lowercase extends Processor{
	@Override
	String process(Object input){
		return ((String)input).toLowerCase();
	}
}

/**
 * 分隔策略
 */
class Spilter extends Processor{
	@Override
	String process(Object input){
		return Arrays.toString(((String)input).split(" "));
	}
}