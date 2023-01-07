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
}
