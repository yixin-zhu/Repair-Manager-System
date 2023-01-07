package extend;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.Controller.ManagerController;
import project.Controller.OwnerController;
import project.Controller.SchedulerController;
import project.Controller.WorkerController;
import project.SpringBootApp;
import project.model.entity.Complaint;
import project.model.entity.Dispatch;
import project.model.entity.Repair;
import project.model.entity.Report;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayName("TestMultipleDispatch")
@SpringBootTest(classes = SpringBootApp.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestExtend4 {
    private static int repairID = 101; // any number here is ok

    private static int dispatchID = 102; // any number here is ok

    private static int complaintID = 103; // any number here is ok

    @Autowired
    SchedulerController schedulerController;

    @Autowired
    WorkerController workerController;

    @Autowired
    OwnerController ownerController;

    @Autowired
    ManagerController managerController;

    @Test
    @Order(1)
    @DisplayName("Scheduler can create Repair")
    void testCreateRepair(){
        Repair r = new Repair("2023-01-04 10:00:00", "no water", 1, "wechat", 1);
        Repair expected = schedulerController.createRepair(r);
        assertSame(r, expected);
        repairID = expected.getID();
    }

    @Test
    @Order(2)
    @DisplayName("Scheduler can create Dispatch")
    void testCreateDispatch(){
        Dispatch d = new Dispatch(Timestamp.valueOf("2023-01-04 11:00:00"), Timestamp.valueOf("2023-01-10 23:00:00"), repairID, 1, 1, 2);
        Dispatch expected = schedulerController.createDispatch(d);
        assertSame(d, expected);
        dispatchID = expected.getID();
    }

    @Test
    @Order(3)
    @DisplayName("Owner can create Complaint")
    void testCreateComplaint(){
        Complaint c = new Complaint(repairID, "not friendly", "2023-01-04 12:00:00", "", 1, 1);
        Complaint expected = ownerController.createComplaint(c);
        assertSame(c, expected);
        complaintID = expected.getID();
    }

    @Test
    @Order(4)
    @DisplayName("Scheduler can show complaints to her/him")
    void testSchedulerShowCompliant(){
        List<Complaint> complaints = schedulerController.showMyCompliant(1);
        assertEquals(1, complaints.size());
        Complaint expected = new Complaint(repairID, "not friendly", "2023-01-04 12:00:00", "", 1, 1);
        assertEquals(expected.getRepairID(), repairID);
        assertEquals(expected.getContent(), complaints.get(0).getContent());
        assertEquals(expected.getTime(), complaints.get(0).getTime());
        assertEquals(expected.getOwnerID(), complaints.get(0).getOwnerID());
        assertEquals(expected.getState(), complaints.get(0).getState());
    }

    @Test
    @Order(5)
    @DisplayName("Worker can show complaints to her/him")
    void testWorkerShowCompliant(){
        List<Complaint> complaints = workerController.showMyCompliant(2);
        assertEquals(1, complaints.size());
        Complaint expected = new Complaint(repairID, "not friendly", "2023-01-04 12:00:00", "", 1, 1);
        assertEquals(expected.getRepairID(), repairID);
        assertEquals(expected.getContent(), complaints.get(0).getContent());
        assertEquals(expected.getTime(), complaints.get(0).getTime());
        assertEquals(expected.getOwnerID(), complaints.get(0).getOwnerID());
        assertEquals(expected.getState(), complaints.get(0).getState());
    }

    @Test
    @Order(6)
    @DisplayName("Scheduler can create Report")
    void testSchedulerCreateReport(){
        Report r = new Report(complaintID, "I am scheduler 1. I am too busy." );
        Report expected = schedulerController.createReport(r);
        assertSame(r, expected);
    }

    @Test
    @Order(7)
    @DisplayName("Worker can create Report")
    void testWorkerCreateReport(){
        Report r = new Report(complaintID, "I am worker 2. I am too rush." );
        Report expected = workerController.createReport(r);
        assertSame(r, expected);
    }

    @Test
    @Order(8)
    @DisplayName("Manager can show all complaints")
    void testManagerShowCompliant(){
        List<Complaint> complaints = managerController.showComplaints();
        assertEquals(1, complaints.size());
        Complaint expected = new Complaint(repairID, "not friendly", "2023-01-04 12:00:00", "", 1, 1);
        assertEquals(expected.getRepairID(), repairID);
        assertEquals(expected.getContent(), complaints.get(0).getContent());
        assertEquals(expected.getTime(), complaints.get(0).getTime());
        assertEquals(expected.getOwnerID(), complaints.get(0).getOwnerID());
        assertEquals(expected.getState(), complaints.get(0).getState());
    }

    @Test
    @Order(9)
    @DisplayName("Manager can show report by complaint")
    void testManagerShowReportByComplaint() {
        List<Report> reports = managerController.showReportsByComplaint(complaintID);
        assertEquals(2, reports.size());
    }

    @Test
    @Order(10)
    @DisplayName("Scheduler can close complaint")
    void testCloseComplaint(){
        Complaint now = managerController.closeComplaint(complaintID);
        assertEquals(0, now.getState());
    }
}
