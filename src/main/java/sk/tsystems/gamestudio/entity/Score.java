package sk.tsystems.gamestudio.entity;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Score {
	@Id
	@GeneratedValue
	private int ident;

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	private String username;
	private String game;
	private int value;

	public Score() {

	}

	public Score(String name, String game, int value) {
		super();
		this.username = name;
		this.game = game;
		this.value = value;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Score [ident=" + ident + ", username=" + username + ", game=" + game + ", value=" + value
				+ ", getIdent()=" + getIdent() + ", getUsername()=" + getUsername() + ", getGame()=" + getGame()
				+ ", getValue()=" + getValue() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
