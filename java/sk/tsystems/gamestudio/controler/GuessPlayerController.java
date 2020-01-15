package sk.tsystems.gamestudio.controler;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.npuzzle.core.Tile;
import sk.tsystems.gamestudio.service.CommentsService;
import sk.tsystems.gamestudio.service.RatingService;
import sk.tsystems.gamestudio.service.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class GuessPlayerController extends Abstract {
	String game = "GuessPlayer";
	String message1;
	String temp;
	String temp1;
	private String[] words = { "Messi", "Ronaldo","Fernandinho","Neymar","Kroos","Ozil","Ramos" };
	private String word = words[(int) (Math.random()*words.length*1)];
	private String asterisk = new String(new char[word.length()]).replace("\0", "*");
	private int count = 0;
	

	final long startMillis = System.currentTimeMillis();

	@RequestMapping("/guessplayer")
	public String index() {
		message = "Guess Player";
		temp = asterisk;
		temp1="";
		if(word=="Messi") {
			temp1="Argentina/Barcelona";
			
		}if(word=="Fernandinho") {
			temp1="Brazil/Manchester City";
			
		}if(word=="Ronaldo") {
			temp1="Portugal/Juventus";
			}if(word=="Ramos") {
				temp1="Spain/Real Madrid";
				
			}if(word=="Kroos") {
				temp1="Germany/Real Madrid";
				
			}if(word=="Ozil") {
				temp1="Germany/Arsenal";
			}if(word=="Neymar") {
				temp1="Brazil/PSG";
			
		}
		return "guessplayer";

	}

	@RequestMapping("/guessplayer/guesss")
	public String hangman(String guesss) {
		try {
			String parseString = String.format(guesss, word);
			hang(parseString);
			if (message.equals("Correct! You win!") && mainController.isLogged()) {
				scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(), game, getScore()));
			}
			if (message.equals("You Loose") && mainController.isLogged()) {
				scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(), game, 0));
			}
		} catch (Exception e) {
			message="Wrong format";
		}
		return "guessplayer";
	}
	@RequestMapping("/guessplayer/next")
	public String next() {
		count=0;
		message="Guess Player";
		for(int word=0;word<words.length;word++);
		String[] words = { "Messi", "Ronaldo","Fernandinho","Neymar","Kroos","Ozil","Ramos" };
		word = words[(int) (Math.random()*words.length*1)];
		asterisk = new String(new char[word.length()]).replace("\0", "*");
		temp=asterisk;
		if(word=="Messi") {
			temp1="Argentina/Barcelona";
			
		}if(word=="Fernandinho") {
			temp1="Brazil/Manchester City";
			
		}if(word=="Ronaldo") {
			temp1="Portugal/Juventus";
			}if(word=="Ramos") {
				temp1="Spain/Real Madrid";
				
			}if(word=="Kroos") {
				temp1="Germany/Real Madrid";
				
			}if(word=="Ozil") {
				temp1="Germany/Arsenal";
			}if(word=="Neymar") {
				temp1="Brazil/PSG";
			
		}
		
		
	
		return "guessplayer";
	}
		private void hang(String value) {
			String tip = "";
			for (int i = 0; i < word.length(); i++) {
				for (int j = 0; j < value.length(); j++) {
					if (word.charAt(i) == value.charAt(0)) {
					temp = tip += value.charAt(0);
					}else if (word.charAt(i) == value.charAt(j)) {
						temp = value;
				} else if (asterisk.charAt(i) != '*') {
					temp = tip += word.charAt(i);
				} else {
					temp = tip += "*";
				}
				}
			}
			

			if (asterisk.equals(tip)) {
				count++;
				message = ("Wrong number"+" "+ count);
			} else {
				asterisk = tip;
				message = "Awesome";
			}
			if (asterisk.equals(word)) {
				message = "Correct! You win!";
			}
			else if(count>=7) {message="You Loose";
			}
			
			if (value==temp) {
				message="Correct! You win!";
			}
			if (value!=temp) {
				message="Wrong";
			}
		
	}


		


	@RequestMapping("/guessplayer/comment")
	public String addComment(Comment comment) {
		String content = comment.getContent();

		if (content.length() > 30)
			message1 = "Comment is too long";

		else if (mainController.isLogged() && content != "")
			commentsService.addComment(
					new Comment(mainController.getLoggedPlayer().getName() + ": ", game, comment.getContent()));

		return "guessplayer";
	}

	@RequestMapping("/guessplayer/ratingbad")
	public String addRating(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 1));

		return "guessplayer";
	}

	@RequestMapping("/guessplayer/ratingmedium")
	public String addRating1(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 2));

		return "guessplayer";
	}

	@RequestMapping("/guessplayer/ratingsuper")
	public String addRating2(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 3));

		return "guessplayer";
	}


	public String getMessage() {
		return message;
	}

	public String getTemp() {
		return temp;
	}

	public String getTemp1() {
		return temp1;
	}

	public String getMessage1() {
		return message1;
	}

	public List<Score> getScores() {
		return scoreService.getToScore(game);
	}

	public List<Comment> getComments() {
		return commentsService.getComments(game);
	}

	public List<Rating> getRating() {
		return ratingService.getRating(game);

	}

	public int getScore() {
		int start=100;
		int score = start - count*5;
		return score;
	}

	public double getAverageRating() {
		return ratingService.getAverageRating(game);
	}

}
