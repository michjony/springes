package com.mark.es.basic.interfaces.filters;

/**
 * Filter 与   Processor具有相同的接口
 * @see com.mark.es.basic.interfaces.classprocessor.Processor
 * 
 * public Waveform process(Waveform input) 与  Object process(Object input) 之间的适配
 */
public class Filter {

	public String name(){
		return getClass().getSimpleName();
	}

	public Waveform process(Waveform input){
		return input;
	}
}
