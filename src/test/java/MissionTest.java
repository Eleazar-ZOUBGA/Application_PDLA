import org.example.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

/* public class MissionTest {

    @Test
    public void testMissionCreationWithEndDate() {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 86400000); // +1 jour
        Mission mission = new Mission("Course", "Faire les courses", startDate, endDate, "Paris");

        assertEquals("Course", mission.getTitle());
        assertEquals("Faire les courses", mission.getDescription());
        assertEquals("Paris", mission.getLocation());
        assertNotNull(mission.getEndDate());
    }

    @Test
    public void testMissionStatus() {
        Mission mission = new Mission("Course", "Acheter des médicaments", new Date(), "Lyon");
        mission.setStatutMission("Validée");

        assertEquals("Validée", mission.getStatutMission());
    }
}
*/