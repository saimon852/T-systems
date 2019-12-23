package sk.tsystems.gamestudio.controler;

import java.util.Formatter;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.service.ScoreService;
import sk.tsystems.gamestudio.game.npuzzle.core.Field;
import sk.tsystems.gamestudio.game.npuzzle.core.Tile;
import sk.tsystems.gamestudio.service.CommentsService;
import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Player;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.controler.MainController;
import sk.tsystems.gamestudio.service.PlayerService;
import sk.tsystems.gamestudio.service.RatingService;
import sk.tsystems.gamestudio.entity.Rating;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PuzzleController {
	private Field field = new Field(4, 4);

	@Autowired
	private MainController mainController;

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private CommentsService commentsService;

	@Autowired
	private RatingService ratingService;

	@RequestMapping("/puzzle")
	public String index() {
		return "puzzle";
	}

	@RequestMapping("/comment")
	public String addComment(Comment comment) {
		if (mainController.isLogged())
			commentsService.addComment(
					new Comment(mainController.getLoggedPlayer().getName() + ": ", "Npuzzle", comment.getContent()));

		return "puzzle";
	}

	@RequestMapping("/puzzle/ratingbad")
	public String addRating(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), "Npuzzle", 1));

		return "puzzle";
	}

	@RequestMapping("/puzzle/ratingmedium")
	public String addRating1(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), "Npuzzle", 2));

		return "puzzle";
	}

	@RequestMapping("/puzzle/ratingsuper")
	public String addRating2(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), "Npuzzle", 3));

		return "puzzle";
	}

	@RequestMapping("/puzzle/move")
	public String move(int tile) {
		field.move(tile);
		if (field.isSolved() && mainController.isLogged()) {
			scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(), "Npuzzle", field.getScore()));
		}
		return "puzzle";
	}

	public String getHtmlField() {
		Formatter f = new Formatter();
		f.format("<table>\n");
		for (int row = 0; row < field.getRowCount(); row++) {
			f.format("<tr>\n");
			for (int column = 0; column < field.getColumnCount(); column++) {
				f.format("<td>\n");
				Tile tile = field.getTile(row, column);
				if (tile != null)
					f.format("<a href='/puzzle/move?tile=%d'><image src='/image/puzzle/img%d.jpg'></img></a>",
							tile.getValue(), tile.getValue());
				f.format("</td>\n");
			}
			f.format("</tr>\n");
		}
		f.format("</table>\n");
		return f.toString();

	}

	public boolean isSolved() {
		return field.isSolved();
	}

	public List<Score> getScores() {
		return scoreService.getToScore("Npuzzle");
	}

	public List<Comment> getComments() {
		return commentsService.getComments("Npuzzle");
	}

	public List<Rating> getRating() {
		return ratingService.getRating("Npuzzle");
	}
	public double getAverageRating(){
		return ratingService.getAverageRating("Npuzzle");
	}
}
