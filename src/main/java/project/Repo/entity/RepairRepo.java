package project.Repo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import project.model.entity.Rating;
import project.model.entity.Repair;

@Component
public interface RepairRepo extends JpaRepository<Repair, Integer> {

}
