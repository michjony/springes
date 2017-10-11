package com.mark.es.basic.io;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MyDatagramChannelClient {

	public static void main(String[] args) throws Exception {
		DatagramChannel client = DatagramChannel.open();
		client.socket().bind(new InetSocketAddress(8080));
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				ByteBuffer readbuf = ByteBuffer.allocate(48);
				SocketAddress receive;
				try {
					receive = client.receive(readbuf);
					if (receive != null) {
						String[] address = receive.toString().replace("/", "").split(":");
						int position = readbuf.position();
						byte[] b = new byte[position];
						readbuf.flip();
						for (int i = 0; i < position; i++) {
							b[i] = readbuf.get();
						}
						System.out.println("receive info from " + address[0] + ":" + address[1]);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		while (true) {
			Scanner sc = new Scanner(System.in);
			String next = sc.nextLine();
			if (next.equals("exit")) break; 
			try {
				sendMessage(client, next);
				TimeUnit.SECONDS.sleep(10);
				t.start();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private static void sendMessage(DatagramChannel channel, String mes) throws Exception {
		if (mes == null || mes.isEmpty()) {
			return;
		}
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.clear();
		buffer.put(mes.getBytes("UTF-8"));
		buffer.flip();
		System.out.println("send msg:" + mes);
		int send = channel.send(buffer, new InetSocketAddress("localhost", 1234));
		System.out.println("send message size : " + send);
	}

}
