package A;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.Controller.OwnerController;
import project.Controller.SchedulerController;
import project.Controller.WorkerController;
import project.SpringBootApp;
import project.model.entity.Dispatch;
import project.model.entity.Log;
import project.model.entity.Rating;
import project.model.entity.Repair;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TestNormalProcess")
@SpringBootTest(classes = SpringBootApp.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestNormalProcess {
    private static int repairID = 6;

    private static int dispatchID = 7;

    @Autowired
    SchedulerController schedulerController;

    @Autowired
    WorkerController workerController;

    @Autowired
    OwnerController ownerController;

    @Test
    @Order(1)
    @DisplayName("Scheduler can create Repair")
    void testCreateRepair(){
        Repair r = new Repair("2023-01-01 10:00:00", "light cant turn on", 1, "wechat", 1);
        Repair expected = schedulerController.createRepair(r);
        assertSame(r, expected);
        repairID = expected.getID();
    }

    @Test
    @Order(2)
    @DisplayName("Scheduler can show waiting repairs")
    void testShowWaitingRepairs(){
        Repair r = new Repair("2023-01-01 10:00:00", "light cant turn on", 1, "wechat", 1);
        List<Repair> repairs = schedulerController.showWaitingRepairs();
        assertEquals(1, repairs.size());
        assertEquals(r.getTime(), repairs.get(0).getTime());
        assertEquals(r.getContent(), repairs.get(0).getContent());
        assertEquals(r.getOwnerID(), repairs.get(0).getOwnerID());
        assertEquals(r.getOrigin(), repairs.get(0).getOrigin());
        assertEquals(r.getState(), repairs.get(0).getState());
    }

    @Test
    @Order(3)
    @DisplayName("Scheduler can create Dispatch")
    void testCreateDispatch(){
        Dispatch d = new Dispatch(Timestamp.valueOf("2023-01-01 11:00:00"), Timestamp.valueOf("2023-01-04 23:00:00"), repairID, 1, 1, 1);
        Dispatch expected = schedulerController.createDispatch(d);
        assertSame(d, expected);
        dispatchID = expected.getID();
    }

    @Test
    @Order(4)
    @DisplayName("Worker can show her/his dispatches")
    void testShowMyDispatches(){
        Dispatch d = new Dispatch(Timestamp.valueOf("2023-01-01 11:00:00"), Timestamp.valueOf("2023-01-04 23:00:00"), repairID, 1, 1, 1);
        List<Dispatch> dispatches = workerController.showMyDispatches(1);
        // assertEquals(1, dispatches.size());
        assertEquals(d.getBeginTime(), dispatches.get(0).getBeginTime());
        assertEquals(d.getEndTime(), dispatches.get(0).getEndTime());
        assertEquals(d.getRepairID(), dispatches.get(0).getRepairID());
        assertEquals(d.getState(), dispatches.get(0).getState());
        assertEquals(d.getSchedulerID(), dispatches.get(0).getSchedulerID());
        assertEquals(d.getWorkerID(), dispatches.get(0).getWorkerID());
    }

    @Test
    @Order(5)
    @DisplayName("Worker can create Log")
    void testCreateLog(){
        Log l = new Log(dispatchID, Timestamp.valueOf("2023-01-01 12:00:00"), Timestamp.valueOf("2023-01-01 14:00:00"), 2, "fixed");
        Log expected = workerController.createLog(l);
        assertSame(l, expected);
    }

    @Test
    @Order(6)
    @DisplayName("Owner can show her/his repairs")
    void testShowMyRepairs(){
        Repair expected = new Repair("2023-01-01 10:00:00", "light cant turn on", 1, "wechat", 2);
        List<Repair> repairs = ownerController.showMyRepairs(1);
        // assertEquals(1, repairs.size());
        assertEquals(expected.getTime(), repairs.get(0).getTime());
        assertEquals(expected.getContent(), repairs.get(0).getContent());
        assertEquals(expected.getOwnerID(), repairs.get(0).getOwnerID());
        assertEquals(expected.getOrigin(), repairs.get(0).getOrigin());
        assertEquals(expected.getState(), repairs.get(0).getState());
    }

    @Test
    @Order(7)
    @DisplayName("Owner can create Rating")
    void testCreateRating(){
        Rating r = new Rating(repairID, 5, 5, 5);
        Rating expected = ownerController.createRating(r);
        assertSame(r, expected);
    }

    @Test
    @Order(8)
    @DisplayName("Scheduler can close repair")
    void testCloseRepair(){
        Repair now = schedulerController.closeRepair(repairID);
        assertEquals(0, now.getState());
    }

}
