import org.example.*;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class IntegrationTest {

    @Test
    public void testVolontaireMission() {
        Benevole benevole = new Benevole("Marie", "Lemoine", "marie.lemoine@example.com", "password");
        Mission mission = new Mission("Lavage", "Laver des vêtements", new Date(), "Toulouse");

        benevole.volontaireMission(mission);
        assertEquals("En Attente", mission.getStatutMission());

        mission.setStatutMission("Validée");
        benevole.volontaireMission(mission);
        assertEquals("En cours", mission.getStatutMission());
    }
}