
import Tietorakenteet.Keko;
import Tietorakenteet.Lista;
import algoritmit.Fringe;
import logiikka.Ruudukko;
import logiikka.Solmu;
import logiikka.Tyyppi;
import algoritmit.Djikstra;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FringeTest {

    public FringeTest() {
    }
    Fringe fringe;

    @Test
    public void reitinPituusOikea() {
        fringe.etsiReitti();
        assertEquals(5, fringe.palautaReittiListana().koko());
    }


    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Ruudukko ruudukko = new Ruudukko(9,9);
        ruudukko.lisaaTyyppi(1,1, Tyyppi.ALOITUS);
        ruudukko.lisaaTyyppi(5,5, Tyyppi.MAALI);
        ruudukko.muodostavieruslista();
        fringe = new Fringe(ruudukko);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
