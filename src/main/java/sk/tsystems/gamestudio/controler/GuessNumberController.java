package sk.tsystems.gamestudio.controler;
 
import java.util.Formatter;

import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.service.CommentsService;
import sk.tsystems.gamestudio.service.RatingService;
import sk.tsystems.gamestudio.service.ScoreService;
import sk.tsystems.gamestudio.game.npuzzle.core.Field;
 
@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class GuessNumberController {
	private int number;
	private String message;
	private Field field;
	final long startMillis=System.currentTimeMillis();
	
	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private MainController mainController;
	@Autowired
	private CommentsService commentsService;

	@Autowired
	private RatingService ratingService;
 
	@RequestMapping("/guessnumber")
	public String index() {
		message = "";
		number = (int) (Math.random() * 1000 + 1);
		return "guessnumber";
	}
 
	@RequestMapping("/guessnumber/guess")
	public String guess(String value) {
		try {
			int parseValue = Integer.parseInt(value);
			evaluate(parseValue);
			if (message.equals("You won") && mainController.isLogged()) {
				scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(), "GuessNumber", getScore()));
			}
 
		} catch (Exception e) {
 
		}
	
		return "guessnumber";
 
	}
	@RequestMapping("/guessnumber/comment")
	public String addComment(Comment comment) {
		if (mainController.isLogged())
			commentsService.addComment(
					new Comment(mainController.getLoggedPlayer().getName() + ": ", "GuessNumber", comment.getContent()));

		return "guessnumber";
	}

	@RequestMapping("/guessnumber/ratingbad")
	public String addRating(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), "GuessNumber", 1));

		return "guessnumber";
	}

	@RequestMapping("/guessnumber/ratingmedium")
	public String addRating1(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), "GuessNumber", 2));

		return "guessnumber";
	}

	@RequestMapping("/guessnumber/ratingsuper")
	public String addRating2(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), "GuessNumber", 3));

		return "guessnumber";
	}
 
	public void evaluate(int value) {
		
		
		
		if (value < number) {
			message = "Thats too small";
 
		} else if (value > number) {
			message = "Thats too big";
 
		} else {
			message = "You won";
			}
		}
		
	
	
	public String getMessage() {
		return message;
	}
	public List<Score> getScores() {
		return scoreService.getToScore("GuessNumber");
	}
	public List<Comment> getComments() {
		return commentsService.getComments("GuessNumber");
	}

	public List<Rating> getRating() {
		return ratingService.getRating("GuessNumber");

	}
	public int getScore() {
		int time = (int) ((System.currentTimeMillis() - startMillis) / 1000);
		int number = 1000;
		int score = number - time*5;
		return score;
 
	}
	public double getAverageRating() {
		return ratingService.getAverageRating("GuessNumber");
	}
	
}
