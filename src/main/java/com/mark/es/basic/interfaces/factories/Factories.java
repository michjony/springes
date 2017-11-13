package com.mark.es.basic.interfaces.factories;

/**
 * 工厂方法设计模式
 * java编程思想第四版P187
 */
public class Factories {
	
	public static void main(String[] args) {
		serviceConsumer(new ServiceImpl1Factory());
		serviceConsumer(new ServiceImpl2Factory());
	}
	
	/**
	 * 面向接口编程，参数是一个接口，此方法被调用时，传递一个具体类
	 * @param sf
	 */
	public static void serviceConsumer(ServiceFactory sf){
		Service service = sf.getService();
		service.method1();
		service.method2();
	}
}

interface ServiceFactory {
	Service getService();
}

interface Service {
	void method1();

	void method2();
}

class ServiceImpl1 implements Service{
	ServiceImpl1(){}
	@Override
	public void method1() {
		System.out.println(this.getClass().getName() + "," + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Override
	public void method2() {
		System.out.println(this.getClass().getName() + "," + Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
}


/**
 * ServiceImpl1的实现工厂类
 */
class ServiceImpl1Factory implements ServiceFactory{
	@Override
	public Service getService() {
		return new ServiceImpl1();
	}
	
}

class ServiceImpl2 implements Service{
	ServiceImpl2(){}
	@Override
	public void method1() {
		System.out.println(this.getClass().getName() + "," + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Override
	public void method2() {
		System.out.println(this.getClass().getName() + "," + Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
}

/**
 *  ServiceImpl2 的 工厂类
 */
class ServiceImpl2Factory implements ServiceFactory{
	@Override
	public Service getService() {
		return new ServiceImpl2();
	}
	
}

