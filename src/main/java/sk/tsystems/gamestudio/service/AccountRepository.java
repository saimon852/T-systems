package sk.tsystems.gamestudio.service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sk.tsystems.gamestudio.entity.Rating;

@Repository
public interface AccountRepository extends JpaRepository<Rating, String> {


}