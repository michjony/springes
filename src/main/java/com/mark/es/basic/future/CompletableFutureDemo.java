package com.mark.es.basic.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class CompletableFutureDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		Supplier<String> supplier = new Supplier<String>() {
			@Override
			public String get() {
				return "4";
			}
		};

		CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(supplier);
		CompletableFuture<Integer> thenApply = supplyAsync.thenApply(Integer::parseInt).thenApply(r -> r * r);
		System.out.println(thenApply.get());
	}

}

