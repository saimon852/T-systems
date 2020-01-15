package sk.tsystems.gamestudio.game.TicTacToe1.core;

import sk.tsystems.gamestudio.game.TicTacToe1.core.Tile;

public class Field {
	private static final long startTime = System.currentTimeMillis();

	private final int rowCount;

	private final int columnCount;

	public String[][] tiles;

	private int rowEmpty;

	private int columnEmpty;
	
	
	private String regex = "\\s{3}";
	
	public void TicTacToe() {
		tiles = new String[rowCount][columnCount];
	
	}

	

	public Field(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		tiles = new String[rowCount][columnCount];
	}


	public int getScore() {
		int seconds = (int) ((System.currentTimeMillis() - startTime) / 1000);
		int number = 2000;
		int score = number - seconds*3;
		return score;

	}


	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public String getTile(int row, int column) {
		return tiles[row][column];
	}


	public boolean isSolved() {
		for (int rows = 0; rows < rowCount; rows++) {
			if (!tiles[rows][0].matches(regex) && tiles[rows][0].equals(tiles[rows][1]) && tiles[rows][1].equals(tiles[rows][2])) {
				return true;
			}
		}
		for (int columns = 0; columns < columnCount; columns++) {
			if (!tiles[0][columns].matches(regex) && tiles[0][columns].equals(tiles[1][columns]) && tiles[1][columns].equals(tiles[2][columns]))
				return true;
		}
		if (!tiles[0][0].matches(regex) && tiles[0][0].equals(tiles[1][1]) && tiles[1][1].equals(tiles[2][2]))
			return true;
		if (!tiles[0][2].matches(regex) && tiles[0][2].equals(tiles[1][1]) && tiles[1][1].equals(tiles[2][0]))
			return true;
		return false;
 
	}
	public void move(int rows, int columns, String player) {
		if (this.tiles[rows][columns].matches(regex))
			tiles[rows][columns] = " " + player + " ";
	}
 
	
}
