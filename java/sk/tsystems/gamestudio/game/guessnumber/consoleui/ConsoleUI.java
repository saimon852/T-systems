package sk.tsystems.gamestudio.game.guessnumber.consoleui;

import java.util.Scanner;

public class ConsoleUI {

	final long startMillis = System.currentTimeMillis();

	public void play() {

		processInput();

	}

	public void processInput() {
		int number = (int) (Math.random() * 1000);

		Scanner scenner = new Scanner(System.in);
		Long startTime = System.currentTimeMillis();
		String user = System.getProperty("user.name");
		System.out.println("1-1000");

		while (true) {
			try {
				int tip;
				System.out.print("Hadaj cislo: ");

				tip = Integer.parseInt(scenner.nextLine());

				if (tip == number) {
					System.out.println("Uhadol si " + user + " tvoj cas je "
							+ (System.currentTimeMillis() - startTime) / 1000 + " sekund");
					return;
				} else if (tip > number)
					System.out.println("Nizsie");
				else
					System.out.println("Vyssie");

			} catch (Exception e) {
				System.err.println("Zadaj cislo");
			}
		}
	}
}
