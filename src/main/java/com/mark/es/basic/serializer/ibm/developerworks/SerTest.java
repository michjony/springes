package com.mark.es.basic.serializer.ibm.developerworks;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;
import com.mark.es.util.IOUtils;

public class SerTest {

	private static final String FILENAME = "tempdata.ser";
	@Test
	public void serializeToDisk(){
		Person mark = new Person("mark", "zhao", 20,Gender.MALE);
		Person jim = new Person("jim", "wang", 20,Gender.MALE);
		mark.setSpouse(jim);
		jim.setSpouse(mark);
		
		try {
			FileOutputStream fos = new FileOutputStream(FILENAME);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(mark);
			oos.writeObject(jim);
			IOUtils.close(oos);
			IOUtils.close(fos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deserializeFromDisk(){
		try {
			FileInputStream fis = new FileInputStream(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object object1 = ois.readObject();
			Object object2 = ois.readObject();
			IOUtils.close(ois);
			IOUtils.close(fis);
			System.out.println(object1);
			System.out.println(object2);
			//new File(FILENAME).delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
