package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import minesweeper.UserInterface;
import minesweeper.core.Clue;
import minesweeper.core.Field;
import minesweeper.core.Mine;
import minesweeper.core.Tile;
import minesweeper.core.Tile.State;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
	/** Playing field. */
	private Field field;

	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Starts the game.
	 * 
	 * @param field field of mines and clues
	 */
	@Override
	public void newGameStarted(Field field) {
		this.field = field;
		do {
			update();
			processInput();
//            throw new UnsupportedOperationException("Resolve the game state - winning or loosing condition.");
		} while (true);
	}

	/**
	 * Updates user interface - prints the field.
	 */
	@Override
	public void update() {
		System.out.print("   ");
		for (int column =0; column < field.getColumnCount(); column++) {
			System.out.printf("%d ", column);
		}
		System.out.println();
		for (int row = 0; row < field.getRowCount(); row++) {
			System.out.printf(" %c ", 'A' + row);
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTiles(row, column);
				if (tile.getState() == State.OPEN) {
					if (tile instanceof Mine) {
						System.out.printf("X ");
					} else if (tile instanceof Clue) {
						System.out.printf("%d ", ((Clue) tile).getValue());
					}
				} else if (tile.getState() == State.CLOSED) {
					System.out.printf("-");
				} else if (tile.getState() == State.MARKED) {
					System.out.printf(" M");
				}

			}
			System.out.println();
		}
	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void processInput() {

		System.out.println("Please enter your select <X> Exit, <MA1> Mark, <OB4> Open :");
		String s = readLine();
		Pattern pattern = Pattern.compile("^([xXoOmM]+ ^([A-Z,a-z]+^[0-9]");
		Matcher matcher = pattern.matcher("s");
	}
}
