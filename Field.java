package minesweeper.core;

import java.util.Random;

/**
 * Field represents playing field and game logic.
 */
public class Field {
	/**
	 * Playing field tiles.
	 */
	private final Tile[][] tiles;

	/**
	 * Field row count. Rows are indexed from 0 to (rowCount - 1).
	 */
	private final int rowCount;

	/**
	 * Column count. Columns are indexed from 0 to (columnCount - 1).
	 */
	private final int columnCount;

	/**
	 * Mine count.
	 */
	private final int mineCount;

	/**
	 * Game state.
	 */
	private GameState state = GameState.PLAYING;

	/**
	 * Constructor.
	 *
	 * @param rowCount    row count
	 * @param columnCount column count
	 * @param mineCount   mine count
	 */
	public Field(int rowCount, int columnCount, int mineCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.mineCount = mineCount;
		tiles = new Tile[rowCount][columnCount];

		// generate the field content
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

	public Tile getTiles(int row, int column) {
		return tiles[row][column];
	}

	/**
	 * Opens tile at specified indeces.
	 *
	 * @param row    row number
	 * @param column column number
	 */
	public void openTile(int row, int column) {
		Tile tile = tiles[row][column];
		if (tile.getState() == Tile.State.CLOSED) {
			tile.setState(Tile.State.OPEN);
			if (tile instanceof Mine) {
				state = GameState.FAILED;
				return;
			}

			if (isSolved()) {
				state = GameState.SOLVED;
				return;
			}
		}
	}

	/**
	 * O
	 *
	 * @param row    row number
	 * @param column column number
	 */
	public void markTile(int row, int column) {
		Tile tile = tiles[row][column];
		if (tile.getState() == Tile.State.CLOSED) {
			tile.setState(Tile.State.MARKED);
		} else if (tile.getState() == Tile.State.MARKED) {
			tile.setState(Tile.State.CLOSED);
		}
	}

	/**
	 * Generates playing field.
	 * 
	 * @return
	 */
	private void generate(int n) {
		Random r1 = new Random();
		int counter = 0;
		while (counter < mineCount) {
			int randomRow = r1.nextInt(rowCount);
			int randomColumn = r1.nextInt(columnCount);
			if (tiles[randomRow][randomColumn] == null) {
				tiles[randomRow][randomColumn] = new Mine();
				counter++;

			}
		}
		for (int r = 0; r < rowCount; r++) {
			for (int s = 0; s < columnCount; s++) {
				if (tiles[r][s] == null) {
					tiles[r][s] = new Clue(countAdjacentMines(r, s));

				}
			}
		}
	}

	/**
	 * Returns true if game is solved, false otherwise.
	 *
	 * @return true if game is solved, false otherwise
	 */
	private boolean isSolved() {
		if ((rowCount*columnCount)-getNumberOf(Tile.State.OPEN)==mineCount)
return true;
else return false ;
	}


	int getNumberOf(Tile.State state) {
		int count = 0;
		for (int rowi = 0; rowi < rowCount; rowi++) {
			for (int columni = 0; columni < columnCount; columni++) {
				if (tiles[rowi][columni].getState() == state) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Returns number of adjacent mines for a tile at specified position in the
	 * field.
	 *
	 * @param row    row number.
	 * @param column column number.
	 * @return number of adjacent mines.
	 */
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

}
