package com.smaatlearn.module2lab1;

public class BasicCalc {
	
	int c;

	int getone() {
		return 1;
	}

	int add(int a, int b) {
		c = a + b;
		return c;
	}

	int subtract(int a, int b) {
		c = a - b;
		return c;
	}

	int multiply(int a, int b) {
		c = a * b;
		return c;
	}

	int divide(int a, int b) {
		c = a / b;
		return c;
	}

}
