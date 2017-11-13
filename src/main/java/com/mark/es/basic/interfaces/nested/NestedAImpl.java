package com.mark.es.basic.interfaces.nested;

/**
 * 实现嵌套接口 需要明确使用    --->  implements 外部接口.内部接口 
 */
public class NestedAImpl implements NestedA , NestedA.NestedB{


	@Override
	public void a() {

	}

	@Override
	public void b() {
		
	}

}
