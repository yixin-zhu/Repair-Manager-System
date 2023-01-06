package project.Repo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import project.model.entity.Complaint;

import java.util.Collection;
import java.util.List;

@Component
public interface ComplaintRepo extends JpaRepository<Complaint, Integer> {

    public List<Complaint> findByRepairID(int i);
}
