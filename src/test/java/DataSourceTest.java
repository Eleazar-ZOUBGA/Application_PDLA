import org.example.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Date;

public class DataSourceTest {

    @Test
    public void testConnection() {
        try {
            DataSource.startConnection();
            assertNotNull(DataSource.getConnection());
        } catch (SQLException e) {

        }
    }

    @Test
    public void testRegisterMission() {
        DataSource.startConnection();
        User user = new VulnerableUser(1, "d", "d", "d@d", "d");
        Mission mission = new Mission("Course", "Acheter des médicaments", new Date(), "Lyon");
        assertTrue(DataSource.registerMission(mission, user));
    }

    /* @Test
    public void testGetMissionByStatus() {
        try {
            DataSource.getConnection();
            //assertTrue(DataSource.registerMission(mission));
        } catch (SQLException e) {
            System.err.println("Erreur de connexion" + e.getMessage());
        }
    }
 */
    
}
