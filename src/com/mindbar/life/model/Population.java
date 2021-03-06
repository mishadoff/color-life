package com.mindbar.life.model;

import static com.mindbar.life.utils.ArithmeticUtils.rMod;

import java.util.HashSet;

import com.mindbar.life.exception.PopulationIsDeadException;
import com.mindbar.life.gen.Generator;
import com.mindbar.life.utils.RandomUtils;

/**
 * Class describes each population. Backed by array[][].
 * @author mishadoff
 *
 */
public class Population {
	private Cell[][] field;
	
	private final int ROWS;
	private final int COLS;
	
	private int populationCount = 0;
	private int virusesCount = 0;
	
	public Population(Cell[][] startPopulation) {
		field = startPopulation;	// think about copy
		ROWS = startPopulation.length;
		COLS = startPopulation[0].length;
		populationCount = calculateLive(startPopulation);
		virusesCount = calculateCells(startPopulation, Cell.VIRUS);
	}

	/**
	 * Performs one step in population. Changes state of this object.
	 */
	public void move() {
		// TODO good for performance rewrite for reusing array. Copy for now
		// TODO easy to reuse two arrays
		Cell[][] newPopulation = new Cell[ROWS][COLS];
		
		HashSet<Integer> viruses = new HashSet<Integer>();
		
		int newCount = 0;
			for (int i = 0; i < ROWS; i++) {
				for (int j = 0; j < COLS; j++) {
					if (field[i][j] == Cell.VIRUS) { // move it to random direction
						viruses.add(i * COLS + j);
						continue;
					}
					int nCount = getNeighboursCount(i, j);
					// TODO make DSL for the rules
					// Rule 1: EMPTY becomes LIVE if have 3 neighbours
					if (field[i][j] == Cell.EMPTY && nCount == 3) {
						newPopulation[i][j] = RandomUtils.genRGB();
						newCount++;
						continue;
					} else {
						newPopulation[i][j] = Cell.EMPTY;
					}
					// Rule 2: Live becomes dead if less than 2 or greater than 3 neighbours
					if (field[i][j].isLive() && nCount >= 2 && nCount <= 3) {
						newPopulation[i][j] = field[i][j];
						newCount++;
						continue;
					} else {
						newPopulation[i][j] = Cell.EMPTY;
						continue;
					}
				}
			}
		// handle viruses
		for (Integer ij : viruses) {
			// parse int
			int i = ij / COLS;
			int j = ij % COLS;
			// TODO generate destination
			int iNew = i + RandomUtils.generateStep();
			int jNew = j + RandomUtils.generateStep();
			// move virus
			if (newPopulation[rMod(iNew, ROWS)][rMod(jNew, COLS)] == Cell.VIRUS) {
				System.err.println("BURST!!!");
				newPopulation[i][j] = Cell.EMPTY;
				newPopulation[rMod(iNew, ROWS)][rMod(jNew, COLS)] = Cell.EMPTY;
				// burst itself
				newPopulation[rMod(iNew - 2, ROWS)][rMod(jNew, COLS)] = Cell.VIRUS;
				newPopulation[rMod(iNew + 2, ROWS)][rMod(jNew, COLS)] = Cell.VIRUS;
				newPopulation[rMod(iNew, ROWS)][rMod(jNew - 2, COLS)] = Cell.VIRUS;
				newPopulation[rMod(iNew, ROWS)][rMod(jNew + 2, COLS)] = Cell.VIRUS;
			} else {
				newPopulation[i][j] = Cell.EMPTY;
				newPopulation[rMod(iNew, ROWS)][rMod(jNew, COLS)] = Cell.VIRUS;
			}
		}
		
			
		field = newPopulation;
		populationCount = newCount;
		virusesCount = viruses.size();
		if (isEmpty()) throw new PopulationIsDeadException("Game over!");
		// TODO handle stability
	}
	
	private boolean isEmpty() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (field[i][j].isLive()) return false;
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
		count += (field[rMod(i + 1, ROWS)][j].isLive()) ? 1 : 0;
		count += (field[rMod(i - 1, ROWS)][j] .isLive()) ? 1 : 0;
		count += (field[rMod(i + 1, ROWS)][rMod(j + 1, COLS)] .isLive()) ? 1 : 0;
		count += (field[rMod(i - 1, ROWS)][rMod(j + 1, COLS)] .isLive()) ? 1 : 0;
		count += (field[rMod(i + 1, ROWS)][rMod(j - 1, COLS)] .isLive()) ? 1 : 0;
		count += (field[rMod(i - 1, ROWS)][rMod(j - 1, COLS)] .isLive()) ? 1 : 0;
		count += (field[i][rMod(j + 1, COLS)] .isLive()) ? 1 : 0;
		count += (field[i][rMod(j - 1, COLS)] .isLive()) ? 1 : 0;
		return count;
	}
	
	private int calculateLive(Cell[][] population) {
		int count = 0;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (population[i][j].isLive()) count++;
			}
		}
		return count;
	}
	
	private int calculateCells(Cell[][] population, Cell cell) {
		int count = 0;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (population[i][j] == cell) count++;
			}
		}
		return count;
	}
	
	private String print(int i, int j) {
		return field[i][j].getSymbol();
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
	
	public Cell[][] getField() {
		return field;	// TODO return copy
	}
	
	public int getLiveCount() {
		return populationCount;
	}
	
	public int getVirusCount() {
		return virusesCount;
	}
	
}
