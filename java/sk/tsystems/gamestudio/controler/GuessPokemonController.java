package sk.tsystems.gamestudio.controler;

import java.util.List;

import java.util.Scanner;

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

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class GuessPokemonController extends Abstract {
	String game = "GuessPokemon";
	String message1;
	String temp;
	String temp1;
	private String[] pokemons = { "pikachu", "onix","raichu","charizard"};
	private String pokemon = pokemons[(int) (Math.random() * pokemons.length)];
	

	final long startMillis = System.currentTimeMillis();

	@RequestMapping("/guesspokemon")
	public String index() {
		message = "Who's That Pokémon?";
		if(pokemon=="onix") {
			temp1="<img src='/image/pokemon/onix.jpg'></img>";
			
		}if(pokemon=="pikachu") {
			temp1="<img src='/image/pokemon/pikachu.jpg'></img>";
			
		}if(pokemon=="raichu") {
			temp1="<img src='/image/pokemon/raichu.jpg'></img>";
		}
			if(pokemon=="charizard") {
				temp1="<img src='/image/pokemon/charizard.jpg'></img>";
			
		}
		return "guesspokemon";

	}
	@RequestMapping("/guesspokemon/next")
	public String nextline() {
		String[] pokemons = { "pikachu", "onix","raichu","charizard"};
		pokemon = pokemons[(int) (Math.random() * pokemons.length)];
		temp1="";
		message="Who's That Pokémon?";
		if(pokemon=="onix") {
			temp1="<img src='/image/pokemon/onix.jpg'></img>";
			
		}if(pokemon=="pikachu") {
			temp1="<img src='/image/pokemon/pikachu.jpg'></img>";
			
		}if(pokemon=="raichu") {
			temp1="<img src='/image/pokemon/raichu.jpg'></img>";
		}
			if(pokemon=="charizard") {
				temp1="<img src='/image/pokemon/charizard.jpg'></img>";
			
		}
		return "/guesspokemon";
	}

	@RequestMapping("/guesspokemon/guesss")
	public String hangman(String guesss) {
		String parseString = String.format(guesss, pokemon);
		hang(parseString);
		
		return "/guesspokemon";
	}
	@RequestMapping("/word/next")
	public String next() {
		temp=pokemon;
		return "/word";
	}
	
	private void hang(String value) {
		if (value.equals(pokemon)) {
			message="You are Pokemon Master!";
			if (message.equals("You are Pokemon Master!") && mainController.isLogged()) {
				scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(), "Pokemon", getScore()));
			}
		}else if (value!=pokemon)
		{message="Zle";
		}
	}
	

	@RequestMapping("/guesspokemon/comment")
	public String addComment(Comment comment) {
		String content = comment.getContent();

		if (content.length() > 30)
			message1 = "Comment is too long";

		else if (mainController.isLogged() && content != "")
			commentsService.addComment(
					new Comment(mainController.getLoggedPlayer().getName() + ": ", game, comment.getContent()));

		return "guesspokemon";
	}

	@RequestMapping("/guesspokemon/ratingbad")
	public String addRating(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 1));

		return "guesspokemon";
	}

	@RequestMapping("/guesspokemon/ratingmedium")
	public String addRating1(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 2));

		return "guesspokemon";
	}

	@RequestMapping("/guesspokemon/ratingsuper")
	public String addRating2(Rating rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 3));

		return "guesspokemon";
	}


	public String getMessage() {
		return message;
	}

	public String getTemp() {
		return temp;
	}

	public String getTemp1() {
		return temp1;
	}

	public String getMessage1() {
		return message1;
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
