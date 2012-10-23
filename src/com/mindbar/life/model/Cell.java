package com.mindbar.life.model;

/**
 * Enum represents cell state.
 * @author mishadoff
 *
 */
public enum Cell {
	EMPTY(0),
	LIVE(1);
	
	private int code;
	
	private Cell(int code) {
		this.code = code;
	}
	
	/**
	 * Factory method to create cell from code.
	 * @param code
	 * @return
	 */
	public static Cell fromCode(int code) {
		for (Cell cell : Cell.values()) {
			if (cell.code == code) {
				return cell;
			}
		}
		// Do not throw exception, just return dead cell if code not found
		return Cell.EMPTY;
	}
}
