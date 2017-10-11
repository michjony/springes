package com.mark.es.basic.io;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 使用selector处理多个channel
 * 
 * @author mqzhao
 *
 */
public class MySelector {

	public static void main(String[] args) throws IOException {

		// 创建selector
		Selector selector = Selector.open();
		// 与Selector一起使用时，Channel必须处于非阻塞模式下。这意味着不能将FileChannel与Selector一起使用,因为FileChannel不能切换到非阻塞模式。而套接字通道都可以
		// RandomAccessFile file = new RandomAccessFile("abc.txt", "rw");
		// FileChannel filechannel = file.getChannel();
		ServerSocketChannel sc = ServerSocketChannel.open();
		sc.configureBlocking(false);
		// 向selector注册通道
		SelectionKey key = sc.register(selector, SelectionKey.OP_READ);

		// 通过key获取channel和selector
		// SelectableChannel channel = key.channel();
		// Selector selector2 = key.selector();

		// 附加对象，将一个对象或者更多信息附加到SelectionKey上。

		while (true) {
			// 通过selector选择通道,select()的返回值表示有多少通道已经准备就绪
			int readyChannels = selector.select();
			if (0 == readyChannels) {
				continue;
			}
			// 调用select()之后，表明有一个或更多个通道就绪，通过selectedKeys方法，访问“已选择键集”中的就绪通道
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			// 遍历已准备就绪的通道
			Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
			while (keyIterator.hasNext()) {
				SelectionKey k = keyIterator.next();
				if (k.isAcceptable()) {
					// a connection was accepted by a ServerSocketChannel.
				} else if (k.isConnectable()) {
					// a connection was established with a remote server.
				} else if (k.isReadable()) {
					// a channel is ready for reading
				} else if (k.isWritable()) {
					// a channel is ready for writing
				}
				keyIterator.remove();
			}
		}
	}

}
