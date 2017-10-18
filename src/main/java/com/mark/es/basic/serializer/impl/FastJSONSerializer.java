package com.mark.es.basic.serializer.impl;

import com.alibaba.fastjson.JSON;
import com.mark.es.basic.serializer.AbstractSerializer;

public class FastJSONSerializer extends AbstractSerializer {

	@Override
	public <T> byte[] serialize(T obj) {
		return JSON.toJSONBytes(obj);
	}

	@Override
	public <T> T deserialize(byte[] bytes, Class<T> clazz) {
		return JSON.parseObject(bytes, clazz);
	}

}
