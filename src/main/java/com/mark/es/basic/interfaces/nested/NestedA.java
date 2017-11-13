package com.mark.es.basic.interfaces.nested;

public interface NestedA {
	void a();

	interface NestedB {
		void b();
	}
	
	class NestedAA{
		//private接口不能在定义它的类之外被实现
		private interface NestedAAA{
			void c();
		}
		
		class NestedAAAA implements NestedAAA{

			@Override
			public void c() {
				
			}
			
		}
	}
	

}
