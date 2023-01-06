package project.Repo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import project.model.entity.Complaint;
import project.model.entity.Fault;

@Component
public interface FaultRepo extends JpaRepository<Fault, Integer> {

}
