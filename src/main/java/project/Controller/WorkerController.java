package project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import project.Service.ComplaintService;
import project.Service.DispatchService;
import project.model.entity.*;

import java.util.List;

@Service
public class WorkerController {
    @Autowired
    DispatchService dispatchService;

    @Autowired
    ComplaintService complaintService;

    public List<Dispatch> showMyDispatches(int workerID){
        return dispatchService.getDispatchByWorkerID(workerID);
    }

    public Log createLog(Log log){
        return dispatchService.insertLog(log);
    }

    public List<Complaint> showMyCompliant(int workerID){
        return complaintService.getCompliantByWorkerID(workerID);
    }

    public Report createReport(Report report){
        return complaintService.insertReport(report);
    }
}
