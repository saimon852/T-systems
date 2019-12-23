package sk.tsystems.gamestudio.service;

import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import sk.tsystems.gamestudio.entity.Rating;

@Component
@Transactional
public class RatingServiceJPA implements RatingService {
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Rating> getRating(String game) {
		return (List<Rating>) entityManager
				.createQuery("select r from Rating r where r.game = :game order by r.rating desc ")
				.setParameter("game", game).setMaxResults(10).getResultList();

	}

	@Override
	public void setRating(Rating rating) {
		try {
			Rating dbRating = (Rating) entityManager
					.createQuery("select r from Rating r where r.name = :name and r.game= :game")
					.setParameter("name", rating.getName()).setParameter("game", rating.getGame()).getSingleResult();
			dbRating.setRating(rating.getRating());
		} catch (NoResultException e) {
			entityManager.persist(rating);
		}
	}

	@Override
	public double getAverageRating(String game) {
			Double dbRating = (Double) entityManager
					.createQuery("select AVG(r.rating) from Rating r where r.game = :game").setParameter("game", game)
					.getSingleResult();
			return dbRating.doubleValue();
		
	}
}