package sk.tsystems.gamestudio.game.minesweeper.consoleui;



import java.io.BufferedReader;






import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import sk.tsystems.gamestudio.game.minesweeper.UserInterface;
import sk.tsystems.gamestudio.game.minesweeper.core.Clue;
import sk.tsystems.gamestudio.game.minesweeper.core.Field;
import sk.tsystems.gamestudio.game.minesweeper.core.GameState;
import sk.tsystems.gamestudio.game.minesweeper.core.Mine;
import sk.tsystems.gamestudio.game.minesweeper.core.Tile;
import sk.tsystems.gamestudio.game.minesweeper.core.Tile.State;


public class ConsoleUI implements UserInterface {
	
	private Field field;

	
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	

	public void newGameStarted(Field field) {
		this.field = field;
		Long startTime = System.currentTimeMillis();
		String user = System.getProperty("user.name");
		do {
			update();
			processInput();
		} while (field.getState()==GameState.PLAYING);
		update();
		
		if(field.getState()==GameState.FAILED){
		System.out.println("LOSE");
	}else if(field.getState()==GameState.SOLVED) {
		
		System.out.println("WIN"+ user +" tvoj cas je "+(System.currentTimeMillis() - startTime) / 1000 +" sekund");
	}
	}

	
	
	public void update() {
		System.out.print("   ");
		for (int column =0; column < field.getColumnCount(); column++) {
			System.out.printf(" %d ", column);
		}
		System.out.println();
		for (int row = 0; row < field.getRowCount(); row++) {
			System.out.printf(" %c ", 'A'+ row);
			
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTile(row, column);
				if (tile.getState() == State.OPEN) {
					if (tile instanceof Mine) {
						System.out.printf("MINE!");
					} else if (tile instanceof Clue) {
						System.out.printf(" %d ", ((Clue) tile).getValue());
					}
				} else if (tile.getState() == State.CLOSED) {
					System.out.printf(" * ");
				} else if (tile.getState() == State.MARKED) {
					System.out.printf(" M ");
				}

			}
			System.out.println();
		}
	}
	



	private void processInput() {

		System.out.println(" Enter your choice X=EXIT, MA1=MARK, OB4=OPEN: ");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine().toUpperCase().trim();
		
		try {
			if ("X".equals(name))
				System.exit(0);
			if (name.matches("[OM][A-I][0-8]")) {
				int row = name.charAt(1) - 'A';
				int column = name.charAt(2) - '0';
				if (name.startsWith("O"))
					field.openTile(row, column);
				else
					field.markTile(row, column);

			}
		} catch (NumberFormatException e) {
			System.err.println("Neplatny pokus");

		}
	}
}
