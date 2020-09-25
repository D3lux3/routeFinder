package logiikka;

import Tietorakenteet.Lista;

import java.util.PriorityQueue;

public class Ruudukko {

    private int x;
    private int y;
    private Solmu ruudukko[][];

    private Solmu aloitus;
    private Solmu maali;

    private Lista<Kaari>[][] vieruslista;

    /**
     * Ruudukon konstruktori.
     * @param x
     * @param y
     */
    public Ruudukko(int x, int y) {
        this.x = x;
        this.y = y;
        this.ruudukko = new Solmu[x][y];
        this.vieruslista = new Lista[x][y];

        for (int i = 0; i < this.x; i++) {
            for (int j = 0; j < this.y; j++) {
                /*
                i = x koordinaatti
                j = y koordinaatti
                
                 */
                this.vieruslista[i][j] = new Lista<>();
            }
        }
        nollaaTaulukko();
    }

    /**
     * Alustaa jokaisen ruudukon kohdan tyhjäksi.
     */
    public void nollaaTaulukko() {
        for (int x = 0; x < ruudukko.length; x++) {
            for (int y = 0; y < ruudukko[0].length; y++) {
                ruudukko[x][y] = new Solmu(x, y, Tyyppi.TYHJA);
            }
        }
        this.aloitus = null;
        this.maali = null;
    }

    /**
     * Palauttaa ruudukon X akselin koon.
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Palauttaa ruudukon Y akselin koon.
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Palauttaa ruudukkoon asetetun maalin Solmu oliona.
     * @return
     */
    public Solmu getMaali() {
        return maali;
    }

    /**
     * Palauttaa ruudukkoon asetetun maalin Solmu oliona.
     * @return
     */
    public Solmu getAloitus() {
        return aloitus;
    }

    /**
     * Palauttaa Solmu olion tietystä kohtaa ruudukosta.
     * @param x
     * @param y
     * @return
     */
    public Solmu getSolmu(int x, int y) {
        if (tarkistaParametrit(x, y)) {
            return (ruudukko[x][y]);
        }
        return null;
    }

    /**
     * Tarkistaa, että onko annetut parametrit lailliset.
     * @param x
     * @param y
     * @return
     */
    private boolean tarkistaParametrit(int x, int y) {
        if (x < this.x && x >= 0
                && y < this.y && y >= 0) {
            return true;
        }
        return false;
    }

    /**
     * Yhdistää kaksi solmua kaarella keskenään suuntaamattomaan verkkoon.
     * @param eka
     * @param toka
     */
    public void yhdistaNaapurit(Solmu eka, Solmu toka, double paino) {
        this.vieruslista[eka.getX()][eka.getY()].lisaa(new Kaari(eka, toka, paino));
        this.vieruslista[toka.getX()][toka.getY()].lisaa(new Kaari(toka, eka, paino));
    }
    /**
     * Etsii jokaiselle Solmu oliolle naapurit ja lisöö sen vieruslistaesitykseen. Metodi käynnistyy kun käyttäjä päästää hiirestä irti
     */
    public void muodostavieruslista() {
        for (int x = 0; x < this.x; x++) {
            for (int y = 0; y < this.y; y++) {
                if (tarkistaParametrit(x,y)) {
                    if (tarkistaParametrit(x -1, y) && this.getTyyppi(x - 1, y) == Tyyppi.TYHJA) {
                        yhdistaNaapurit(ruudukko[x][y], ruudukko[x -1][y], 1);
                    }

                    if (tarkistaParametrit(x +1, y) && this.getTyyppi(x + 1, y) == Tyyppi.TYHJA) {
                        yhdistaNaapurit(ruudukko[x][y], ruudukko[x + 1][y], 1);
                    }

                    if (tarkistaParametrit(x , y - 1) && this.getTyyppi(x, y - 1) == Tyyppi.TYHJA) {
                        yhdistaNaapurit(ruudukko[x][y], ruudukko[x][y - 1], 1);
                    }

                    if (tarkistaParametrit(x, y + 1) && this.getTyyppi(x, y + 1) == Tyyppi.TYHJA) {
                        yhdistaNaapurit(ruudukko[x][y], ruudukko[x][y + 1], 1);
                    }

                    //Diagonaaliset
                    if (tarkistaParametrit(x -1, y - 1) && this.getTyyppi(x - 1, y - 1) == Tyyppi.TYHJA) {
                        yhdistaNaapurit(ruudukko[x][y], ruudukko[x -1][y - 1], Math.sqrt(2));
                    }

                    if (tarkistaParametrit(x + 1, y + 1) && this.getTyyppi(x + 1, y + 1) == Tyyppi.TYHJA) {
                        yhdistaNaapurit(ruudukko[x][y], ruudukko[x + 1][y + 1], Math.sqrt(2));
                    }

                    if (tarkistaParametrit(x + 1, y - 1) && this.getTyyppi(x + 1, y - 1) == Tyyppi.TYHJA) {
                        yhdistaNaapurit(ruudukko[x][y], ruudukko[x + 1][y - 1], Math.sqrt(2));
                    }

                    if (tarkistaParametrit(x - 1, y + 1) && this.getTyyppi(x - 1, y + 1) == Tyyppi.TYHJA) {
                        yhdistaNaapurit(ruudukko[x][y], ruudukko[x - 1][y + 1], Math.sqrt(2));
                    }

                    //Diagonaaliset
                }
            }
        }
    }


