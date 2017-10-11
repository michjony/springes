package com.mark.es.basic.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOAndIO {

	/**
	 * 使用io读取文件
	 * 
	 * @param filepath
	 * @throws Exception
	 */
	public static void ioreadFile(String filepath) throws Exception {
		InputStream in = new BufferedInputStream(new FileInputStream(filepath));
		byte[] buf = new byte[1024];
		int readsbytes = in.read(buf);
		while (-1 != readsbytes) {
			for (int i = 0; i < readsbytes; i++) {
				System.out.print((char) buf[i]);
			}
			readsbytes = in.read(buf);
		}
		if (in != null) {
			in.close();
		}
	}

	/**
	 * 使用nio读取文件
	 * 
	 * @param filepath
	 * @throws Exception
	 */
	public static void nioreadFile(String filepath) throws Exception {
		RandomAccessFile file = new RandomAccessFile(filepath, "rw");
		FileChannel channel = file.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(64);
		buf.clear();
		int read = channel.read(buf);
		while (read != -1) {
			buf.flip();
			while (buf.hasRemaining()) {
				System.out.print((char) buf.get());
			}
			buf.clear();
			read = channel.read(buf);
		}
		if (null != channel) {
			channel.close();
		}
	}

	public static void main(String[] args) throws Exception {
		ioreadFile("abc.txt");
		System.out.println();
		nioreadFile("abc.txt");
	}
}
