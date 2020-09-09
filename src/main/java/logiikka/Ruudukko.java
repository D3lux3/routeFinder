package logiikka;

import Tietorakenteet.Lista;

public class Ruudukko {

    private int x;
    private int y;
    private Solmu ruudukko[][];

    private Solmu aloitus;
    private Solmu maali;

    private Lista<Solmu>[][] vieruslista;

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

    public void nollaaTaulukko() {
        for (int x = 0; x < ruudukko.length; x++) {
            for (int y = 0; y < ruudukko[0].length; y++) {
                ruudukko[x][y] = new Solmu(x, y, Tyyppi.TYHJA);
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Solmu getMaali() {
        return maali;
    }

    public Solmu getAloitus() {
        return aloitus;
    }

    public Solmu getSolmu(int x, int y) {
        if (tarkistaParametrit(x, y)) {
            return (ruudukko[x][y]);
        }
        return null;
    }

    private boolean tarkistaParametrit(int x, int y) {
        if (x <= this.x - 1 && x >= 0
                && y <= this.y && y >= 0) {
            return true;
        }
        return false;
    }

    private void yhdistaNaapurit(Solmu eka, Solmu toka) {
        this.vieruslista[eka.getX()][eka.getY()].lisaa(toka);
        this.vieruslista[toka.getX()][toka.getY()].lisaa(eka);
    }

    //Heti kun käyttäjä päästää hiirestä irti, etsitään vieruslistaan kaverit
    public void etsiNaapurit() {
        for (int x = 1; x < this.x; x++) {
            for (int y = 1; y < this.y; y++) {
                
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

    private void resetPolku() {
        for (int x = 0; x < this.x; x++) {
            for (int y = 0; y < this.y; y++) {
                if (ruudukko[x][y].getTyyppi() == Tyyppi.REITTI) {
                    ruudukko[x][y] = new Solmu(x, y, Tyyppi.REITTI);
                }
            }
        }
    }

    public Lista[][] getVieruslista() {
        return this.vieruslista;
    }

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

    public Tyyppi getTyyppi(int x, int y) {
        return ruudukko[x][y].getTyyppi();
    }
}
