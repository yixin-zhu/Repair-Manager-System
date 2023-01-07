package extend;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.Controller.SchedulerController;
import project.SpringBootApp;
import project.model.people.Worker;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TestMultipleDispatch")
@SpringBootTest(classes = SpringBootApp.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestExtend3 {
    @Autowired
    SchedulerController schedulerController;

    @Test
    @Order(1)
    @DisplayName("Scheduler can select worker by skill")
    void testFindWorkerBySkill(){
        List<Worker> workers = schedulerController.showWorkersInfoBySkill(1);
        assertEquals(1, workers.size());
        assertEquals("Zhao", workers.get(0).getName());
    }


}
