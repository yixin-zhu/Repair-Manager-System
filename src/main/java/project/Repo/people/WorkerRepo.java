package project.Repo.people;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import project.model.entity.Rating;
import project.model.people.Worker;

import java.util.List;

@Component
public interface WorkerRepo extends JpaRepository<Worker, Integer> {

    public List<Worker> findByState(int i);
}
