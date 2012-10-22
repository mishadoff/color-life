package com.mindbar.life.model;

public final class Configurations {
	private static final int ROWS = 10; 
	private static final int COLS = 10; 
	
	public static int[][] getStatic() {
		int[][] a = new int[ROWS][COLS];
			a[2][3] = 1; a[2][4] = 1; a[3][3] = 1; a[3][4] = 1;
			a[6][6] = 1; a[6][7] = 1; a[7][6] = 1; a[7][7] = 1;
		return a;
	}
	
	public static int[][] getDynamic() {
		int[][] a = new int[ROWS][COLS];
			a[2][3] = 1; a[2][4] = 1; a[2][5] = 1;
			a[5][6] = 1; a[6][6] = 1; a[7][6] = 1;
		return a;
	}
	
	public static int[][] getGlider() {
		int[][] a = new int[ROWS][COLS];
			a[3][7] = 1; a[4][8] = 1; a[5][6] = 1; a[5][7] = 1; a[5][8] = 1;
		return a;
	}
}
