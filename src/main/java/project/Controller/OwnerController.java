package project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import project.Service.ComplaintService;
import project.Service.DispatchService;
import project.model.entity.Complaint;
import project.model.entity.Rating;
import project.model.entity.Repair;

import java.util.List;

@Service
public class OwnerController {
    @Autowired
    DispatchService dispatchService;

    @Autowired
    ComplaintService complaintService;

    public Complaint createComplaint(Complaint complaint){
        return complaintService.insertComplaint(complaint);
    }

    public Rating createRating(Rating rating){
        return dispatchService.insertRating(rating);
    }

    public List<Repair> showMyRepairs(int ownerID){
        return dispatchService.getRepairByOwner(ownerID);
    }
}
