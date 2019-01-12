package dataTests;
import org.junit.jupiter.api.Test;
import data.MailAddress;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MailAddressTests {
    MailAddress adress;

    @Test
    public void MailAdressNull(){
        assertThrows(RuntimeException.class, () -> adress = new MailAddress(null));
    }
}
