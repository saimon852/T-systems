package sk.tsystems.gamestudio.game.minesweeper;


import sk.tsystems.gamestudio.game.minesweeper.core.Field;

public interface UserInterface {

	void newGameStarted(Field field);

	void update();

}