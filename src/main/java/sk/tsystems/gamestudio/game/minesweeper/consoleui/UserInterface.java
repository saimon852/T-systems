package sk.tsystems.gamestudio.game.minesweeper.consoleui;


import sk.tsystems.gamestudio.game.minesweeper.core.Field;

public interface UserInterface {

	
	void newGameStarted(Field field);

	
	void update();

}