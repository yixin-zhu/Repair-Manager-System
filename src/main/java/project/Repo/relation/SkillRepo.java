package project.Repo.relation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import project.model.entity.Rating;
import project.model.relation.Skill;

@Component
public interface SkillRepo extends JpaRepository<Skill, Integer> {

}
