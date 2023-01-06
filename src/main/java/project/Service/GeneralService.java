package project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.Repo.entity.RepairRepo;
import project.model.entity.Repair;

import java.util.List;

@Service
public class GeneralService {

    @Autowired
    RepairRepo repairRepo;

    public int sumTimeByRepair(int repairID) {
        Repair repair = repairRepo.getById(repairID);
        return 0;
    }
}
