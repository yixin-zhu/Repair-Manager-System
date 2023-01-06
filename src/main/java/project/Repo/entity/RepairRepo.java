package project.Repo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import project.model.entity.Rating;
import project.model.entity.Repair;

import java.util.List;

@Component
public interface RepairRepo extends JpaRepository<Repair, Integer> {

    List<Repair> findByState(int i);

    List<Repair> findByOwnerID(int ownerID);
}
