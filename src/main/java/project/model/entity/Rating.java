package project.model.entity;

import javax.persistence.*;

@Entity
@Table(name="rating")
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

    public Rating(){

    }

    public Rating(int repairID, int inTime, int score, int satisfation) {
        this.repairID = repairID;
        this.inTime = inTime;
        this.score = score;
        this.satisfation = satisfation;
    }

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
