package sk.tsystems.gamestudio.service;

import java.util.List;



import sk.tsystems.gamestudio.entity.Comment;

public interface CommentsService {

	void addComment(Comment comments);

	List<Comment> getComments(String game);


}
