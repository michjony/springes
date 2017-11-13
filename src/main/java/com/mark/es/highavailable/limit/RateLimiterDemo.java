package com.mark.es.highavailable.limit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterDemo {
	
	//每秒10个许可
	final RateLimiter rateLimiter = RateLimiter.create(10.0);
	
	void submitTask(List<Runnable> tasks,Executor executor){
		for (Runnable runnable : tasks) {
			rateLimiter.acquire();
			executor.execute(runnable);
		}
	}
	
	public static void main(String[] args) {
		List<Runnable> tasks = new ArrayList<>();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("thread run start");
					TimeUnit.SECONDS.sleep(1);
					System.out.println("thread run stop");
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					e.printStackTrace();
				}
			}
		});
		tasks.add(thread);
		ExecutorService  executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		new RateLimiterDemo().submitTask(tasks , executor );
		executor.shutdown();
	}
}
