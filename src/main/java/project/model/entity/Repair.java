package project.model.entity;

import javax.persistence.*;

@Entity
@Table(name="repair")
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name="time")
    private String time;

    @Column(name="content")
    private String content;

    @Column(name="ownerID")
    private int ownerID;

    @Column(name="origin")
    private String origin;

    // 0 finnish 1 waiting 2 working
    @Column(name="state")
    private int state;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
