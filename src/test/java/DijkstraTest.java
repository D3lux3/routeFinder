
import Tietorakenteet.Lista;
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

public class DijkstraTest {

    public DijkstraTest() {
    }
    Djikstra djikstra;

    @Test
    public void reitinPituusOikea() {
        djikstra.etsiReitti();
        assertEquals(5, djikstra.palautaReittiListana().koko());
    }

    @Test
    public void optimaalisinReitti() {
        djikstra.etsiReitti();
        Lista<Solmu> lista = djikstra.palautaReittiListana();
        assertEquals(new Solmu(5,5, Tyyppi.MAALI),  lista.hae(0));
        assertEquals(new Solmu(4,4, Tyyppi.TYHJA),  lista.hae(1));
        assertEquals(new Solmu(3,3, Tyyppi.TYHJA),  lista.hae(2));
        assertEquals(new Solmu(2,2, Tyyppi.TYHJA),  lista.hae(3));
        assertEquals(new Solmu(1,1, Tyyppi.ALOITUS),  lista.hae(4));
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
        djikstra = new Djikstra(ruudukko);
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
