package project.model.relation;

import javax.persistence.*;

@Entity
@Table(name="skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name="workerID")
    private int workerID;

    @Column(name="faultID")
    private int faultID;

    public int getWorkerID() {
        return workerID;
    }

    public void setWorkerID(int workerID) {
        this.workerID = workerID;
    }

    public int getFaultID() {
        return faultID;
    }

    public void setFaultID(int faultID) {
        this.faultID = faultID;
    }


}
