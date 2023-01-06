package project.model.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

public class Dispatch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name="beginTime")
    private Timestamp beginTime;

    @Column(name="endTime")
    private Timestamp endTime;

    @Column(name="repairID")
    private int repairID;

    // 0 inactive 1 active
    @Column(name="state")
    private int state;

    @Column(name="schedulerID")
    private int schedulerID;

    @Column(name="workerID")
    private int workerID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = Timestamp.valueOf(beginTime);
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = Timestamp.valueOf(endTime);
    }

    public int getRepairID() {
        return repairID;
    }

    public void setRepairID(int repairID) {
        this.repairID = repairID;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSchedulerID() {
        return schedulerID;
    }

    public void setSchedulerID(int schedulerID) {
        this.schedulerID = schedulerID;
    }

    public int getWorkerID() {
        return workerID;
    }

    public void setWorkerID(int workerID) {
        this.workerID = workerID;
    }
}
