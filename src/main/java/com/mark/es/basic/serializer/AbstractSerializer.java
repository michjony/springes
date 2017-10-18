package com.mark.es.basic.serializer;

import com.mark.es.basic.serializer.impl.FastJSONSerializer;
import com.mark.es.basic.serializer.impl.HessianSerializer;

/**
 * 抽象序列化类
 * 定义序列化的抽象方法
 * @author mqzhao
 */
public abstract class AbstractSerializer {

	//序列化方法
	public abstract <T> byte[] serialize(T obj);
	//反序列化方法
	public abstract <T> T deserialize (byte[] bytes, Class<T> clazz);
	
	//序列化枚举类	
	public enum SerializeEnum{
		FASTJSON(new FastJSONSerializer()),HESSIAN(new HessianSerializer());
		public final AbstractSerializer serializer;
		SerializeEnum(AbstractSerializer _serializer){
			serializer = _serializer;
		}
	} 
	
}
