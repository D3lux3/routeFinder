package logiikka;

import java.util.Objects;

public class Solmu {

    private int x;
    private int y;
    private Tyyppi tyyppi;

    /**
     *
     * @param x
     * @param y
     * @param tyyppi
     *
     * Solmu olion konstruktori.
     *
     */
    public Solmu(int x, int y, Tyyppi tyyppi) {
        this.x = x;
        this.y= y;
        this.tyyppi = tyyppi;
    }

    /**
     * Palauttaa solmun X arvon.
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     *
     * Palauttaa solmun Y arvon.
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Palauttaa solmun tyypin.
     * @return
     */
    public Tyyppi getTyyppi() {
        return tyyppi;
    }

    @Override
    public String toString() {
        return "Y:" + this.y + " X:" + this.x + " Tyyppi: " + this.tyyppi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solmu solmu = (Solmu) o;
        return x == solmu.x &&
                y == solmu.y &&
                tyyppi == solmu.tyyppi;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, tyyppi);
    }
}
