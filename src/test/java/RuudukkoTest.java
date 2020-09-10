
import Tietorakenteet.Lista;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import logiikka.Ruudukko;
import logiikka.Tyyppi;
import logiikka.Solmu;
public class RuudukkoTest {

    Ruudukko ruudukko;

    public RuudukkoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ruudukko = new Ruudukko(50, 50);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void kaikkiRuudutAluksiTyhjaa() {
        for (int x = 0; x < ruudukko.getX(); x++) {
            for (int y = 0; y < ruudukko.getY(); y++) {

                assertEquals(ruudukko.getTyyppi(x, y), Tyyppi.TYHJA);
            }
        }
    }

    @Test
    public void ruudukonNollausNollaa() {
        for (int x = 0; x < ruudukko.getX(); x++) {
            for (int y = 0; y < ruudukko.getY(); y++) {
                if (x % 2 == 0) {
                    ruudukko.lisaaTyyppi(x, y, Tyyppi.SEINA);
                }

                if (y % 2 == 0) {
                    ruudukko.lisaaTyyppi(x, y, Tyyppi.SEINA);
                }
            }
        }

        ruudukko.nollaaTaulukko();

        for (int x = 0; x < ruudukko.getX(); x++) {
            for (int y = 0; y < ruudukko.getY(); y++) {
                assertEquals(ruudukko.getTyyppi(x, y), Tyyppi.TYHJA);
            }
        }
    }

    @Test
    public void aloituksenAsetusToimii() {
        ruudukko.lisaaTyyppi(5,5, Tyyppi.ALOITUS);
        assertNotEquals(ruudukko.getAloitus(), null);
        assertEquals(ruudukko.getSolmu(5,5), ruudukko.getAloitus());
        assertEquals(ruudukko.getAloitus().getX(), 5);
        assertEquals(ruudukko.getAloitus().getY(), 5);
    }

    @Test
    public void maalinAsetusToimii() {
        ruudukko.lisaaTyyppi(5,5, Tyyppi.MAALI);
        assertNotEquals(ruudukko.getMaali(), null);
        assertEquals(ruudukko.getSolmu(5,5), ruudukko.getMaali());
        assertEquals(ruudukko.getMaali().getX(), 5);
        assertEquals(ruudukko.getMaali().getY(), 5);
    }

    @Test
    public void etsiiNaapurit() {
        ruudukko.etsiNaapurit();
        Lista lista[][] = ruudukko.getVieruslista();
        assertNotEquals(lista[1][1].hae(0), null);
    }

    @Test
    public void tyypinLisausJaGetOnnistuu() {
        ruudukko.lisaaTyyppi(5,5, Tyyppi.ALOITUS);
        assertEquals(ruudukko.getTyyppi(5,5), Tyyppi.ALOITUS);
    }

}
