package sk.tsystems.gamestudio.entity;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Player {
	@GeneratedValue
	private int ident;
	@Id
	private String name;
	private String password;
	private String password1;


	public Player() {

	}

	public Player(String name, String password,String password1) {
		super();
		this.name = name;
		this.password = password;
		this.password1 = password1;
	}
	

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public static boolean isValid(String password) {

	    if (password.length() < 7) {
	        return false;
	    } else {

	        for (int p = 0; p < password.length(); p++) {
	            if (Character.isUpperCase(password.charAt(p))) {
	            }
	        }
	        for (int q = 0; q < password.length(); q++) {
	            if (Character.isLowerCase(password.charAt(q))) {
	            }
	        }
	        for (int r = 0; r < password.length(); r++) {
	            if (Character.isDigit(password.charAt(r))) {
	            }
	        } 
	            }
	            return true;
	        }
	}

