package sk.tsystems.gamestudio.consoleui.Menu;

import java.util.Scanner;





import sk.tsystems.gamestudio.game.guessnumber.consoleui.ConsoleUI;
import sk.tsystems.gamestudio.game.guessnumber.core.Main;
import sk.tsystems.gamestudio.consoleui.Menu.Game;
import sk.tsystems.gamestudio.game.npuzzle.core.Field;
import sk.tsystems.gamestudio.game.npuzzle.consoleui.ConsoleUI1;
import sk.tsystems.gamestudio.game.npuzzle.core.Main1;
import sk.tsystems.gamestudio.consoleui.Menu.*;



public class GamestudioMenu {



	private static final String[] games = {"GuessNumber","Puzzle","Minesweeper" };

	public void run() {
		while (true) {
			try {
				String user = System.getProperty("user.name");
				System.out.println("Welcome to the GAMESTUDIO " + user + " , Choose your game:");

				Scanner scanner = new Scanner(System.in);
				for (int i = 0; i < games.length; i++) {
					System.out.println((i + 1) + ". " + games[i]);
				}

				int input = Integer.parseInt(scanner.nextLine());
				if (input == 00) {

				}
				if (input > 0 && input <= games.length) {
				} else {
					System.out.println("Game Doesnt Exist");
				}
				switch (input) {
				case 1:
					System.out.println("GuessNumber starting...");
					System.out.println("---------------------");
					ConsoleUI ui = new ConsoleUI();
					ui.play();
					break;
				
				case 2:
					System.out.println("Puzzle starting...");
					System.out.println("---------------------");
					ConsoleUI1 ui1 = new ConsoleUI1();
					Field field = new Field(4, 4);
					ui1.playGame(field);
					
					break;
				
				case 3:
					System.out.println("Minesweepers starting...");
					System.out.println("---------------------");
				}return;
				

			} catch (NumberFormatException e) {
				System.err.println("Wrong input, Enter game number");
			}
		}
	}
	}

