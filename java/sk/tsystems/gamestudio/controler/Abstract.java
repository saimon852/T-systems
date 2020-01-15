package sk.tsystems.gamestudio.controler;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.service.CommentsService;
import sk.tsystems.gamestudio.service.RatingService;
import sk.tsystems.gamestudio.service.ScoreService;


public abstract class Abstract {
	String message;
	String message1;

	@Autowired
	public MainController mainController;

	@Autowired
	public ScoreService scoreService;

	@Autowired
	public CommentsService commentsService;

	@Autowired
	public RatingService ratingService;
	

	public String getMessage() {
		return message;
	}

	public String getMessage1() {
		return message1;
	}
}
