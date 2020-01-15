package sk.tsystems.gamestudio.game.guessnumber.core;

import java.util.Scanner;

public class Field {
	final long startMillis=System.currentTimeMillis();
	
	
	public int getScore() {
		int time = (int) ((System.currentTimeMillis() - startMillis) / 1000 );
		int number = 2000;
		int score = number - time*3;
		return score;
 
	}
	
}
