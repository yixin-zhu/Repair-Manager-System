package project.model.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name="repairID")
    private int repairID;

    @Column(name="inTime")
    private int inTime;

    @Column(name="score")
    private int score;

    @Column(name="satisfation")
    private int satisfation;

    public int getRepairID() {
        return repairID;
    }

    public void setRepairID(int repairID) {
        this.repairID = repairID;
    }

    public int getInTime() {
        return inTime;
    }

    public void setInTime(int inTime) {
        this.inTime = inTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSatisfation() {
        return satisfation;
    }

    public void setSatisfation(int satisfation) {
        this.satisfation = satisfation;
    }
}
