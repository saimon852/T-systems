package sk.tsystems.gamestudio.controler;

import java.util.Formatter;




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
public class GuessNumberController extends Abstract{
	private int number;
	final long startMillis = System.currentTimeMillis();
	String game = "GuessNumber";

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
				scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(), game, getScore()));
			}

		} catch (Exception e) {

		}

		return "guessnumber";

	}

	@RequestMapping("/guessnumber/comment")
	public String addComment(Comment comment) {
		String content = comment.getContent();
		if (content.length() > 30)
			message1="Comment is too long";
		else if (mainController.isLogged()&& content != "")
			commentsService.addComment(
					new Comment(mainController.getLoggedPlayer().getName() + ": ", game, comment.getContent()));

		return "guessnumber";
	}

	@RequestMapping("/guessnumber/ratingbad")
	public String addRating(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 1));

		return "guessnumber";
	}

	@RequestMapping("/guessnumber/ratingmedium")
	public String addRating1(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 2));

		return "guessnumber";
	}

	@RequestMapping("/guessnumber/ratingsuper")
	public String addRating2(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 3));

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
		int time = (int) ((System.currentTimeMillis() - startMillis) / 1000);
		int number = 1000;
		int score = number - time * 5;
		return score;

	}

	public double getAverageRating() {
		return ratingService.getAverageRating(game);
	}

}
