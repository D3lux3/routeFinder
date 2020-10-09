package algoritmit;

import Tietorakenteet.Keko;
import Tietorakenteet.Lista;
import logiikka.Kaari;
import logiikka.Ruudukko;
import logiikka.Solmu;
import logiikka.Tyyppi;


public class Djikstra {

    private Ruudukko ruudukko;

    public Djikstra (Ruudukko ruudukko) {
        this.ruudukko = ruudukko;
    }

    /**
     * Etsii nopeimman reitin oliomuuttuja Ruudukolle
     */
    public void etsiReitti() {
        if (ruudukko.getAloitus() != null && ruudukko.getMaali() != null) {
            Keko keko = new Keko();
            boolean kasitelty[][] = new boolean[ruudukko.getX()][ruudukko.getY()];
            for (int x = 0; x < ruudukko.getX(); x++) {
                for (int y = 0; y < ruudukko.getY(); y++) {
                    ruudukko.setSolmunEtaisyys(x,y, Integer.MAX_VALUE);
                    kasitelty[x][y] = false;
                }
            }

            //Alku pisteen etaisyyden asetus
            ruudukko.setSolmunEtaisyys(ruudukko.getAloitus().getX(), ruudukko.getAloitus().getY(), 0);
            keko.lisaa(ruudukko.getSolmu(ruudukko.getAloitus().getX(),ruudukko.getAloitus().getY()));

            while (!keko.onkoTyhja()) {
                Solmu seuraava = keko.poistaMin();

                if (!kasitelty[seuraava.getX()][seuraava.getY()]) {
                    kasitelty[seuraava.getX()][seuraava.getY()] = true;
                    for (int i = 0; i < ruudukko.getVieruslista()[seuraava.getX()][seuraava.getY()].koko(); i++) {
                        Kaari kaari = (Kaari) ruudukko.getVieruslista()[seuraava.getX()][seuraava.getY()].hae(i);
                        Solmu loppu = kaari.loppu;

                        if (loppu.getEtaisyys() > seuraava.getEtaisyys() + kaari.paino) {
                            if (!kasitelty[loppu.getX()][loppu.getY()]) {
                                ruudukko.setSolmunVanhempi(loppu.getX(), loppu.getY(), seuraava);
                            }
                            ruudukko.setSolmunEtaisyys(loppu.getX(),loppu.getY(), seuraava.getEtaisyys() + kaari.paino);
                            keko.lisaa(ruudukko.getRuudukko()[loppu.getX()][loppu.getY()]);
                        }
                    }
                }
            }
        }
        muodostaReitti();
    }


    private void muodostaReitti() {
        Solmu solmu = ruudukko.getSolmu(ruudukko.getMaali().getX(), ruudukko.getMaali().getY());
        Lista<Solmu> reitti = new Lista<>();

        while (solmu != null) {
            reitti.lisaa(solmu);
            solmu = solmu.getVanhempi();
        }
        ruudukko.piirraReitti(reitti, Tyyppi.DIJKSTRA);
    }

    /**
     * Metodi testejä varten.
     * @return
     */
    public Lista<Solmu> palautaReittiListana() {
        Solmu solmu = ruudukko.getSolmu(ruudukko.getMaali().getX(), ruudukko.getMaali().getY());
        Lista <Solmu> reitti = new Lista<>();
        while (solmu != null) {
            reitti.lisaa(solmu);
            solmu = solmu.getVanhempi();
        }
        return reitti;
    }


}
