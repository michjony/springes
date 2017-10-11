package com.mark.es.basic.io;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MyFileChannel {

	public static void main(String[] args) throws Exception {
		
		//打开FileChannel 使用InputStream OutputStream RandomAccessFile流打开
		RandomAccessFile file = new RandomAccessFile("abc.txt","rw");
		FileChannel channel = file.getChannel();
		//从FileChannel中读取数据,将数据写入到bytebuffer中
		ByteBuffer buf = ByteBuffer.allocate(1024);
		int bytesRead = channel.read(buf);
		while(-1!=bytesRead){
			buf.flip();
			while(buf.hasRemaining()){
				System.out.println((char)buf.get());
			}
			buf.clear();
			bytesRead = channel.read(buf);
		}
		//向fileChannel写数据
		String newData = "new String from channel write";
		System.out.println("==1===");
		//获取byteBuf的位置
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println("==2===");
		//获取Channel的position
		System.out.println(channel.position());
		//设置channel的postion位置
		channel.position(100+channel.position());
		System.out.println(channel.position());
		System.out.println("==3===");
		buf.put(newData.getBytes());
		buf.flip();
		/**
		 * FileChannel.write()是在while循环中调用的。因为无法保证write()方法一次能向FileChannel写入多少字节，因此需要重复调用write()方法，直到Buffer中已经没有尚未写入通道的字节
		 */
		while(buf.hasRemaining()){
			channel.write(buf);
		}
	
		//fileChannel的size方法,返回该实例所关联文件的大小
		long fileSize = channel.size();
		
		//filechannel的truncate方法 截取一个文件，截取文件时，文件将中指定长度后面的部分将被删除
		channel.truncate(1024);

		//filechannel的force()方法将通道里尚未写入磁盘的数据强制写到磁盘上。
		channel.force(true);
		//关闭FileChannel
		channel.close();
	}

}
