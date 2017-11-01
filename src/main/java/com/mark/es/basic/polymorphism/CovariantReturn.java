package com.mark.es.basic.polymorphism;

/**
 * 协变返回类型 导出类中被覆盖方法可以返回基类方法的返回类型的某种导出类型
 */
public class CovariantReturn {

	public static void main(String[] args) {
		System.out.println(new Mill().get());
		System.out.println(new WheatMill().get());
	}

}

class Grain {
	public String toString() {
		return "Grain";
	}
}

class Wheat extends Grain {
	public String toString() {
		return "Wheat";
	}
}

class Mill {
	Grain get() {
		return new Grain();
	}
}

class WheatMill extends Mill {

	/**
	 * 使用@Override注解表示重载父类的方法，返回类型是父类方法返回类型的导出类型
	 * 也可将返回类型换为Grain，但是获取的对象依然是Wheat实例，进行了向上转型。
	 */
	@Override
	Wheat get() {
		return new Wheat();
	}

}