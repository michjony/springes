package com.mark.es.basic.interfaces.filters;

public class BandPass extends Filter {
	double highcutoff;
	double lowcutoff;

	public BandPass(double highcutoff, double lowcutoff) {
		this.highcutoff = highcutoff;
		this.lowcutoff = lowcutoff;
	}

	@Override
	public Waveform process(Waveform input) {
		return input;
	}

}
