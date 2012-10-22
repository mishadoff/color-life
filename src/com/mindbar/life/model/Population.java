package com.mindbar.life.model;

import static com.mindbar.life.utils.ArithmeticUtils.rMod;

import com.mindbar.life.exception.PopulationIsDeadException;

/**
 * Class describes each population. Backed by array[][].
 * @author mishadoff
 *
 */
public class Population {
	private int[][] field;
	
	private final int ROWS;
	private final int COLS;
	
	private final String DEAD_SYMBOL = ".";
	private final String LIVE_SYMBOL = "#";
	
	private int populationCount = 0;
	
	public Population(int[][] startPopulation) {
		field = startPopulation;	// think about copy
		ROWS = startPopulation.length;
		COLS = startPopulation[0].length;
		populationCount = calculatePopulation(startPopulation);
	}

	/**
	 * Performs one step in population. Changes state of this object.
	 */
	public void move() {
		// TODO good for performance rewrite for reusing array. Copy for now
		int[][] newPopulation = new int[ROWS][COLS];
		int newCount = 0;
			for (int i = 0; i < ROWS; i++) {
				for (int j = 0; j < COLS; j++) {
					int nCount = getNeighboursCount(i, j);
					// Rule 1: Dead becomes Live if have 3 neighbours
					if (field[i][j] == 0 && nCount == 3) {
						newPopulation[i][j] = 1;
						newCount++;
					}
					// Rule 2: Live becomes dead if less than 2 or greater than 3 neighbours
					if (field[i][j] == 1 && nCount >= 2 && nCount <= 3) {
						newPopulation[i][j] = 1;
						newCount++;
					}
				}
			}
		field = newPopulation;
		populationCount = newCount;
		if (isEmpty()) throw new PopulationIsDeadException("Game over!");
	}
	
	private boolean isEmpty() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (field[i][j] == 1) return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns count of neighbours of cell.<br>
	 * Take into account that rightmost element is the neighbour to
	 * leftmost as well as the upper with the bottom.
	 * @return
	 */
	private int getNeighboursCount(int i, int j) {
		int count = 0;
		count += field[rMod(i + 1, ROWS)][j];
		count += field[rMod(i - 1, ROWS)][j];
		count += field[rMod(i + 1, ROWS)][rMod(j + 1, COLS)];
		count += field[rMod(i - 1, ROWS)][rMod(j + 1, COLS)];
		count += field[rMod(i + 1, ROWS)][rMod(j - 1, COLS)];
		count += field[rMod(i - 1, ROWS)][rMod(j - 1, COLS)];
		count += field[i][rMod(j + 1, COLS)];
		count += field[i][rMod(j - 1, COLS)];
		return count;
	}
	
	private int calculatePopulation(int[][] population) {
		int count = 0;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (population[i][j] == 1) count++;
			}
		}
		return count;
	}
	
	private String print(int i, int j) {
		return (field[i][j] == 1) ? LIVE_SYMBOL : DEAD_SYMBOL;
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				b.append(print(i, j));
			}
			b.append("\n");
		}
		return b.toString();
	}
	
	public int getCount() {
		return populationCount;
	}
	
}
