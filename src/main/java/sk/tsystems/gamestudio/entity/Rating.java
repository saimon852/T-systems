package sk.tsystems.gamestudio.entity;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Rating {
	
	@GeneratedValue
	@Id
	private int ident;
	private String name;
	private int rating;
	private String game;


	public Rating() {

	}

	public Rating(String name,String game, int rating) {
		super();
		this.name = name;
		this.game= game;
		this.rating=rating;
	
		
	}

	

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	

	

	

}
