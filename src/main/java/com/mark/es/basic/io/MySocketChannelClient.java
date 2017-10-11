package com.mark.es.basic.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * SocketChannel 常用api 连接到TCP网络套接字的通道
 * 
 * @author mqzhao
 *
 */
public class MySocketChannelClient {

	public static void main(String[] args) throws IOException {
		/*
		// 创建SocketChannel的方式
		// 1.打开一个SocketChannel并连接到互联网上的某台服务器
		// 2.一个新连接到达ServerSocketChannel时，会创建一个SocketChannel
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		socketChannel.connect(new InetSocketAddress(8080));
		if (socketChannel.finishConnect()) {
			System.out.println("connected");
			// 从SocketChannel读取数据
			// 该方法将数据从SocketChannel
			// 读到Buffer中。read()方法返回的int值表示读了多少字节进Buffer里。如果返回的是-1，表示已经读到了流的末尾（连接关闭了）
			ByteBuffer buf = ByteBuffer.allocate(48);
			StringBuffer sb = new StringBuffer();
			int a = socketChannel.read(buf);
			while (a != -1) {
				buf.flip();
				while (buf.hasRemaining()) {
					sb.append((char)buf.get());
				}
				buf.clear();
				a = socketChannel.read(buf);
			}
			System.out.println("recevice data : " + sb.toString());
			// 写入SocketChannel 写数据到SocketChannel
			ByteBuffer writebuf = ByteBuffer.allocate(1024);
			String data = "client recevice server info ->  " + sb.toString();
			writebuf.clear();
			writebuf.put(data.getBytes());
			writebuf.flip();
			while (writebuf.hasRemaining()) {
				socketChannel.write(writebuf);
			}
		}
		// 关闭SocketChannel
		// socketChannel.close();
		 */
		client();
	}
	
	
	public static void client(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        try
        {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(8080));
 
            if(socketChannel.finishConnect())
            {
                int i=0;
                while(true)
                {
                    TimeUnit.SECONDS.sleep(1);
                    String info = "I'm "+i+++"-th information from client";
                    buffer.clear();
                    buffer.put(info.getBytes());
                    buffer.flip();
                    while(buffer.hasRemaining()){
                        socketChannel.write(buffer);
                    }
        			readServerInfo(socketChannel);
                }
            }
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
        finally{
            try{
                if(socketChannel!=null){
                    socketChannel.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }


	private static void readServerInfo(SocketChannel socketChannel) throws IOException {
		ByteBuffer buf = ByteBuffer.allocate(1024);
		StringBuffer sb = new StringBuffer();
		int a = socketChannel.read(buf);
		while (a != 0) {
			buf.flip();
			while (buf.hasRemaining()) {
				sb.append((char)buf.get());
			}
			buf.clear();
			a = socketChannel.read(buf);
		}
		System.out.println("recevice data : " + sb.toString());
	}
}
