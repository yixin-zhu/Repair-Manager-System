package project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.Repo.entity.ComplaintRepo;
import project.Repo.entity.DispatchRepo;
import project.Repo.entity.ReportRepo;
import project.model.entity.Complaint;
import project.model.entity.Dispatch;
import project.model.entity.Report;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplaintService {
    @Autowired
    DispatchRepo dispatchRepo;

    @Autowired
    ComplaintRepo complaintRepo;

    @Autowired
    ReportRepo reportRepo;

    public List<Complaint> getCompliantBySchedulerID(int schedulerID) {
        List<Dispatch> dispatches = dispatchRepo.findBySchedulerID(schedulerID);
        List<Integer> repairIDs = new ArrayList<>();
        List<Complaint> ans = new ArrayList<>();
        for(Dispatch d : dispatches){
            repairIDs.add(d.getRepairID());
        }
        for(int i : repairIDs){
            List<Complaint> complaints = complaintRepo.findByRepairID(i);
            for(Complaint c : complaints){
                if(c.getState()>0){
                    ans.add(c);
                }
            }
        }
        return ans;
    }

    public List<Complaint> getCompliantByWorkerID(int workerID) {
        List<Dispatch> dispatches = dispatchRepo.findByWorkerID(workerID);
        List<Integer> repairIDs = new ArrayList<>();
        List<Complaint> ans = new ArrayList<>();
        for(Dispatch d : dispatches){
            repairIDs.add(d.getRepairID());
        }
        for(int i : repairIDs){
            List<Complaint> complaints = complaintRepo.findByRepairID(i);
            for(Complaint c : complaints){
                if(c.getState()>0){
                    ans.add(c);
                }
            }
        }
        return ans;
    }

    public Report insertReport(Report report) {
        return reportRepo.save(report);
    }

    public List<Complaint> findAllComplaints() {
        return complaintRepo.findAll();
    }

    public List<Report> getReportsByComplaint(int complaintID) {
        return reportRepo.findByComplaintID(complaintID);
    }

    public Complaint updateStateOfComplaint(int complaintID, int newState) {
        Complaint complaint = complaintRepo.getById(complaintID);
        complaint.setState(newState);
        return complaintRepo.save(complaint);
    }

    public Complaint insertComplaint(Complaint complaint) {
        return complaintRepo.save(complaint);
    }
}
