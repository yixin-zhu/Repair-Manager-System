package extend;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.Controller.SchedulerController;
import project.Controller.WorkerController;
import project.SpringBootApp;
import project.model.entity.Dispatch;
import project.model.entity.Log;
import project.model.entity.Repair;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayName("TestMultipleDispatch")
@SpringBootTest(classes = SpringBootApp.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestExtend2 {
    private static int repairID = 101; // any number here is ok

    private static int dispatchID = 102; // any number here is ok

    @Autowired
    SchedulerController schedulerController;

    @Autowired
    WorkerController workerController;

    @Test
    @Order(1)
    @DisplayName("Scheduler can create Repair")
    void testCreateRepair(){
        Repair r = new Repair("2023-01-03 10:00:00", "light cant turn on", 1, "wechat", 1);
        Repair expected = schedulerController.createRepair(r);
        assertSame(r, expected);
        repairID = expected.getID();
    }

    @Test
    @Order(2)
    @DisplayName("Scheduler can create Dispatch")
    void testCreateDispatch(){
        Dispatch d = new Dispatch(Timestamp.valueOf("2023-01-03 11:00:00"), Timestamp.valueOf("2023-01-06 23:00:00"), repairID, 1, 1, 1);
        Dispatch expected = schedulerController.createDispatch(d);
        assertSame(d, expected);
        dispatchID = expected.getID();
    }
    @Test
    @Order(3)
    @DisplayName("Worker can create Log")
    void testCreateLog(){
        Log l = new Log(dispatchID, Timestamp.valueOf("2023-01-03 12:00:00"), Timestamp.valueOf("2023-01-03 14:00:00"), 2, "to be continue");
        Log expected = workerController.createLog(l);
        assertSame(l, expected);
    }

    @Test
    @Order(4)
    @DisplayName("Worker can create Log again")
    void testCreateLog2(){
        Log l = new Log(dispatchID, Timestamp.valueOf("2023-01-04 12:00:00"), Timestamp.valueOf("2023-01-04 14:00:00"), 2, "fixed");
        Log expected = workerController.createLog(l);
        assertSame(l, expected);
    }
}
