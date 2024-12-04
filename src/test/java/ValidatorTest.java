import org.example.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//import java.util.ArrayList;
import java.util.Date;

public class ValidatorTest {

    @Test
    public void testValidateMission() {
        Validator validator = new Validator("Paul", "Durand", "paul.durand@example.com", "validatorpassword");
        Mission mission = new Mission("Transport", "Transporter un colis", new Date(), "Marseille");

        validator.validateMission(mission,true);
        assertEquals("En Attente",mission.getStatutMission());
    }
    @Test
    public void testNoValidateMission() {
        Validator validator = new Validator("Paul", "Durand", "paul.durand@example.com", "validatorpassword");
        Mission mission = new Mission("Transport", "Transporter un colis", new Date(), "Marseille");

        validator.validateMission(mission,false);
        assertEquals("En Attente",mission.getStatutMission());
    }

    /* @Test
    public void testGetMissionsNotValidated() {
        Validator validator = new Validator("Paul", "Durand", "paul.durand@example.com", "validatorpassword");
        ArrayList<Mission> missions = DataSource.getMissionsByStatus("En Attente");

        assertNotNull(missions);
        assertTrue(missions.isEmpty()); // Supposons qu'il n'y a pas encore de missions
    }  */
}