package sk.tsystems.gamestudio.controler;
import java.util.Formatter;

import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.npuzzle.consoleui.ConsoleUI1;
import sk.tsystems.gamestudio.game.npuzzle.core.Field;
import sk.tsystems.gamestudio.game.npuzzle.core.Tile;
import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Rating;


@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PuzzleController extends Abstract {
	private Field field = new Field(4, 4);
	String game="Npuzzle";
	

	@RequestMapping("/puzzle")
	public String index() {
		return "puzzle";
	}
	@RequestMapping("/puzzle/generate")
	public String generate() {
		field.shuffle();
		return "puzzle";
	}

	@RequestMapping("/comment")
	public String addComment(Comment comment) {
		String content = comment.getContent();
		
		if (content.length() > 30)
			message="Comment is too long";
		if (mainController.isLogged() && content.equals(""))
			message="Empty Comment";

		else if (mainController.isLogged() && content != "")
			commentsService.addComment(
					new Comment(mainController.getLoggedPlayer().getName() + ": ", game, comment.getContent()));

		return "puzzle";
	}
	

	@RequestMapping("/puzzle/ratingbad")
	public String addRating(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 1));

		return "puzzle";
	}

	@RequestMapping("/puzzle/ratingmedium")
	public String addRating1(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 2));

		return "puzzle";
	}

	@RequestMapping("/puzzle/ratingsuper")
	public String addRating2(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(),game, 3));

		return "puzzle";
	}

	@RequestMapping("/puzzle/move")
	public String move(int tile) {
		field.move(tile);
		if (field.isSolved() && mainController.isLogged()) {
			scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(),game, field.getScore()));
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
		return scoreService.getToScore(game);
	}

	public List<Comment> getComments() {
		return commentsService.getComments(game);
	}

	public List<Rating> getRating() {
		return ratingService.getRating(game);
	}
	public double getAverageRating(){
		return ratingService.getAverageRating(game);
	}
}
