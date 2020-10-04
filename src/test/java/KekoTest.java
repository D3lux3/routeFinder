
import Tietorakenteet.Keko;
import logiikka.Solmu;
import logiikka.Tyyppi;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KekoTest {

    public KekoTest() {
    }
    Keko keko;

    @Test
    public void kekoAluksiTyhja() {
        assertTrue(keko.onkoTyhja());
    }

    @Test
    public void listanKokoKasvaaYhdella() {
        keko.lisaa(new Solmu(5,5, Tyyppi.TYHJA));
        assertEquals(1, keko.koko());
    }

    @Test
    public void palauttaaPienimmänSolmunJuurena() {
        keko.lisaa(new Solmu(5,5, Tyyppi.TYHJA));
        Solmu solmu = new Solmu(6,6, Tyyppi.TYHJA);
        solmu.setEtaisyys(1);
        keko.lisaa(solmu);
        keko.lisaa(new Solmu(7,5, Tyyppi.TYHJA));
        keko.lisaa(new Solmu(8,5, Tyyppi.TYHJA));
        keko.lisaa(new Solmu(9,5, Tyyppi.TYHJA));
        assertEquals(solmu, keko.min());
    }

    @Test
    public void pienimmänSolmunPoistaminen() {
        keko.lisaa(new Solmu(5,5, Tyyppi.TYHJA));
        Solmu solmu = new Solmu(6,6, Tyyppi.TYHJA);
        solmu.setEtaisyys(1);
        keko.lisaa(solmu);
        keko.lisaa(new Solmu(7,5, Tyyppi.TYHJA));
        keko.lisaa(new Solmu(8,5, Tyyppi.TYHJA));
        keko.lisaa(new Solmu(9,5, Tyyppi.TYHJA));
        int koko = keko.koko();

        assertEquals(solmu, keko.poistaMin());
        assertEquals(koko - 1, keko.koko());
    }


    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        keko = new Keko();
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
