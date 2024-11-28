import org.example.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testBenevoleCreation() {
        Benevole benevole = new Benevole("Jean", "Dupont", "jean.dupont@example.com", "password123");
        assertEquals("Jean Dupont", benevole.getUserFullName());
        assertEquals("Benevole", benevole.getRole());
    }

    @Test
    public void testVulnerableUserCreation() {
        VulnerableUser user = new VulnerableUser("Alice", "Martin", "alice.martin@example.com", "securepassword");
        assertEquals("Alice", user.getFName());
        assertEquals("Martin", user.getLName());
        assertEquals("Vulnerable User", user.getRole());
    }
}
