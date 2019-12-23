package sk.tsystems.gamestudio;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.service.ScoreService;
import sk.tsystems.gamestudio.service.ScoreServiceJDBC;

public class Main {

	public static void main(String[] args) {
		ScoreService scoreService = new ScoreServiceJDBC();
		scoreService.addScore(new Score("jaro","npuzzle",123));
		for (Score score : scoreService.getToScore("npuzzle"))
			System.out.println(score.getUsername()+ " "+ score.getValue());
				
		
		

	}

}
