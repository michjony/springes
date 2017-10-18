package com.mark.es.basic.serializer.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.mark.es.basic.serializer.AbstractSerializer;

public class HessianSerializer extends AbstractSerializer{

	@Override
	public <T> byte[] serialize(T obj) {
		ByteArrayOutputStream io = new ByteArrayOutputStream();
		HessianOutput ho = new HessianOutput(io);
		try {
			ho.writeObject(obj);
		} catch (IOException e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
		return io.toByteArray();
	}

	@Override
	public <T> T deserialize(byte[] bytes, Class<T> clazz) {
		ByteArrayInputStream io = new ByteArrayInputStream(bytes);
		HessianInput hi = new HessianInput(io);
		try {
			T t = (T) hi.readObject();
			return t;
		} catch (IOException e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
		
	}


}
