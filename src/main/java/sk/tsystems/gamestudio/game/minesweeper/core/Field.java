package sk.tsystems.gamestudio.game.minesweeper.core;

import java.util.Random;

public class Field {

	private final Tile[][] tiles;

	private final int rowCount;

	private final int columnCount;

	private final int mineCount;

	private int numberOfOpenTiles;

	private int score;

	private static final long startTime = System.currentTimeMillis();

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	private GameState state = GameState.PLAYING;

	public Field(int rowCount, int columnCount, int mineCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.mineCount = mineCount;
		tiles = new Tile[rowCount][columnCount];

		generate(mineCount);
	}

	public GameState getState() {
		return state;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getMineCount() {
		return mineCount;
	}

	public Tile getTile(int row, int column) {
		return tiles[row][column];
	}

	public void openTile(int row, int column) {

		Tile tile = tiles[row][column];

		if (tile.getState().equals(Tile.State.CLOSED)) {
			tile.setState(Tile.State.OPEN);
			numberOfOpenTiles++;

			if (tile instanceof Mine) {
				state = GameState.FAILED;
				return;
			}
			if (tile instanceof Clue && ((Clue) tile).getValue() == 0) {
				openNext(row, column);
			}
		}
		if (isSolved()) {
			state = GameState.SOLVED;
			return;
		}
	}

	private void openNext(int row, int column) {
		if (exist(row - 1, column - 1))
			openTile(row - 1, column - 1);
		if (exist(row - 1, column))
			openTile(row - 1, column);
		if (exist(row - 1, column + 1))
			openTile(row - 1, column + 1);
		if (exist(row, column - 1))
			openTile(row, column - 1);
		if (exist(row, column + 1))
			openTile(row, column + 1);
		if (exist(row + 1, column - 1))
			openTile(row + 1, column - 1);
		if (exist(row + 1, column))
			openTile(row + 1, column);
		if (exist(row + 1, column + 1))
			openTile(row + 1, column + 1);
	}

	private boolean exist(int row, int column) {

		return row >= 0 && row < rowCount && column >= 0 && column < columnCount;

	}

	public void markTile(int row, int column) {

		Tile tile = tiles[row][column];
		if (tile.getState() == Tile.State.CLOSED) {
			tile.setState(Tile.State.MARKED);
		} else if (tile.getState() == Tile.State.MARKED) {
			tile.setState(Tile.State.CLOSED);
		}
	}

	private void generate(int n) {
		Random r = new Random();
		int counter = 0;
		while (counter < mineCount) {
			int randomRow = r.nextInt(rowCount);
			int randomColumn = r.nextInt(columnCount);
			if (tiles[randomRow][randomColumn] == null) {
				tiles[randomRow][randomColumn] = new Mine();
				counter++;
			}
		}
		for (int f = 0; f < rowCount; f++) {
			for (int s = 0; s < columnCount; s++) {
				if (tiles[f][s] == null) {
					tiles[f][s] = new Clue(countAdjacentMines(f, s));
				}

			}
		}
	}

	public boolean isSolved() {

		if ((rowCount * columnCount) - getNumberOf(Tile.State.OPEN) == mineCount)
			return true;
		else
			return false;

	}

	private int getNumberOf(Tile.State state) {
		int count = 0;
		for (int row = 0; row < rowCount; row++) {
			for (int columnx = 0; columnx < columnCount; columnx++) {
				if (tiles[row][columnx].getState() == state) {
					count++;
				}
			}
		}
		return count;
	}

	private int countAdjacentMines(int row, int column) {
		int count = 0;
		for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
			int actRow = row + rowOffset;
			if (actRow >= 0 && actRow < rowCount) {
				for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
					int actColumn = column + columnOffset;
					if (actColumn >= 0 && actColumn < columnCount) {
						if (tiles[actRow][actColumn] instanceof Mine) {
							count++;
						}
					}
				}
			}
		}

		return count;
	}

	public int getScore1() {
		if(state == GameState.SOLVED) {
		int seconds = (int) ((System.currentTimeMillis() - startTime) / 1000);
		int score = (columnCount * rowCount * mineCount)*30 - seconds*5;
		return score;
	} else {
		return 0;
	}
}
}
