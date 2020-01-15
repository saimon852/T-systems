package sk.tsystems.gamestudio.service;

import java.util.List;

import javax.persistence.NoResultException;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Rating;

public interface RatingService {

	void setRating(Rating rating);

	double getAverageRating(String game);

	List<Rating> getRating(String game);



	
	

}
