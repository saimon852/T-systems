//package sk.tsystems.gamestudio.service;
//
//
//
//	import java.sql.DriverManager;
//
//	import java.sql.SQLException;
//	import java.sql.Statement;
//	import java.sql.Connection;
//	import java.util.ArrayList;
//	import java.util.List;
//
//import sk.tsystems.gamestudio.entity.Comment;
//	import java.sql.PreparedStatement;
//	import java.sql.ResultSet;
//
//	//CREATE TABLE score (username VARCHAR(64) NOT NULL, game VARCHAR(64) NOT NULL,value INT NOT NULL)
//
//	public class CommentsServiceJDBC implements CommentsService {
//		private static final String URL = " jdbc:postgresql://localhost/gamestudio";
//		private static final String LOGIN = "postgres";
//		private static final String PASSWORD = "jahodka";
//		private static final String INSERT = "INSERT INTO comments (username,game,content) VALUES (?,?,?)";
//		private static final String SELECT = "SELECT username,game,content FROM comments WHERE game =?";
//
//		public void addComment(Comment comments) {
//			try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
//					PreparedStatement ps = connection.prepareStatement(INSERT)) {
//				ps.setString(1, comments.getUsername());
//				ps.setString(2, comments.getGame());
//				ps.setString(3, comments.getContent());
//				ps.executeUpdate();
//
//			} catch (SQLException e) {
//				throw new GameStudioException(e);
//			}
//
//		}
//
//		public List<Comment> getComments(String game) {
//			try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
//					PreparedStatement ps = connection.prepareStatement(SELECT)) {
//				ps.setString(1, game);
//				try (ResultSet rs = ps.executeQuery()) {
//					List<Comment> comments = new ArrayList<>();
//					while (rs.next()) {
//						Comment comment = new Comment (rs.getString(1), rs.getString(2), rs.getString(3));
//						comments.add(comment);
//					}
//					return comments;
//				}
//
//			} catch (SQLException e) {
//				throw new GameStudioException(e);
//			}
//		}
//
//
//}
