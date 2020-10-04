
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Tietorakenteet.Lista;

public class ListaTest {

    public ListaTest() {
    }
    Lista<Integer> lista;
    
    @Test
    public void listanKokoAluksiNolla() {
        assertEquals(0, lista.koko());
    }

    @Test
    public void listanKokoKasvaaYhdella() {
        lista.lisaa(0);
        assertEquals(1, lista.koko());
    }


    @Test
    public void listanAlkioidenKaantaminen() {
        lista.lisaa(1);
        lista.lisaa(2);
        lista.lisaa(3);
        lista.lisaa(4);
        lista.lisaa(5);
        lista.kaannaYmpari();
        assertEquals(5, (int) lista.hae(0));
    }

    @Test
    public void hakeminenListastaOnnistuu() {
        lista.lisaa(0);
        lista.lisaa(2);
        lista.lisaa(5);
        lista.lisaa(9);
        assertEquals(0, (int) lista.hae(0));
        assertEquals(5, (int) lista.hae(2));
    }

    @Test
    public void listastaPoistaminenOnnistuu() {
        lista.lisaa(0);
        lista.poista(0);
        assertEquals(0, lista.koko());
    }
    @Test
    public void listanAlustusToimii() {
        lista.lisaa(0);
        lista.lisaa(2);
        lista.lisaa(5);
        lista.lisaa(9);
        
        assertEquals(4, lista.koko());
        
        lista.alusta();
        
        assertEquals(0, lista.koko());
    }    
    
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        lista = new Lista();
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
