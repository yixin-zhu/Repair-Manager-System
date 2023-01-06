package project.Repo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import project.model.entity.Complaint;
import project.model.entity.Dispatch;

import java.util.List;

@Component
public interface DispatchRepo extends JpaRepository<Dispatch, Integer> {

    public List<Dispatch> findByRepairID(int repairID);

    public List<Dispatch> findBySchedulerID(int schedulerID);

    public List<Dispatch> findByWorkerID(int workerID);
}