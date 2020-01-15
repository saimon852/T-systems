package sk.tsystems.gamestudio.game.TicTacToe1.core;

import sk.tsystems.gamestudio.game.TicTacToe1.consoleui.ConsoleUI;
import sk.tsystems.gamestudio.game.TicTacToe1.core.Field;

public class Main {

	public static void main(String[] args) {
		ConsoleUI ui = new ConsoleUI();
		Field field = new Field(8, 8);
		ui.playGame(field);
	}
}