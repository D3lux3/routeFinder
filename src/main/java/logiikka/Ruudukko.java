package logiikka;


public class Ruudukko {

    private int x;
    private int y;
    private Solmu ruudukko[][];

    private Solmu aloitus;
    private Solmu maali;

    public Ruudukko(int x, int y) {
        this.x = x;
        this.y = y;
        this.ruudukko = new Solmu[x][y];
        nollaaTaulukko();
    }

    public void nollaaTaulukko() {
        for (int x = 0; x < ruudukko.length; x++) {
            for (int y = 0; y < ruudukko[0].length; y++) {
                ruudukko[x][y] = new Solmu(x, y, Tyyppi.TYHJA);
            }
        }
    }

    private boolean tarkistaParametrit(int x, int y) {
        if (x <= this.x && x >= 0
                && y <= this.y && y >= 0) {
            return true;
        }
        return false;
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




    public void lisaaTyyppi(int x, int y, Tyyppi tyyppi) {
        if (tarkistaParametrit(x,y)) {

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
