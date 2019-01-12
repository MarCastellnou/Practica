package dataTests;
import org.junit.jupiter.api.Test;
import data.DigitalSignature;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DigitalSignatureTests {
    DigitalSignature sign;

    @Test

    public void DigitalSignatureNull(){
        assertThrows(RuntimeException.class, () -> sign = new DigitalSignature(null));
    }

}
