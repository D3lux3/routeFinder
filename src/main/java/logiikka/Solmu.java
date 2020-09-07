package logiikka;

import java.util.Objects;

public class Solmu {

    private int x;
    private int y;
    private Tyyppi tyyppi;

    public Solmu(int x, int y, Tyyppi tyyppi) {
        this.x = x;
        this.y= y;
        this.tyyppi = tyyppi;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

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
