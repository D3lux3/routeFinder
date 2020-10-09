import Tietorakenteet.Lista;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import logiikka.Tyyppi;
import org.junit.Test;

import logiikka.Solmu;

public class SolmuTest {

    Solmu solmu;

    @Before
    public void setUp() throws Exception {
        solmu = new Solmu(0,0, Tyyppi.ALOITUS);
    }

    public void tearDown() throws Exception {
    }

    @Test
    public void tyypinHaku() {
        assertEquals(Tyyppi.ALOITUS, solmu.getTyyppi());
    }

    @Test
    public void koordinaatitOikein() {
        assertEquals(0, solmu.getX());
        assertEquals(0, solmu.getY());
    }

    @Test
    public void toStringOikea() {
        assertEquals("X:0 | Y:0 | Tyyppi: ALOITUS", solmu.toString());
    }
}