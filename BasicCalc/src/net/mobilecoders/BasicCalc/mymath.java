package net.mobilecoders.BasicCalc;

public class mymath extends calc {

	int exp(int a, int power) {
		int x;
		c = 1;
		for (x=0;x<power;x++) {
			c = multiply(c,a);
		}
		return getzero();
	}
}
