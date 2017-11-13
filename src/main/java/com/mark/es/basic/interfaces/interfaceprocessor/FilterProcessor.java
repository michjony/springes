package com.mark.es.basic.interfaces.interfaceprocessor;

import com.mark.es.basic.interfaces.filters.BandPass;
import com.mark.es.basic.interfaces.filters.HighPass;
import com.mark.es.basic.interfaces.filters.LowPass;
import com.mark.es.basic.interfaces.filters.Waveform;

public class FilterProcessor {

	public static void main(String[] args) {
		Waveform w = new Waveform();
		Apply.process(new FilterAdapter(new LowPass(1.0)), w);
		Apply.process(new FilterAdapter(new HighPass(2.0)), w);
		Apply.process(new FilterAdapter(new BandPass(1.0,2.0)), w);
	}

}
