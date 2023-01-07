package project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import project.Service.ComplaintService;
import project.Service.DispatchService;
import project.model.entity.*;
import project.model.people.Worker;

import java.util.List;

@Service
public class SchedulerController {
    @Autowired
    DispatchService dispatchService;

    @Autowired
    ComplaintService complaintService;

    public Repair createRepair(Repair repair){
        return dispatchService.insertRepair(repair);
    }

    public Report createReport(Report report){
        return complaintService.insertReport(report);
    }
    public List<Repair> showWaitingRepairs(){
        return dispatchService.getRepairByState(1);
    }

    public Dispatch createDispatch(Dispatch dispatch){
        int repairID = dispatch.getRepairID();
        dispatchService.updateRepairState(repairID, 2);
        return dispatchService.insertDispatch(dispatch);
    }

    public Dispatch closeDispatch(int dispatchID){
        return dispatchService.updateDispatchState(dispatchID, 0);
    }

    public Repair closeRepair(int repairID){
        return dispatchService.updateRepairState(repairID, 0);
    }

    public int showStateOfRepair(int repairID){
        return dispatchService.getStateOfRepair(repairID);
    }

    public List<Dispatch> showRelatedDispatchByRepair(int repairID){
        return dispatchService.getDispatchByRepair(repairID);
    }

    public List<Log> showRelatedLogByRepair(int repairID){
        return dispatchService.getLogByRepair(repairID);
    }

    public List<Worker> showFreeWorkersInfo(){
        return dispatchService.getWorkerByState(0);
    }

    public List<Worker> showWorkersInfoBySkill(int fault){
        return dispatchService.getWorkerBySkill(fault);
    }

    public List<Complaint> showMyCompliant(int schedulerID){
        return complaintService.getCompliantBySchedulerID(schedulerID);
    }
}
