package project.Repo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import project.model.entity.Complaint;

@Component
public interface ComplaintRepo extends JpaRepository<Complaint, Integer> {

}
