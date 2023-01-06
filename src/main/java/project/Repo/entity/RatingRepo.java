package project.Repo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import project.model.entity.Complaint;
import project.model.entity.Rating;

@Component
public interface RatingRepo extends JpaRepository<Rating, Integer> {

}
