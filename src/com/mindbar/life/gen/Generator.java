package com.mindbar.life.gen;

import java.util.Random;

public class Generator {
	
	private Random random;
	
	public Generator() {
		random = new Random();
	}
	
	public int[][] getRandomPopulation(int rows, int cols) {
		int[][] pop = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				pop[i][j] = random.nextInt(2);
			}
		}
		return pop;
	}
	
}
