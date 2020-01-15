package sk.tsystems.gamestudio.game.GuessPlayer;

import java.util.Scanner;

import sk.tsystems.gamestudio.controler.GuessPlayerController;
import sk.tsystems.gamestudio.game.GuessPlayer.*;


public class ConsoleUI {
	

	public static String[] words = {"Me", "Ronaldo"};
	public static String word = words[(int) (Math.random() * words.length)];
	public static String asterisk = new String(new char[word.length()]).replace("\0", "_");
	public static int count = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (count < 7 && asterisk.contains("_")) {
			System.out.println("Guess any letter in the word");
			System.out.println(asterisk);
			String guess = sc.next();
			hang(guess);
		}
		sc.close();
	}

	public static void hang(String guess) {
		String newasterisk = "";
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == guess.charAt(0)) {
			newasterisk += guess.charAt(0);
			} else if (asterisk.charAt(i) != '_') {
	newasterisk += word.charAt(i);
			} else {
				newasterisk += "_";
			}
		}

		if (asterisk.equals(newasterisk)) {
			count++;
			System.out.println(("Zle")+count);
		} else {
			asterisk = newasterisk;
			System.out.println("Dobre");
		}
		if (asterisk.equals(word)) {
			System.out.println("Correct! You win! The word was " + word);
		}
	}

	public static void hangmanImage() {
		if (count == 1) {
			System.out.println("Wrong guess, try again");

		}
		if (count == 2) {
			System.out.println("Wrong guess, try again");

		}
		if (count == 3) {

		}
		if (count == 4) {
			System.out.println("Wrong guess, try again");
		}
		if (count == 5) {
			System.out.println("Wrong guess, try again");
		}
		if (count == 6) {
			System.out.println("Wrong guess, try again");
		}
		if (count == 7) {
			System.out.println("GAME OVER!");
		}
	}
}