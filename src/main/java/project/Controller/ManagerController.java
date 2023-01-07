package project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import project.Service.ComplaintService;
import project.model.entity.Complaint;
import project.model.entity.Report;

import java.util.List;

@Service
public class ManagerController {
    @Autowired
    ComplaintService complaintService;

    public List<Complaint> showComplaints(){
        return complaintService.findAllComplaints();
    }

    public List<Report> showReportsByComplaint(int complaintID){
        return complaintService.getReportsByComplaint(complaintID);
    }

    public Complaint closeComplaint(int complaintID){
        return complaintService.updateStateOfComplaint(complaintID, 0);
    }
}
