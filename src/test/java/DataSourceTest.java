import org.example.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;

public class DataSourceTest {

    //vrais si on a access a la base de données vpn ou machine insa
    boolean access = false;

    @Test
    public void testConnection() {
        if (access){
            try {
                DataSource.startConnection();
                assertNotNull(DataSource.getConnection());
            } catch (SQLException e) {

            }
        }
    }

    @Test
    public void testRegisterMission() {
        if (access) {
            DataSource.startConnection();
            User user = new VulnerableUser(1, "d", "d", "d@d", "d");
            Mission mission = new Mission("Course", "Acheter des médicaments", new Date(), "Lyon");
            assertTrue(DataSource.registerMission(mission, user));
        }
    }

    @Test
    public void testGetMissionByStatusEnAttente() {
        if (access) {
            DataSource.startConnection();
            User user = new VulnerableUser(1, "d", "d", "d@d", "d");
            Mission mission = new Mission("Course", "Acheter des médicaments", new Date(), "Lyon");
            DataSource.registerMission(mission, user);
            ArrayList<Mission> missions = DataSource.getMissionsByStatus("En Attente");
            for (Mission mission1 : missions){
                assertEquals("En Attente", mission1.getStatutMission());
            }    
        }
    }
    
    @Test
    public void testGetMissionByStatusValidee() {
        if (access) {
            DataSource.startConnection();
            User user = new VulnerableUser(1, "d", "d", "d@d", "d");
            Mission mission = new Mission("Transport", "Transporter un colis", new Date(), "Marseille");
            mission.setStatutMission("Validée");
            DataSource.registerMission(mission, user);
            ArrayList<Mission> missions = DataSource.getMissionsByStatus("Validée");
            for (Mission mission1 : missions){
                assertEquals("Validée", mission1.getStatutMission());
            }    
        }
    }

    @Test
    public void testGetMissionByStatusRealisee() {
        if (access) {
            DataSource.startConnection();
            User user = new VulnerableUser(1, "d", "d", "d@d", "d");
            Mission mission = new Mission("Lavage", "Laver des vêtements", new Date(), "Toulouse");
            mission.setStatutMission("Réalisée");
            DataSource.registerMission(mission, user);
            ArrayList<Mission> missions = DataSource.getMissionsByStatus("Réalisée");
            for (Mission mission1 : missions){
                assertEquals("Réalisée", mission1.getStatutMission());
            }    
        }
    }


}
