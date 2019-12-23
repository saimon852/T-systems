package sk.tsystems.gamestudio.game.npuzzle.core;

import sk.tsystems.gamestudio.game.npuzzle.consoleui.ConsoleUI1;


public class Main1 {

	public static void main(String[] args) {
		ConsoleUI1 ui1 = new ConsoleUI1();
		Field field = new Field(4, 4);
		ui1.playGame(field);
	}
}
