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
        assertEquals(solmu.getTyyppi(), Tyyppi.ALOITUS);
    }

    @Test
    public void koordinaatitOikein() {
        assertEquals(solmu.getX(), 0);
        assertEquals(solmu.getY(), 0);
    }

    @Test
    public void toStringOikea() {
        assertEquals("Y:0 X:0 Tyyppi: ALOITUS", solmu.toString());
    }
}