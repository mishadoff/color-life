package com.mindbar.life.utils;

import java.util.Random;

import com.mindbar.life.model.Cell;

public final class RandomUtils {
	private static Random random = new Random();
	
	private RandomUtils() { }
	
	// TODO rewrite to generic probability cell map
	public Cell getRandomDeadLife() {
		return Cell.fromCode(random.nextInt(2));
	}
	
	// TODO extract to generator utils
	public static int generateStep() {
		return random.nextInt(3) - 1;
	}
	
	public static Cell genCell() {
		if (random.nextBoolean()) {
			return Cell.EMPTY;
		} else {
			return genRGB();
		}
	}
	
	public static Cell genRGB() {
		// TODO implement generic method
		int r = random.nextInt(3);
		if (r == 0) {
			return Cell.R;
		} else if (r == 1) {
			return Cell.G;
		} else {
			return Cell.B;
		}
	}
}
