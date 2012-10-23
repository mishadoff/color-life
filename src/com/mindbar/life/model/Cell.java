package com.mindbar.life.model;

/**
 * Enum represents cell state.
 * @author mishadoff
 *
 */
public enum Cell {
	EMPTY(0, "."),
	LIVE(1, "#"),
	VIRUS(2, "@"),
	
	R(3, "R"),
	G(4, "G"),
	B(5, "B"),
	
	;
	
	private int code;
	private String symbol;
	
	private Cell(int code, String symbol) {
		this.code = code;
		this.symbol = symbol;
	}
	
	public boolean isLive() {
		// TODO implement with private static set
		return this == LIVE || this == R || this == G || this == B;
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
	
	public int getCode() {
		return code;
	}
	
	public String getSymbol() {
		return symbol;
	}
}
