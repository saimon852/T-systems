//package sk.tsystems.gamestudio.service;
//
//import java.sql.DriverManager;
//
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.List;
//
//import sk.tsystems.gamestudio.entity.Comments;
//import sk.tsystems.gamestudio.entity.Player;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
////CREATE TABLE score (username VARCHAR(64) NOT NULL, game VARCHAR(64) NOT NULL,value INT NOT NULL)
//
//public class PlayerServiceJDBC implements PlayerService {
//	private static final String URL = " jdbc:postgresql://localhost/gamestudio";
//	private static final String LOGIN = "postgres";
//	private static final String PASSWORD = "jahodka";
//	private static final String INSERT = "INSERT INTO player (name,password) VALUES (?,?)";
//	private static final String SELECT = "SELECT name,password FROM player WHERE name =?";
//
//	
//
//	public List<Player> getPlayers(String name) {
//		try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
//				PreparedStatement ps = connection.prepareStatement(SELECT)) {
//			ps.setString(1, name);
//			try (ResultSet rs = ps.executeQuery()) {
//				List<Player> players = new ArrayList<>();
//				while (rs.next()) {
//					Player player = new Player(rs.getString(1), rs.getString(2));
//					players.add(player);
//				}
//				return players;
//			}
//
//		} catch (SQLException e) {
//			throw new GameStudioException(e);
//		}
//	}
//
//	@Override
//	public void addPlayer(Player player) {
//		try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
//				PreparedStatement ps = connection.prepareStatement(INSERT)) {
//			ps.setString(1, player.getName());
//			ps.setString(2, player.getPassword());
//			ps.executeUpdate();
//
//		} catch (SQLException e) {
//			throw new GameStudioException(e);
//		}
//
//	}
//
//		
//	
//
//
//}
