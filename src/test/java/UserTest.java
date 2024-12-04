import org.example.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testBenevoleCreation() {
        Benevole user = new Benevole("Jean", "Dupont", "jean.dupont@example.com", "password123");
        assertEquals("Jean", user.getFName());
        assertEquals("Dupont", user.getLName());
        assertEquals("Jean Dupont", user.getUserFullName());
        assertEquals("Benevole", user.getRole());
    }

    @Test
    public void testVulnerableUserCreation() {
        VulnerableUser user = new VulnerableUser("Alice", "Martin", "alice.martin@example.com", "securepassword");
        assertEquals("Alice", user.getFName());
        assertEquals("Martin", user.getLName());
        assertEquals("Alice Martin", user.getUserFullName());
        assertEquals("Vulnerable User", user.getRole());
    }

    @Test
    public void testValidator() {
        Validator user = new Validator("Alice", "Martin", "alice.martin@example.com", "securepassword");
        assertEquals("Alice", user.getFName());
        assertEquals("Martin", user.getLName());
        assertEquals("Alice Martin", user.getUserFullName());
        assertEquals("Validator", user.getRole());
    }
}
