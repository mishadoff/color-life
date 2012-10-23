package com.mindbar.life.gen;

import java.util.Random;

import com.mindbar.life.model.Cell;

public class Generator {
	
	private static Random random = new Random();
	
	public Cell[][] getRandomPopulation(int rows, int cols) {
		Cell[][] pop = new Cell[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				pop[i][j] = getRandomDeadLife();
			}
		}
		return pop;
	}
	
	// TODO rewrite to generic probability cell map
	public Cell getRandomDeadLife() {
		return Cell.fromCode(random.nextInt(2));
	}
	
	// TODO extract to generator utils
	public static int generateStep() {
		return random.nextInt(3) - 1;
	}
	
}
