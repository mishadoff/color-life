package com.mindbar.life.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mindbar.life.gen.Generator;
import com.mindbar.life.model.Population;

public class PerformanceTest {

	@Test
	public void testPerformance() {
		final int ROWS = 100;
		final int COLS = 100;
		int NUM = 10000;
		Generator g = new Generator();
		Population p = new Population(g.getRandomPopulation(ROWS, COLS));
		System.out.println("Field: " + ROWS + " x " + COLS);
		System.out.println("Tests: " + NUM);
		for (int i = 0; i < NUM; i++) {
			p.move();
		}
	}

}
