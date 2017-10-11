package com.mark.es.basic.io;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class ChannelTransferFrom {

	public static void main(String[] args) throws Exception {
		// transferfrom
		RandomAccessFile fromfile = new RandomAccessFile("abc.txt", "rw");
		FileChannel f = fromfile.getChannel();
		RandomAccessFile tofile = new RandomAccessFile("to.txt", "rw");
		FileChannel tochannel = tofile.getChannel();
		int position = 0;
		long count = f.size();
		/**
		 * 方法的输入参数position表示从position处开始向目标文件写入数据，count表示最多传输的字节数。如果源通道的剩余空间小于 count 个字节，则所传输的字节数要小于请求的字节数。
此外要注意，在SoketChannel的实现中，SocketChannel只会传输此刻准备好的数据（可能不足count字节）。因此，SocketChannel可能不会将请求的所有数据(count个字节)全部传输到FileChannel中。
		 */
		//tochannel.transferFrom(f, position, count);
		
		/**
		 * transferTo()方法将数据从FileChannel传输到其他的channel中
		 */
		f.transferTo(position, count,tochannel);
		
	}
}
