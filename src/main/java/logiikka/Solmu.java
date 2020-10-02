package logiikka;

import java.util.Objects;

public class Solmu implements Comparable<Solmu> {


    private int x;
    private int y;
    private Tyyppi tyyppi;
    private double etaisyys;
    private Solmu vanhempi;

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
        this.y = y;
        this.tyyppi = tyyppi;
        this.etaisyys = Integer.MAX_VALUE;
        this.vanhempi = null;
    }

    /**
     * Asettaa solmulle toisen solmu olion vanhemmaksi.
     * @param solmu
     */
    public void setVanhempi(Solmu solmu) {
        this.vanhempi = solmu;
    }

    /**
     * Palauttaa Solmun vanhemman
     * @return
     */
    public Solmu getVanhempi() {
        return this.vanhempi;
    }

    /**
     * Palauttaa solmun X arvon.
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Asettaa etaisyys muuttujalle arvon.
     * @param etaisyys
     */

    public void setEtaisyys(double etaisyys){
        this.etaisyys = etaisyys;
    }

    /**
     * Palauttaa etaisyys muuttujan.
     * @return
     */
    public double getEtaisyys() {
        return this.etaisyys;
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
        return "X:" + this.x + " | " + "Y:" + this.y + " | " +"Tyyppi: " + this.tyyppi;
    }

    @Override
    public int compareTo(Solmu toinen) {
        if (etaisyys > toinen.getEtaisyys()) {
            return 1;
        } else if (etaisyys < toinen.getEtaisyys()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
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
