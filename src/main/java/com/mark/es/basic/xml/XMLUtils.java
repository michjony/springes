package com.mark.es.basic.xml;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

public class XMLUtils {

	/**
	 * xml文件转为javabean文件
	 * 
	 * @throws IOException
	 */
	public static void XML2BeanFile() throws IOException {
		// 读取文件
		String newLine = System.getProperty("line.separator");
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new BufferedInputStream(new FileInputStream(new File("1.xml"))), "utf-8"));
		StringBuilder result = new StringBuilder();
		String line;
		boolean flag = false;
		while ((line = reader.readLine()) != null) {
			result.append(flag ? newLine : "").append(line);
			flag = true;
		}
		String str = result.toString();
		// 生成javabean类
		XStream x = new XStream();
		Object fromXML = x.fromXML(str);
		System.out.println(fromXML);
	}

	public static void main(String[] args) throws Exception {
		readXML("1.xml","com.xxx.xxx.dto");
	}

	/**
	 * 根据xml类生成java类
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static void readXML(String filename , String packagename) throws DocumentException, IOException {
		SAXReader sr = new SAXReader();// 获取读取xml的对象。
		Document doc = sr.read(filename);// 得到xml所在位置。然后开始读取。并将数据放入doc中
		Element el_root = (Element) doc.getRootElement();// 向外取数据，获取xml的根节点。
		String rootFilename = el_root.getName();
		Iterator it = ((org.dom4j.Element) el_root).elementIterator();// 从根节点下依次遍历，获取根节点下所有子节点
		File file = new File(rootFilename+".java");
		if(file.exists()){
			file.delete();
		}
		if(!file.exists()){
			file.createNewFile();
		}
		List<String> rootField = new ArrayList<>();
		List<String> eleField = new LinkedList<>();
		while (it.hasNext()) {// 遍历该子节点
			Object o = it.next();// 再获取该子节点下的子节点
			Element el_row = (Element) o;
			rootField.add(el_row.getName());
			String s = el_row.getText();
			Iterator it_row = el_row.elementIterator();
			File elejava = new  File(el_row.getName()+".java");
			if(elejava.exists()){
				elejava.delete();
			}
			if(!elejava.exists()){
				elejava.createNewFile();
			}
			while (it_row.hasNext()) {// 遍历节点
				Element el_ename = (Element) it_row.next();// 获取该节点下的所有数据。
				eleField.add(el_ename.getName());
			}
			//创建二级类
			createjava(elejava,eleField,packagename);
		}
		//创建一级类
		createjava(file,rootField,packagename);
	}


	private static void createjava(File file , List<String> rootField,String packagename) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(file))));
		StringBuilder sb = new StringBuilder();
		String str = file.getName();
		sb.append("package ").append(packagename).append(";\n\n");
		sb.append("import com.thoughtworks.xstream.annotations.XStreamAlias;\n\n");
		sb.append("@XStreamAlias(\"").append(str.substring(0, str.indexOf('.'))).append("\")\n");
		sb.append("public class ").append(str.substring(0, str.indexOf('.'))).append("{").append("\n");
		for (int i = 0; i < rootField.size(); i++) {
			sb.append("	@XStreamAlias(\"").append(rootField.get(i)).append("\")\n");
			sb.append("	private String ").append(rootField.get(i)).append(";\n\n");
		}
		sb.append("}");
		writer.write(sb.toString());
		writer.close();
	}
}
