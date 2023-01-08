package functions;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.Controller.GeneralController;
import project.Controller.SchedulerController;
import project.Controller.WorkerController;
import project.SpringBootApp;
import project.model.entity.Complaint;
import project.model.entity.Dispatch;
import project.model.entity.Log;
import project.model.entity.Repair;
import project.model.people.Worker;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest(classes = SpringBootApp.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("TestUserFunction")
public class TestUserFunction {
    private static int repairID = 101; // any number here is ok

    private static int dispatchID = 102; // any number here is ok
    @Autowired
    SchedulerController schedulerController;

    @Autowired
    WorkerController workerController;

    @Autowired
    GeneralController generalController;

    @Test
    @Order(1)
    @DisplayName("Scheduler can create Repair")
    void testCreateRepair(){
        Repair r = new Repair("2023-01-05 10:00:00", "lose key", 1, "wechat", 1);
        Repair expected = schedulerController.createRepair(r);
        assertSame(r, expected);
        repairID = expected.getID();
    }

    @Test
    @Order(2)
    @DisplayName("Scheduler can create Dispatch")
    void testCreateDispatch(){
        Dispatch d = new Dispatch(Timestamp.valueOf("2023-01-05 11:00:00"), Timestamp.valueOf("2023-01-05 23:00:00"), repairID, 1, 1, 3);
        Dispatch expected = schedulerController.createDispatch(d);
        assertSame(d, expected);
        dispatchID = expected.getID();
    }

    @Test
    @Order(3)
    @DisplayName("Worker can create Log")
    void testCreateLog(){
        Log l = new Log(dispatchID, Timestamp.valueOf("2023-01-05 12:00:00"), Timestamp.valueOf("2023-01-05 14:00:00"), 2, "fixed");
        Log expected = workerController.createLog(l);
        assertSame(l, expected);
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class TestSchedulerCanKnowAboutRepair{
        @Test
        @Order(4)
        @DisplayName("Scheduler can show state of repair")
        void testShowStateOfRepairs(){
            int state = schedulerController.showStateOfRepair(repairID);
            // assertEquals(1, repairs.size());
            assertEquals(2, state);
        }

        @Test
        @Order(5)
        @DisplayName("Scheduler can show dispatch by repair")
        void testShowDispatchByRepair(){
            List<Dispatch> dispatches = schedulerController.showRelatedDispatchByRepair(repairID);
            Dispatch d = new Dispatch(Timestamp.valueOf("2023-01-05 11:00:00"), Timestamp.valueOf("2023-01-05 23:00:00"), repairID, 1, 1, 3);
            assertEquals(d.getBeginTime(), dispatches.get(0).getBeginTime());
            assertEquals(d.getEndTime(), dispatches.get(0).getEndTime());
            assertEquals(d.getRepairID(), dispatches.get(0).getRepairID());
            assertEquals(d.getSchedulerID(), dispatches.get(0).getSchedulerID());
            assertEquals(d.getWorkerID(), dispatches.get(0).getWorkerID());
        }

        @Test
        @Order(6)
        @DisplayName("Scheduler can show log by repair")
        void testShowLogByRepair(){
            List<Log> logs = schedulerController.showRelatedLogByRepair(repairID);
            Log l = new Log(dispatchID, Timestamp.valueOf("2023-01-05 12:00:00"), Timestamp.valueOf("2023-01-05 14:00:00"), 2, "fixed");            assertEquals(l.getDispatchID(), logs.get(0).getDispatchID());
            assertEquals(l.getBeginTime(), logs.get(0).getBeginTime());
            assertEquals(l.getEndTime(), logs.get(0).getEndTime());
            assertEquals(l.getHours(), logs.get(0).getHours());
            assertEquals(l.getComment(), logs.get(0).getComment());
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class TestWorkerCanKnowAboutDispatchAndComplaint{
        @Test
        @Order(7)
        @DisplayName("Worker can show dispatch to her/him")
        void testWorkerShowDispatches(){
            Dispatch d = new Dispatch(Timestamp.valueOf("2023-01-05 11:00:00"), Timestamp.valueOf("2023-01-05 23:00:00"), repairID, 1, 1, 3);
            List<Dispatch> dispatches = workerController.showMyDispatches(3);
            assertEquals(d.getBeginTime(), dispatches.get(0).getBeginTime());
            assertEquals(d.getEndTime(), dispatches.get(0).getEndTime());
            assertEquals(d.getRepairID(), dispatches.get(0).getRepairID());
            assertEquals(d.getState(), dispatches.get(0).getState());
            assertEquals(d.getSchedulerID(), dispatches.get(0).getSchedulerID());
            assertEquals(d.getWorkerID(), dispatches.get(0).getWorkerID());
        }

        @Test
        @Order(8)
        @DisplayName("Worker can show complaint to her/him")
        void testWorkerShowComplaint(){
            List<Complaint> complaints = workerController.showMyCompliant(3);
            assertEquals(0, complaints.size());
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class TestCanKnowAboutCurrentDispatch{
        @Test
        @Order(9)
        @DisplayName("User can know current dispatch of repair")
        void TestCanKnowCurrentDispatch(){
            List<Dispatch> dispatches = schedulerController.showRelatedDispatchByRepair(repairID);
            Dispatch d = new Dispatch(Timestamp.valueOf("2023-01-05 11:00:00"), Timestamp.valueOf("2023-01-05 23:00:00"), repairID, 1, 1, 3);
            assertEquals(d.getBeginTime(), dispatches.get(0).getBeginTime());
            assertEquals(d.getEndTime(), dispatches.get(0).getEndTime());
            assertEquals(d.getRepairID(), dispatches.get(0).getRepairID());
            assertEquals(d.getSchedulerID(), dispatches.get(0).getSchedulerID());
            assertEquals(d.getWorkerID(), dispatches.get(0).getWorkerID());
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class TestCanKnowFreeWorker{
        @Test
        @Order(10)
        @DisplayName("Scheduler can know free worker")
        void TestSchedulerCanKnowFreeWorker(){
            List<Worker> workers = schedulerController.showFreeWorkersInfo();
            assertEquals(1, workers.size());
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class TestCanKnowTimeUsedByRepair{
        @Test
        @Order(11)
        @DisplayName("User can sum time by repair")
        void TestSumTimeByRepair(){
            int totalTime = generalController.sumTimeByRepair(repairID);
            assertEquals(2, totalTime);
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class TestCanKnowTimeUsedByWorker{
        @Test
        @Order(12)
        @DisplayName("User can sum time by Worker in recent")
        void TestSumTimeByWorker(){
            int totalTime = generalController.sumTimeByWorkerInRecent(3, Timestamp.valueOf("2023-01-01 00:00:00"), Timestamp.valueOf("2023-01-31 23:59:59"));
            assertEquals(2, totalTime);
        }
    }
}
