package project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.Repo.entity.*;
import project.Repo.people.WorkerRepo;
import project.Repo.relation.SkillRepo;
import project.model.entity.*;
import project.model.people.Worker;
import project.model.relation.Skill;

import java.util.ArrayList;
import java.util.List;

@Service
public class DispatchService {
    @Autowired
    RepairRepo repairRepo;

    @Autowired
    DispatchRepo dispatchRepo;

    @Autowired
    LogRepo logRepo;

    @Autowired
    WorkerRepo workerRepo;

    @Autowired
    ComplaintRepo complaintRepo;

    @Autowired
    RatingRepo ratingRepo;

    @Autowired
    SkillRepo skillRepo;

    public Repair insertRepair(Repair r){
        return repairRepo.save(r);
    }

    public List<Repair> getRepairByState(int i) {
        return repairRepo.findByState(1);
    }

    public Dispatch insertDispatch(Dispatch dispatch) {
        return dispatchRepo.save(dispatch);
    }

    public Repair updateRepairState(int repairID, int newState) {
        Repair repair = repairRepo.getOne(repairID);
        repair.setState(newState);
        return repairRepo.save(repair);
    }

    public Dispatch updateDispatchState(int dispatchID, int newState) {
        Dispatch dispatch = dispatchRepo.getById(dispatchID);
        dispatch.setState(newState);
        return dispatchRepo.save(dispatch);
    }

    public Worker updateWorkerState(int workerID, int newState) {
        Worker worker = workerRepo.getById(workerID);
        worker.setState(newState);
        return workerRepo.save(worker);
    }

    public int getStateOfRepair(int repairID) {
        Repair repair = repairRepo.getById(repairID);
        return repair.getState();
    }

    public List<Dispatch> getDispatchByRepair(int repairID) {
        return dispatchRepo.findByRepairID(repairID);
    }

    public List<Dispatch> getDispatchByWorkerID(int workerID) {
        return dispatchRepo.findByWorkerID(workerID);
    }

    public List<Log> getLogByRepair(int repairID) {
        List<Log> ans = new ArrayList<>();
        List<Dispatch> dispatches= dispatchRepo.findByRepairID(repairID);
        for(Dispatch d : dispatches){
            ans.addAll(logRepo.findByDispatchID(d.getID()));
        }
        return ans;
    }

    public List<Worker> getWorkerByState(int i) {
        return workerRepo.findByState(i);
    }


    public Log insertLog(Log log) {
        return logRepo.save(log);
    }


    public Rating insertRating(Rating rating) {
        return ratingRepo.save(rating);
    }

    public List<Repair> getRepairByOwner(int ownerID) {
        return repairRepo.findByOwnerID(ownerID);
    }

    public List<Worker> getWorkerBySkill(int fault) {
        List<Skill> skills = skillRepo.findByFaultID(fault);
        List<Worker> ans = new ArrayList<>();
        for(Skill s : skills){
            ans.addAll(workerRepo.findByID(s.getWorkerID()));
        }
        return ans;
    }

    public int findWorkerIDByDispatch(int dispatchID) {
        Dispatch dispatch = dispatchRepo.getById(dispatchID);
        return dispatch.getWorkerID();
    }
}
