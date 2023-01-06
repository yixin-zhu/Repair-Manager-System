package project.Repo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import project.model.entity.Complaint;
import project.model.entity.Log;

import java.util.Collection;
import java.util.List;

@Component
public interface LogRepo extends JpaRepository<Log, Integer> {

    public List<Log> findByDispatchID(int id);
}
