package com.mark.es.basic.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 
 * @author mqzhao
 * buffer本质上是一个缓冲区，可以写入数据，然后从中读取数据到内存中，这块内存被包装成NIO Buffer对象，并提供一组方法，用来方便的访问这块内存
 * buffer的三个属性：position,limit,capacity
 * position和limit的含义取决于Buffer处在读模式还是写模式。不管Buffer处在什么模式，capacity的含义总是一样的。
 * 
capacity
作为一个内存块，Buffer有一个固定的大小值，也叫“capacity”.你只能往里写capacity个byte、long，char等类型。一旦Buffer满了，需要将其清空（通过读数据或者清除数据）才能继续写数据往里写数据。
position
当你写数据到Buffer中时，position表示当前的位置。初始的position值为0.当一个byte、long等数据写到Buffer后， position会向前移动到下一个可插入数据的Buffer单元。position最大可为capacity – 1.
当读取数据时，也是从某个特定位置读。当将Buffer从写模式切换到读模式，position会被重置为0. 当从Buffer的position处读取数据时，position向前移动到下一个可读的位置。
limit
在写模式下，Buffer的limit表示你最多能往Buffer里写多少数据。 写模式下，limit等于Buffer的capacity。
当切换Buffer到读模式时， limit表示你最多能读到多少数据。因此，当切换Buffer到读模式时，limit会被设置成写模式下的position值。换句话说，你能读到之前写入的所有数据（limit被设置成已写数据的数量，这个值在写模式下就是position）
 rewind()方法
Buffer.rewind()将position设回0，所以你可以重读Buffer中的所有数据。limit保持不变，仍然表示能从Buffer中读取多少个元素（byte、char等）。


 *
 *
 *
 */
public class MyChannel {
	//使用文件通道FileChannel 读取abc.txt的数据
	public static void main(String[] args) throws IOException {
		RandomAccessFile afile = new RandomAccessFile("abc.txt","rw");
		FileChannel inChannel = afile.getChannel();
		//设置buffer的capacity大小
		ByteBuffer buf = ByteBuffer.allocate(48);
		//写数据到buffer,写到buffer有两种方式：1，从channel写到buffer；2，使用buffer的put方法
		int bytesRead = inChannel.read(buf);
		while(bytesRead!=-1){
			System.out.println("Read " + bytesRead);
			//需要通过flip()方法将Buffer从写模式切换到读模式。在读模式下，可以读取之前写入到buffer的所有数据
			//调用flip()方法会将position设回0，并将limit设置成之前position的值。
			buf.flip(); //make buffer ready for read
			//从buffer中读取数据
			//两种方式：1，从buffer中读取使用get---buf.get()；2，从buffer中读取数据到channel，使用----inChannel.write(buf) read from buffer into channel.
			while(buf.hasRemaining()){
				System.out.println((char)buf.get()); //read 1 byte  at a time 
			}
			//一旦读完了所有的数据，就需要清空缓冲区，让它可以再次被写入。有两种方式能清空缓冲区：调用clear()或compact()方法。clear()方法会清空整个缓冲区。compact()方法只会清除已经读过的数据。任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。
			buf.clear(); //make buffer ready for writing
			bytesRead = inChannel.read(buf);
		}
		afile.close();
		
	}

}
