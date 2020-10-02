package logiikka;

import Tietorakenteet.Lista;

public class Ruudukko {

    private int x;
    private int y;
    private Solmu ruudukko[][];

    private Solmu aloitus;
    private Solmu maali;
    private int reitinPituus;
    private Lista<Kaari>[][] vieruslista;
    private int[][] lista;

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
        this.lista = new int[x][y];

        this.nollaaVieruslistat();
        this.nollaaTaulukko();
    }

    /**
     * Alustaa jokaisen ruudukon kohdan tyhjäksi.
     */
    public void nollaaTaulukko() {
        reitinPituus = 0;
        int i = 1;
        for (int x = 0; x < ruudukko.length; x++) {
            for (int y = 0; y < ruudukko[0].length; y++) {
                ruudukko[x][y] = new Solmu(x, y, Tyyppi.TYHJA);
                lista[x][y] = i;
                i++;
            }
        }
        this.aloitus = null;
        this.maali = null;
    }

    /**
     * Palauttaa annetuille koordinaateille järjestysnumeron.
     * @param x
     * @param y
     * @return
     */
    public int getJarjestysnumero(int x, int y) {
        return lista[x][y];
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
     * Alustaa vieruslistat tyhjiksi listoiksi.
     */
    private void nollaaVieruslistat() {
        for (int i = 0; i < this.x; i++) {
            for (int j = 0; j < this.y; j++) {
                /*
                i = x koordinaatti
                j = y koordinaatti

                 */
                this.vieruslista[i][j] = new Lista<>();
            }
        }
    }

    /**
     * Etsii jokaiselle Solmu oliolle naapurit ja lisöö sen vieruslistaesitykseen. Metodi käynnistyy kun käyttäjä päästää hiirestä irti
     */
    public void muodostavieruslista() {
        //Tyhjätään vieruslistat, ennen muodostusta.
        this.nollaaVieruslistat();

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
                if (ruudukko[x][y].getTyyppi() == Tyyppi.DIJKSTRA || ruudukko[x][y].getTyyppi() == Tyyppi.FRINGE) {
                    ruudukko[x][y] = new Solmu(x, y, Tyyppi.TYHJA);
                }
            }
        }

    }


    /**
     * Piirtää annetuista solmuista reitin simulaatioon.
     * @param reitti
     */
    public void piirraReitti(Lista<Solmu> reitti, Tyyppi tyyppi) {
        for (int i = 0; i <  reitti.koko(); i++) {
            if (reitti.hae(i).getTyyppi() != Tyyppi.ALOITUS && reitti.hae(i).getTyyppi() != Tyyppi.MAALI) {
                lisaaTyyppi(reitti.hae(i).getX(), reitti.hae(i).getY(), tyyppi);
            }
        }
    }

    /**
     * Palauttaa kaksiuloitteisen ruudukko olion.
     * @return
     */
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

    /**
     * Asettaa solmulle vanhemman solmun
     * @param x
     * @param y
     * @param solmu
     */

    public void setSolmunVanhempi(int x, int y, Solmu solmu) {
        this.ruudukko[x][y].setVanhempi(solmu);
    }

    /**
     * Asettaa solmulle tietyn etäisyyden.
     * @param x
     * @param y
     * @param etaisyys
     */
    public void setSolmunEtaisyys(int x, int y, double etaisyys) {
        this.ruudukko[x][y].setEtaisyys(etaisyys);
    }


    /**
     * Nollaa solmujen vanhemmat.
     */
    public void nollaaSolmujenVanhemmat() {
        for (int x = 0; x < this.x; x++) {
            for (int y = 0; y < this.y; y++) {
                this.ruudukko[x][y].setVanhempi(null);
            }
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

    /**
     * Palauttaa kahden pisteen välisen heuristisen arvion.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public double pisteidenHeuristinen(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int min = Math.min(dx, dy);
        int max = Math.max(dx, dy);

        int diakonaaliset = min;
        int suorat = max - min;

        return Math.sqrt(2) * diakonaaliset + suorat;
    }
}
