package project.Repo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import project.model.entity.Rating;
import project.model.entity.Report;

import java.util.List;

@Component
public interface ReportRepo extends JpaRepository<Report, Integer> {

    public List<Report> findByComplaintID(int complaintID);
}
