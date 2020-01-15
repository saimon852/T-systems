package sk.tsystems.gamestudio.controler;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Player;
import sk.tsystems.gamestudio.service.PlayerService;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MainController {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd    HH:mm");
	LocalDateTime now = LocalDateTime.now();
	private Player loggedPlayer;
	private String message;

	@Autowired
	private PlayerService playerService;
	
	
	@RequestMapping("/index1") 
		public String color() {
		return "/index1";
	}
	
	@RequestMapping("/index2") 
	public String color1() {
	return "/index2";
}

	

	@RequestMapping("/register")

	public String index(Player player) {
		String password = player.getPassword();
		String name = player.getName();
		String password1 = player.getPassword1();
		List<Player> users = listAllUsers();
		boolean userExist = false;
		for (Player u : users) {
			if (u.getName().equals(name)) {
				message = "Zadajte iné meno";
				userExist = true;
			}
		}

		if (!userExist && name != "" && Player.isValid(password) && password.equals(password1)) {
			message = "Registrácia prebehla úspešne!";
			playerService.addPlayer(new Player(player.getName(), player.getPassword(), player.getPassword1()));

		}

		else if (!userExist && name == "" && password == "")
			message = "Meno ani Heslo nesmie byt prázdne";

		else if (!userExist && password != password1)
			message = "Heslo je nesprávne,skúste to znova";
		

		return "redirect:/";
	}


	@RequestMapping("/login")
	public String login(Player player) {
		String name = player.getName();
		String password = player.getPassword();
		Player database = (Player) playerService.getPlayers(name);
		if (database != null)
			if (password.equals(database.getPassword())) {
				loggedPlayer = player;

			} else {
				message = "Neplatné prihlasovacie údaje";

			}

		return "redirect:/";

	}

	@RequestMapping("/logout")
	public String logout() {
		loggedPlayer = null;
		message = "Použivatel odhlasený";
		return "redirect:/";

	}

	public boolean isLogged() {
		return loggedPlayer != null;

	}

	public Player getLoggedPlayer() {
		return loggedPlayer;
	}

	public Player getLoggedPlayer1() {
		return loggedPlayer;
	}

	public List<Player> listAllUsers() {
		return playerService.userList();
	}

	public String getMessage() {
		return message;
	}

	public String getNow() {
		return (dtf.format(now));
	}
	
	
	}


