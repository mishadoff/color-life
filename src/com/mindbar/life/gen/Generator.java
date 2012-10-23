package com.mindbar.life.gen;

import com.mindbar.life.model.Cell;
import com.mindbar.life.utils.RandomUtils;

public class Generator {
	
	public Cell[][] getRandomPopulation(int rows, int cols) {
		Cell[][] pop = new Cell[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				pop[i][j] = RandomUtils.genCell();
			}
		}
		return pop;
	}
}
