
package algoritmit;

import logiikka.Ruudukko;
import logiikka.Solmu;
import Tietorakenteet.Lista;
import java.util.ArrayDeque;
public class Bfs {

    private Lista<Solmu> reitti;
    private Ruudukko ruudukko;
    private int etaisyys[][];
    
    public Bfs(Ruudukko ruudukko) {
        this.ruudukko = ruudukko;
        this.reitti = new Lista<>();
    }
    
    
    public void muodostaReitti() {
        Solmu alku = ruudukko.getAloitus();
        System.out.println("Muodostetaan reittiä");
        if (alku == null) {
            return;
        }
        
        ArrayDeque<Solmu> jono = new ArrayDeque<>();
        
        this.etaisyys = new int[ruudukko.getX()][ruudukko.getY()];
        
        boolean vierailtu[][] = new boolean[ruudukko.getX()][ruudukko.getY()];
        
        this.etaisyys[alku.getX()][alku.getY()] = 0;
        vierailtu[alku.getX()][alku.getY()] = true;
        jono.addLast(alku);
        
        while(!jono.isEmpty()) {
         Solmu solmu = jono.pollFirst();
            
         //Solmun naapureiden iterointi.
         for (int i = 0; i < this.ruudukko.getVieruslista()[solmu.getX()][solmu.getY()].koko(); i++) {
          Solmu naapuri = (Solmu) this.ruudukko.getVieruslista()[solmu.getX()][solmu.getY()].hae(i);
             if (vierailtu[naapuri.getX()][naapuri.getY()]) {
                 continue;
             }
             jono.addLast(naapuri);
             vierailtu[naapuri.getX()][naapuri.getY()] = true;
             etaisyys[naapuri.getX()][naapuri.getY()] = etaisyys[solmu.getX()][solmu.getY()] + 1;

         }
        }
        
        for (int x = 0; x < etaisyys.length; x++) {
            for (int y = 0; y < etaisyys.length; y++) {
                System.out.print(etaisyys[x][y] + " ");
            }
            System.out.println("");
        }
        
        System.out.println("muodostettu");
    }
    
    
}
