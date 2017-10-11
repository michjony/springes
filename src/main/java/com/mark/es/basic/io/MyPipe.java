package com.mark.es.basic.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * pipe是2个线程之间的单向数据连接，有一个source通道和一个sink通道，数据会被写到sink通道，从source通道读取
 * @author mqzhao
 *
 */
public class MyPipe {

	public static void main(String[] args) throws IOException {
		//创建管道
		Pipe pipe = Pipe.open();
		//向管道写数据 需要使用sink通道
		Pipe.SinkChannel sinkChannel = pipe.sink();
		ByteBuffer buf = ByteBuffer.allocate(32); //声明的缓冲区大小要大于put的字节数组
		buf.clear();
		buf.put("new message".getBytes()); //
		buf.flip();
		while(buf.hasRemaining()) {
		    sinkChannel.write(buf);
		}
		//从管道中读取数据
		Pipe.SourceChannel sourceChannel = pipe.source();
		ByteBuffer readbuf = ByteBuffer.allocate(32);
		int read = sourceChannel.read(readbuf);
		System.out.println("start read");
		while(read!=-1){
			readbuf.flip();
			while(readbuf.hasRemaining()){
				System.out.print((char)readbuf.get());
			}
			readbuf.clear();
			read = sourceChannel.read(readbuf);
		}
		System.out.println("over read");
	}
}
