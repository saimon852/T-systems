package sk.tsystems.gamestudio.controler;

import java.util.Formatter;





import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Rating;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.minesweeper.core.Clue;
import sk.tsystems.gamestudio.game.minesweeper.core.Field;
import sk.tsystems.gamestudio.game.minesweeper.core.GameState;
import sk.tsystems.gamestudio.game.minesweeper.core.Mine;
import sk.tsystems.gamestudio.game.minesweeper.core.Tile;
import sk.tsystems.gamestudio.game.minesweeper.core.Tile.State;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.service.CommentsService;
import sk.tsystems.gamestudio.service.RatingService;
import sk.tsystems.gamestudio.service.ScoreService;



@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MinesweeperController {
	private Field field;
	private boolean marking;
	
	@Autowired
	private MainController mainController;
	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private CommentsService commentsService;

	@Autowired
	private RatingService ratingService;
	
	
	
	
	
	@RequestMapping("/minesweeper")
	public String index() {
		field = new Field(9, 9, 2);
		return "minesweeper";
	}
	@RequestMapping("/minesweeper/ratingbad1")
	public String addRating(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), "Minesweeper", 1));


		return "minesweeper";
	}
	@RequestMapping("/minesweeper/ratingmedium1")
	public String addRating1(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), "Minesweeper", 2));


		return "minesweeper";
	}
	@RequestMapping("/minesweeper/ratingsuper1")
	public String addRating2(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), "Minesweeper", 3));
		

	
		return "minesweeper";
	}

	@RequestMapping("/minesweeper/opened")
	public String opened(int row, int column) {
		if (field.getState() == GameState.PLAYING)
			if (marking)
				field.markTile(row, column);
			else
				field.openTile(row, column);
		if (field.getState()==GameState.SOLVED && mainController.isLogged()) {
			scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(), "Minesweeper", field.getScore1()));
		}
		return "minesweeper";
	}
	

	@RequestMapping("/minesweeper/change")
	public String change() {
		marking = !marking;
		return "minesweeper";
	}
	@RequestMapping("/minesweeper/comment")
	public String addComment(Comment comment) {
		if (mainController.isLogged())
			commentsService
					.addComment(new Comment(mainController.getLoggedPlayer().getName()+ ": ", "Minesweeper", comment.getContent()));

		return "minesweeper";
	}
	
	

	public String getHtmlField() {
		Formatter ff = new Formatter();
		ff.format("<table>\n");
		for (int row = 0; row < field.getRowCount(); row++) {
			ff.format("<tr>\n");
			for (int column = 0; column < field.getColumnCount(); column++) {
				ff.format("<td>\n");
				Tile tile = field.getTile(row, column);
				if (tile != null) {
					if (tile.getState() == State.CLOSED) {
						ff.format(
								"<a href='/minesweeper/opened?row=%d&column=%d'><img src='/image/mines/%s.png'></img></a>",
								row, column, getImageName(tile));
					}
					if (tile.getState() == State.MARKED) {
						ff.format(
								"<a href='/minesweeper/opened?row=%d&column=%d'><img src='/image/mines/marked.png'></img></a>",
								row, column);
					}

					if (tile.getState() == State.OPEN) {
						if (tile instanceof Mine) {
							ff.format("<img src='/image/mines/mine.png'></img>");
						} else if (tile instanceof Clue) {
							ff.format("<img src='/image/mines/open%d.png'></img>", ((Clue) tile).getValue());
						}
					}
				}
				ff.format("</td>\n");
			}
			ff.format("</tr>\n");
		}
		ff.format("</table>\n");
		return ff.toString();

	}

	private Object getImageName(Tile tile) {
		switch (tile.getState()) {
		case CLOSED:
			return "closed";
		case MARKED:
			return "marked";
		case OPEN:
			if (tile instanceof Clue)
				return "open" + ((Clue) tile).getValue();
			else
				return "mine";
		default:
			throw new IllegalArgumentException();

		}
	}

	public boolean isMarking() {
		return marking;

	}
	public boolean isSolved() {
		return field.isSolved();
	}
	public List<Comment> getComments() {
		return commentsService.getComments("Minesweeper");
	}
	public List<Score> getScores() {
		return scoreService.getToScore("Minesweeper");
	}
	public List<Rating> getRating() {
		return ratingService.getRating("Minesweeper");
	}
	public double getAverageRating() {
		return ratingService.getAverageRating("Minesweeper");
	}
	
}
