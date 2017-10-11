package com.mark.es.basic.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * DatagramChannel是一个能收发UDP包的通道，UDP不能像其它通道那样读取和写入，它发送和接受的是数据包
 * 
 * @author mqzhao
 *
 */
public class MyDatagramChannel {

	public static void main(String[] args) throws IOException {

		// 打开DatagramChannel的方式
		DatagramChannel channel = DatagramChannel.open();
		// 在80端口上接受UDP包
		channel.socket().bind(new InetSocketAddress(80));
		ByteBuffer readbuf = ByteBuffer.allocate(48);
		byte[] b;
		while (true) {
			// 接受数据
			readbuf.clear();
			// receive()方法会将接收到的数据包内容复制到指定的Buffer中，如果Buffer容不下收到的数据，多出的数据将被丢弃。
			SocketAddress receive = channel.receive(readbuf);
			if (null != receive) {
				String[] address = receive.toString().replace("/", "").split(":");
				int position = readbuf.position();
				b = new byte[position];
				readbuf.flip();
				for (int i = 0; i < position; i++) {
					b[i] = readbuf.get();
				}
				System.out.println("receive info from " + address[0] + ":" + address[1]);
				// 发送响应
				sendReback(receive, channel);
			}
		}
	}

	private static void sendReback(SocketAddress socketAddress, DatagramChannel channel) throws IOException {
		String message = "I has receive your message";
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.put(message.getBytes("UTF-8"));
		buffer.flip();
		channel.send(buffer, socketAddress);
	}
}
