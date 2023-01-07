package project.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name="dispatchID")
    private int dispatchID;

    @Column(name="beginTime")
    private Timestamp beginTime;

    @Column(name="endTime")
    private Timestamp endTime;

    @Column(name="hours")
    private int hours;

    @Column(name="comment")
    private String comment;

    public Log(){

    }

    public Log(int dispatchID, Timestamp beginTime, Timestamp endTime, int hours, String comment) {
        this.dispatchID = dispatchID;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.hours = hours;
        this.comment = comment;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getDispatchID() {
        return dispatchID;
    }

    public void setDispatchID(int dispatchID) {
        this.dispatchID = dispatchID;
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

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
