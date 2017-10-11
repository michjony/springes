package com.mark.es.basic.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * scatter和gather用于描述从channel中读取或者写入到channel的操作
 * scatter(分散)从channel中读取是指，在读操作时将读取的数据写入到多个buffer中。因此，从channel中读取的数据分散(scatter)到多个buffer中
 * gather(聚集)写入Channel是指在写操作时将多个buffer的数据写入到同一个channel中，因此，channel将多个buffer中的数据聚集(gather)后发送到channel。
 * 
 * Scattering
 * Reads在移动下一个buffer前，必须填满当前的buffer，这也意味着它不适用于动态消息(译者注：消息大小不固定)。换句话说，如果存在消息头和消息体，消息头必须完成填充（例如
 * 128byte），Scattering Reads才能正常工作。
 * 
 * @author mqzhao
 *
 */
public class ScatterAndGatherDemo {

	public static void main(String[] args) throws IOException {

		ByteBuffer header = ByteBuffer.allocate(128);
		ByteBuffer body = ByteBuffer.allocate(1024);
		ByteBuffer[] bufferArray = { header, body };
		RandomAccessFile afile = new RandomAccessFile("abc.txt", "rw");
		FileChannel inChannel = afile.getChannel();
		inChannel.read(bufferArray);

		// buffers数组是write()方法的入参，write()方法会按照buffer在数组中的顺序，将数据写入到channel，注意只有position和limit之间的数据才会被写入。因此，如果一个buffer的容量为128byte，但是仅仅包含58byte的数据，那么这58byte的数据将被写入到channel中。因此与Scattering
		// Reads相反，Gathering Writes能较好的处理动态消息。
		inChannel.write(bufferArray);

	}

}
