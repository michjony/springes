package com.mark.es.basic.serializer;

import java.lang.reflect.Type;

public interface ISerializer<T> {
	
	T deserializeType(final byte[] bytes, final Type returnType) throws Exception ;
	
}
