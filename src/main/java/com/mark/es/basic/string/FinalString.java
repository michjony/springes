package com.mark.es.basic.string;

public class FinalString {

	// 使用final修饰的变量一旦初始化就不能修改
	private final String finalString = "finalString";
	private String string;

	public static void main(String[] args) throws InterruptedException {
		FinalString a = new FinalString();
		FinalString b = new FinalString();
		Thread threada = new Thread(new Runnable() {
			@Override
			public void run() {
				a.string = "threada";
			}
		});
		Thread threadb = new Thread(new Runnable() {
			@Override
			public void run() {
				b.string = "threadb";
			}
		});
		threada.start();
		threadb.start();
		threada.join();
		threadb.join();
		System.out.println(a.string);
		System.out.println(b.string);
		System.out.println(a.finalString);
		System.out.println(b.finalString);
		System.out.println(a.finalString.equals(b.finalString));
	}
}