    /**
     * Poistaa algoritmin tuottaman polun pois ruudukolta.
     */
    public void resetPolku() {
        for (int x = 0; x < this.x; x++) {
            for (int y = 0; y < this.y; y++) {
                if (ruudukko[x][y].getTyyppi() == Tyyppi.REITTI) {
                    ruudukko[x][y] = new Solmu(x, y, Tyyppi.TYHJA);
                }
            }
        }

    }

    public void piirraReitti(Lista<Solmu> reitti) {
        for (int i = 0; i < reitti.koko(); i++) {
            Solmu solmu = reitti.hae(i);
            lisaaTyyppi(solmu.getX(), solmu.getY(), Tyyppi.REITTI);
        }
    }

    public Solmu[][] getRuudukko() {
        return ruudukko;
    }

    /**
     * Palauttaa vieruslistan.
     * @return
     */
    public Lista[][] getVieruslista() {
        return this.vieruslista;
    }

    /**
     *
     * Lisää annettuihin parametreihin Solmu-olion.
     * @param x
     * @param y
     * @param tyyppi
     */
    public void lisaaTyyppi(int x, int y, Tyyppi tyyppi) {
        if (tarkistaParametrit(x, y)) {

            if (tyyppi == Tyyppi.ALOITUS) {
                if (aloitus != null) {
                    return;
                }
                this.aloitus = new Solmu(x, y, Tyyppi.ALOITUS);
            }

            if (tyyppi == Tyyppi.MAALI) {
                if (maali != null) {
                    return;
                }
                this.maali = new Solmu(x, y, Tyyppi.MAALI);
            }

            this.ruudukko[x][y] = new Solmu(x, y, tyyppi);
        }
    }

    public void setSolmunVanhempi(int x, int y, Solmu solmu) {
        this.ruudukko[x][y].setVanhempi(solmu);
    }

    public void setSolmunEtaisyys(int x, int y, double etaisyys) {
        this.ruudukko[x][y].setEtaisyys(etaisyys);
    }

    /**
     * Palauttaa annetuista parametreistä Tyypin.
     * @param x
     * @param y
     * @return
     */
    public Tyyppi getTyyppi(int x, int y) {
        return ruudukko[x][y].getTyyppi();
    }

/*
    public void algo() {
        if (this.aloitus != null && this.maali != null) {

            PriorityQueue<Solmu> keko = new PriorityQueue<Solmu>();

            boolean kasitelty[][] = new boolean[this.x][this.y];
            for (int x = 0; x < this.x; x++) {
                for (int y = 0; y < this.y; y++) {
                    ruudukko[x][y].setEtaisyys(Integer.MAX_VALUE);
                    kasitelty[x][y] = false;
                }
            }

            //Alku pisteen etaisyyden asetus
            ruudukko[this.aloitus.getX()][this.aloitus.getY()].setEtaisyys(0);
            keko.add(ruudukko[this.aloitus.getX()][this.aloitus.getY()]);

            while (!keko.isEmpty()) {
                Solmu seuraava = keko.poll();

                if (!kasitelty[seuraava.getX()][seuraava.getY()]) {
                    kasitelty[seuraava.getX()][seuraava.getY()] = true;
                    for (int i = 0; i < this.vieruslista[seuraava.getX()][seuraava.getY()].koko(); i++) {
                        Kaari kaari = this.vieruslista[seuraava.getX()][seuraava.getY()].hae(i);
                        Solmu loppu = kaari.loppu;

                        if (loppu.getEtaisyys() > seuraava.getEtaisyys() + kaari.paino) {
                            ruudukko[loppu.getX()][loppu.getY()].setVanhempi(seuraava);
                            ruudukko[loppu.getX()][loppu.getY()].setEtaisyys(seuraava.getEtaisyys() + kaari.paino);
                            keko.add(ruudukko[loppu.getX()][loppu.getY()]);
                        }
                    }
                }
            }


            Solmu solmu = ruudukko[this.maali.getX()][this.maali.getY()];
            Lista <Solmu> reitti = new Lista<>(100);
            while (solmu != null) {
                reitti.lisaa(solmu);
                solmu = solmu.getVanhempi();
            }
            this.piirraReitti(reitti);

        }
    }
*/

}
