package project.model.entity;

import javax.persistence.*;

@Entity
@Table(name="report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name="complaintID")
    private int complaintID;

    @Column(name="content")
    private String content;

    public Report(){

    }

    public Report(int complaintID, String content) {
        this.complaintID = complaintID;
        this.content = content;
    }

    public int getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(int complaintID) {
        this.complaintID = complaintID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
