package project.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="dispatch")
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

    public Dispatch(){

    }

    public Dispatch(Timestamp beginTime, Timestamp endTime, int repairID, int state, int schedulerID, int workerID) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.repairID = repairID;
        this.state = state;
        this.schedulerID = schedulerID;
        this.workerID = workerID;
    }

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
