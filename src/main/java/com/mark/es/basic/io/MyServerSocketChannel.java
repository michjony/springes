package com.mark.es.basic.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * ServerSocketChannel 是一个可以监听新进来的TCP连接的通道
 * 
 * @author mqzhao
 *
 */
public class MyServerSocketChannel {

	private static final int BUF_SIZE = 1024;
	private static final int PORT = 8080;
	private static final int TIMEOUT = 3000;

	public static void main(String[] args) {
		selector();
	}

	private static void selector() {
		Selector selector = null;
		ServerSocketChannel ssc = null;
		try {
			selector = Selector.open();
			// 打开ServerSocket
			ssc = ServerSocketChannel.open();
			// 绑定端口号
			ssc.socket().bind(new InetSocketAddress(PORT));
			// 设置非阻塞
			ssc.configureBlocking(false);
			// 注册请求连接  Channel配合Selector使用
			ssc.register(selector, SelectionKey.OP_ACCEPT);
			while (true) {
				if (selector.select(TIMEOUT) == 0) {
					System.out.println("等待连接超时");
					continue;
				}
				Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
				while (iter.hasNext()) {
					SelectionKey key = iter.next();
					if (key.isAcceptable()) {
						handleAccept(key);
					} else if (key.isReadable()) {
						handleRead(key);
					} else if (key.isWritable()) {
						System.out.println("start write info to client");
				
						handleWrite(key);
					} else if (key.isConnectable()) {
						handleConnect();
					}
					// 移除已经处理完的请求
					iter.remove();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != selector) {
					selector.close();
				}
				if (null != ssc) {
					ssc.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void handleConnect() {
		System.out.println("isConnectable");
	}

	private static void handleWrite(SelectionKey key) throws IOException {
		
		byte[] b = "welcome to server ".getBytes();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.clear();
		buffer.put(b);
		buffer.flip();
		SocketChannel sc = (SocketChannel) key.channel();
		while(buffer.hasRemaining()){
			sc.write(buffer);
		}
		buffer.clear();
		System.out.println("write over");
		sc.register(key.selector(), SelectionKey.OP_READ , ByteBuffer.allocateDirect(BUF_SIZE));
	}

	private static void handleRead(SelectionKey key) throws IOException {
		System.out.println("read key --> " + key.interestOps());
		SocketChannel sc = (SocketChannel)key.channel();
		ByteBuffer buf = (ByteBuffer) key.attachment();
		long bytesRead = sc.read(buf);
		StringBuffer sb = new StringBuffer();
		while(bytesRead>0){
			buf.flip();
			while(buf.hasRemaining()){
				sb.append((char)buf.get());
			}
			buf.clear();
			bytesRead = sc.read(buf);
		}
		if(bytesRead == -1){
			sc.close();
		}
		System.out.println("read message -> "+sb.toString());
		sc.register(key.selector(), SelectionKey.OP_WRITE , ByteBuffer.allocateDirect(BUF_SIZE));
	}

	private static void handleAccept(SelectionKey key) throws IOException {
		System.out.println("accept key --> " + key.interestOps());
		//通过SelectionKey获取
		ServerSocketChannel channel = (ServerSocketChannel)key.channel();
		SocketChannel sc = channel.accept();
		sc.configureBlocking(false);
		sc.register(key.selector(), SelectionKey.OP_READ , ByteBuffer.allocateDirect(BUF_SIZE));
	}
	
}
