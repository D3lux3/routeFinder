package algoritmit;

import Tietorakenteet.Lista;
import logiikka.Kaari;
import logiikka.Ruudukko;
import logiikka.Solmu;
import java.util.PriorityQueue;

public class Djikstra {


    public static void algo(Ruudukko ruudukko) {
        if (ruudukko.getAloitus() != null && ruudukko.getMaali() != null) {
            PriorityQueue<Solmu> keko = new PriorityQueue<Solmu>();

            boolean kasitelty[][] = new boolean[ruudukko.getX()][ruudukko.getY()];
            for (int x = 0; x < ruudukko.getX(); x++) {
                for (int y = 0; y < ruudukko.getY(); y++) {
                    ruudukko.setSolmunEtaisyys(x,y, Integer.MAX_VALUE);
                    kasitelty[x][y] = false;
                }
            }

            //Alku pisteen etaisyyden asetus
            ruudukko.setSolmunEtaisyys(ruudukko.getAloitus().getX(), ruudukko.getAloitus().getY(), 0);
            keko.add(ruudukko.getRuudukko()[ruudukko.getAloitus().getX()][ruudukko.getAloitus().getY()]);

            while (!keko.isEmpty()) {
                Solmu seuraava = keko.poll();

                if (!kasitelty[seuraava.getX()][seuraava.getY()]) {
                    kasitelty[seuraava.getX()][seuraava.getY()] = true;
                    for (int i = 0; i < ruudukko.getVieruslista()[seuraava.getX()][seuraava.getY()].koko(); i++) {
                        Kaari kaari = (Kaari) ruudukko.getVieruslista()[seuraava.getX()][seuraava.getY()].hae(i);
                        Solmu loppu = kaari.loppu;

                        if (loppu.getEtaisyys() > seuraava.getEtaisyys() + kaari.paino) {
                            ruudukko.setSolmunVanhempi(loppu.getX(), loppu.getY(), seuraava);
                            ruudukko.setSolmunEtaisyys(loppu.getX(),loppu.getY(), seuraava.getEtaisyys() + kaari.paino);
                            keko.add(ruudukko.getRuudukko()[loppu.getX()][loppu.getY()]);
                        }
                    }
                }
            }
        }

        Solmu solmu = ruudukko.getRuudukko()[ruudukko.getMaali().getX()][ruudukko.getMaali().getY()];
        Lista <Solmu> reitti = new Lista<>();
        while (solmu != null) {
            reitti.lisaa(solmu);
            solmu = solmu.getVanhempi();
        }
        ruudukko.piirraReitti(reitti);
    }



}
