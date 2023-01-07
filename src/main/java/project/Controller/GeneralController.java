package project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import project.Service.ComplaintService;
import project.Service.DispatchService;
import project.Service.GeneralService;
import project.model.entity.Repair;

import java.sql.Timestamp;

@Service
public class GeneralController {
    @Autowired
    GeneralService generalService;

    public int sumTimeByRepair(int repairID){
        return generalService.sumTimeByRepair(repairID);
    }

    public int sumTimeByWorkerInRecent(int workerID, Timestamp begin, Timestamp end){
        return generalService.sumTimeByWorkerInRecent(workerID, begin, end);
    }

}
