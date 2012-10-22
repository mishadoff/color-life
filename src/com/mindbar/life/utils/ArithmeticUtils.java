package com.mindbar.life.utils;

public final class ArithmeticUtils {
	private ArithmeticUtils() { }
	
	public static int rMod(int a, int b) {
		return (a >= 0) ? a % b : (a + b) % b;
	}
}
