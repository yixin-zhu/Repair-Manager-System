package project.Repo.people;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import project.model.entity.Rating;
import project.model.people.Owner;

@Component
public interface OwnerRepo extends JpaRepository<Owner, Integer> {

}
