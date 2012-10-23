package com.mindbar.life.utils;

import com.mindbar.life.model.Cell;

// TODO get rid of this class. Just temporary for teesting purposes
public final class CellConvertor {
	private CellConvertor() { }
	
	public final Cell[][] fromIntArray(int[][] array) {
		Cell[][] cells = new Cell[array.length][array[0].length];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				cells[i][j] = Cell.fromCode(array[i][j]);
			}
		}
		return cells;
	}
}
