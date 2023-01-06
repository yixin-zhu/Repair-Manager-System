package project.Repo.people;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import project.model.entity.Rating;
import project.model.people.Scheduler;

@Component
public interface SchedulerRepo extends JpaRepository<Scheduler, Integer> {

}
