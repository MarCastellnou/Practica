package dataTests;
import org.junit.jupiter.api.Test;
import data.Nif;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NifTests {
    Nif nif;

    @Test
    public void NifNull(){
        assertThrows(RuntimeException.class, () -> nif = new Nif(null));
    }

    @Test
    public void NifLongError(){
        assertThrows(RuntimeException.class, () -> nif = new Nif("562398L"));
        assertThrows(RuntimeException.class, () -> nif = new Nif("98624531783N"));
    }
    @Test
    public void NifMasDeUnaLetra(){
        assertThrows(RuntimeException.class, () -> nif = new Nif("9862O531N"));
        assertThrows(RuntimeException.class, () -> nif = new Nif("9M62O531N"));
    }

    @Test
    public void NifSinLetra(){
        assertThrows(RuntimeException.class, () -> nif = new Nif("36548561"));
    }
}
