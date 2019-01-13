package dataTests;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import data.Party;

public class PartyTests {
    Party partido;
    @Test
    public void PartyNull(){
        assertThrows(RuntimeException.class, () -> partido = new Party(null));
    }
}
