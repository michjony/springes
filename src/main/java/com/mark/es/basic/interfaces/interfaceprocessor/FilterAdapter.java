package com.mark.es.basic.interfaces.interfaceprocessor;

import com.mark.es.basic.interfaces.filters.Filter;
import com.mark.es.basic.interfaces.filters.Waveform;

/**
 * 适配器
 * @author mqzhao
 *
 */
public class FilterAdapter implements Processor {
	//注入实现
	Filter filter;

	FilterAdapter(Filter filter) {
		this.filter = filter;
	}

	@Override
	public String name() {
		return filter.name();
	}

	@Override
	public Waveform process(Object input) {
		return filter.process((Waveform)input);
	}

}
