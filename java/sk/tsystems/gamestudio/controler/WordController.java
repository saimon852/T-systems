package sk.tsystems.gamestudio.controler;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.entity.Score;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class WordController extends Abstract {
	private String[] words = { "dom", "strom", "auto", "pes" };
	private String word = words[(int) (Math.random() * words.length)];
	String game = "Word";
	final long startMillis = System.currentTimeMillis();
	String message2="";

	@RequestMapping("/word")
	public String index() {
		message1="Hadaj slovo po anglicky";
		message = word;
		if(word=="dom") {
			message="House";
			
		}if(word=="strom") {
			message="Tree";
			
		}if(word=="auto") {
			message="Car";
		}if(word=="Pes") {
			message="Dog";
			
		}
		return "/word";

	}
	@RequestMapping("/word/generate")
	public String index1() {
		message1="";
		String[] words = { "dom", "strom", "auto", "pes" };
		word = words[(int) (Math.random() * words.length)];
		if(word=="dom") {
			message="House";
			
		}if(word=="strom") {
			message="Tree";
			
		}if(word=="auto") {
			message="Car";
		}if(word=="Pes") {
			message="Dog";
			
		}
		return "/word";

	}

	@RequestMapping("/word/guesss")
	public String hangman(String guesss) {
		try {
			String parseString = String.format(guesss, word);
			hang(parseString);
			if (message1.equals("You win!") && mainController.isLogged()) {
				scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(), game, getScore()));
			}
		} catch (Exception e) {
			message = "Wrong format";
		}
		return "/word";
	}

	private void hang(String value) {
		if (value.equals(word)) {
			message1 = "You win!";		
		}else {message1="Hadaj dalej";
		}
	
	}

	@RequestMapping("/word/comment")
	public String addComment(Comment comment) {
		String content = comment.getContent();

		if (content.length() > 30)
			message2 = "Comment is too long";

		else if (mainController.isLogged() && content != "")
			commentsService.addComment(
					new Comment(mainController.getLoggedPlayer().getName() + ": ", game, comment.getContent()));

		return "/word";
	}

	@RequestMapping("/word/ratingbad")
	public String addRating(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 1));

		return "/word";
	}

	@RequestMapping("/word/ratingmedium")
	public String addRating1(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 2));

		return "/word";
	}

	@RequestMapping("/word/ratingsuper")
	public String addRating2(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 3));

		return "/word";
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
	public String getMessage2() {
		return message2;
	}

	public int getScore() {
		int time = (int) ((System.currentTimeMillis() - startMillis) / 1000);
		int number = 1000;
		int score = number - time * 5;
		return score;

	}

	public double getAverageRating() {
		return ratingService.getAverageRating(game);
	}
}
