package sk.tsystems.gamestudio.game.npuzzle.core;

import java.util.Random;

import java.util.Scanner;

import sk.tsystems.gamestudio.game.minesweeper.core.GameState;

public class Field {
	private static final long startTime = System.currentTimeMillis();

	private final int rowCount;

	private final int columnCount;

	private final Tile[][] tiles;

	private int rowEmpty;

	private int columnEmpty;

	public Field(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		tiles = new Tile[rowCount][columnCount];
		generate();
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

	public Tile getTile(int row, int column) {
		return tiles[row][column];
	}

	public void generate() {
		int index = 1;
		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnCount; column++) {
				tiles[row][column] = new Tile(index);
				index++;
			}
		}
		rowEmpty = rowCount - 1;
		columnEmpty = columnCount - 1;
		tiles[rowEmpty][columnEmpty] = null;

		shuffle();
	}

	public void shuffle() {
		Random random = new Random();
		for (int i = 0; i < rowCount * columnCount * 1; i++)
			move(random.nextInt(rowCount * columnCount - 1) + 1);
	}

	public void move(int tileNumber) {
		if (rowEmpty > 0 && tiles[rowEmpty - 1][columnEmpty].getValue() == tileNumber) {
			exchangeWithEmptyTile(-1, 0);
		} else if (rowEmpty < rowCount - 1 && tiles[rowEmpty + 1][columnEmpty].getValue() == tileNumber) {
			exchangeWithEmptyTile(1, 0);
		} else if (columnEmpty > 0 && tiles[rowEmpty][columnEmpty - 1].getValue() == tileNumber) {
			exchangeWithEmptyTile(0, -1);
		} else if (columnEmpty < columnCount - 1 && tiles[rowEmpty][columnEmpty + 1].getValue() == tileNumber) {
			exchangeWithEmptyTile(0, 1);
		}
	}

	private void exchangeWithEmptyTile(int deltaRow, int deltaColumn) {
		tiles[rowEmpty][columnEmpty] = tiles[rowEmpty + deltaRow][columnEmpty + deltaColumn];
		rowEmpty = rowEmpty + deltaRow;
		columnEmpty = columnEmpty + deltaColumn;
		tiles[rowEmpty][columnEmpty] = null;
	}

	public boolean isSolved() {
		int index = 1;
		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnCount; column++) {
				Tile tile = tiles[row][column];
				if (tile != null && tile.getValue() != index)
					return false;
				index++;
			}
		}

		return true;
	}

}