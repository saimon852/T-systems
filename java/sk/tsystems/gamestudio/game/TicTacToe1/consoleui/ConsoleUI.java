package sk.tsystems.gamestudio.game.TicTacToe1.consoleui;

import java.util.Scanner;

import sk.tsystems.gamestudio.game.TicTacToe1.core.Field;
import sk.tsystems.gamestudio.game.TicTacToe1.core.Tile;

public class ConsoleUI {
	private Scanner scanner = new Scanner(System.in);

	private Field field;
	private String[][] board;

	public void playGame(Field field) {

		this.field = field;
		Long startTime = System.currentTimeMillis();
		String user = System.getProperty("user.name");
		do {
			show();
			processInput();
		} while (!field.isSolved());

		show();
		System.out.println(
				"You won!" + user + " tvoj cas je " + (System.currentTimeMillis() - startTime) / 1000 + " sekund");
	}

	private void processInput() {
		System.out.print("Enter tile to move: ");

		try {
			String line = scanner.nextLine();
			if ("X".equals(line.toUpperCase()))
				System.exit(0);
			int tileNumber = Integer.parseInt(line);
			field.move(tileNumber, tileNumber, line);
		} catch (NumberFormatException e) {
			System.err.println("Invalid tile number!");
		}
	}

	public String show() {
		String strBoard = "";
		for (int rows = 0; rows < field.getRowCount(); rows++) {
			for (int columns = 0; columns < field.getColumnCount(); columns++) {
				if (columns != field.getColumnCount() - 1)
					strBoard += field.tiles[rows][columns] + "|";
				else
					strBoard += field.tiles[rows][columns];
			}
			if (rows != field.getColumnCount() - 1)
				strBoard += "\n---+---+---|\n";
		}
		return strBoard;
	}

}
