package com.mark.es.util;

public class IOUtils {

	public static void close(AutoCloseable io) {
		if(null!=io){
			try {
				io.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
