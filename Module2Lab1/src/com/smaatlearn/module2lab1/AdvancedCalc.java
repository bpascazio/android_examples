package com.smaatlearn.module2lab1;

public class AdvancedCalc extends BasicCalc {

	int exp(int a, int power) {
		int x;
		c = 1;
		if (power==0) {
			return getone();
		}
		for (x=0;x<power;x++) {
			c = multiply(c,a);
		}
		return c;
	}
	
	int power10(int power) {
		return exp(10, power);
	}
	
}
