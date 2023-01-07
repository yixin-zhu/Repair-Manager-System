package project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.Repo.entity.DispatchRepo;
import project.Repo.entity.LogRepo;
import project.Repo.entity.RepairRepo;
import project.model.entity.Dispatch;
import project.model.entity.Log;
import project.model.entity.Repair;

import java.sql.Timestamp;
import java.util.List;

@Service
public class GeneralService {

    @Autowired
    RepairRepo repairRepo;

    @Autowired
    DispatchRepo dispatchRepo;

    @Autowired
    LogRepo logRepo;

    public int sumTimeByRepair(int repairID) {
        Repair repair = repairRepo.getById(repairID);
        List<Dispatch> dispatches = dispatchRepo.findByRepairID(repair.getID());
        int ans = 0;
        for(Dispatch d : dispatches){
            List<Log> logs = logRepo.findByDispatchID(d.getID());
            for(Log l : logs){
                ans += l.getHours();
            }
        }
        return ans;
    }

    public int sumTimeByWorkerInRecent(int workerID, Timestamp begin, Timestamp end) {
        List<Dispatch> dispatches = dispatchRepo.findByWorkerID(workerID);
        int ans = 0;
        for(Dispatch d : dispatches){
            List<Log> logs = logRepo.findByDispatchID(d.getID());
            for(Log l : logs){
                if(begin.before(l.getBeginTime()) && end.after(l.getEndTime())){
                    ans += l.getHours();
                }
            }
        }
        return ans;
    }
}
