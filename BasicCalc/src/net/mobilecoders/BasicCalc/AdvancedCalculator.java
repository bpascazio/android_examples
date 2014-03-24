package net.mobilecoders.BasicCalc;

public class AdvancedCalculator extends BasicCalculator {

	int exp(int a, int b) {
		int c = a;
		int x = 0;
		for (x = 0; x < b; x++) {
			c = c * a;
		}
		return c;
	}
}
