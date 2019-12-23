package sk.tsystems.gamestudio.game.minesweeper;

import sk.tsystems.gamestudio.game.minesweeper.consoleui.ConsoleUI;


import sk.tsystems.gamestudio.game.minesweeper.core.Field;

public class Minesweeper {
   
    private UserInterface userInterface;
 
   
    private Minesweeper() {
        userInterface = new ConsoleUI();
        
        Field field = new Field(9,9,10);
        userInterface.newGameStarted(field);
    }


    public static void main(String[] args) {
        new Minesweeper();
    }
}

