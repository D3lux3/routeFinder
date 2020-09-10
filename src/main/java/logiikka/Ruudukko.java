package logiikka;

import Tietorakenteet.Lista;

public class Ruudukko {

    private int x;
    private int y;
    private Solmu ruudukko[][];

    private Solmu aloitus;
    private Solmu maali;

    private Lista<Solmu>[][] vieruslista;

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
                && y <= this.y && y >= 0) {
            return true;
        }
        return false;
    }

    /**
     * Yhdistää kaksi solmua keskenään suuntaamattomaan verkkoon.
     * @param eka
     * @param toka
     */
    private void yhdistaNaapurit(Solmu eka, Solmu toka) {
        this.vieruslista[eka.getX()][eka.getY()].lisaa(toka);
        this.vieruslista[toka.getX()][toka.getY()].lisaa(eka);
    }

    /**
     * Etsii jokaiselle Solmu oliolle naapurit ja lisöö sen vieruslistaesitykseen. Metodi käynnistyy kun käyttäjä päästää hiirestä irti
     */
    public void etsiNaapurit() {
        for (int x = 0; x < this.x; x++) {
            for (int y = 0; y < this.y; y++) {

                if (getTyyppi(x, y) != Tyyppi.SEINA) {

                    if (x - 1 > 0 && getTyyppi(x - 1, y) != Tyyppi.SEINA) {
                        yhdistaNaapurit(getSolmu(x, y), getSolmu(x - 1, y));
                    }

                    if (x + 1 <= x && getTyyppi(x + 1, y) != Tyyppi.SEINA) {
                        yhdistaNaapurit(getSolmu(x, y), getSolmu(x + 1, y));
                    }
                    if (y + 1 <= y && getTyyppi(x, y + 1) != Tyyppi.SEINA) {
                        yhdistaNaapurit(getSolmu(x, y), getSolmu(x, y + 1));
                    }

                    if (y - 1 > 0 && getTyyppi(x, y - 1) != Tyyppi.SEINA) {
                        yhdistaNaapurit(getSolmu(x, y), getSolmu(x, y - 1));
                    }

                }
            }
        }
    }

    /**
     * Poistaa algoritmin tuottaman polun pois ruudukolta.
     */
    private void resetPolku() {
        for (int x = 0; x < this.x; x++) {
            for (int y = 0; y < this.y; y++) {
                if (ruudukko[x][y].getTyyppi() == Tyyppi.REITTI) {
                    ruudukko[x][y] = new Solmu(x, y, Tyyppi.REITTI);
                }
            }
        }
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

    /**
     * Palauttaa annetuista parametreistä Tyypin.
     * @param x
     * @param y
     * @return
     */
    public Tyyppi getTyyppi(int x, int y) {
        return ruudukko[x][y].getTyyppi();
    }
}
