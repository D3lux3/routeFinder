package logiikka;


public class Kaari {

    public Solmu alku;
    public Solmu loppu;
    public double paino;

    /**
     * Luo Kaari olion, jolla on alkusolmu, loppusolmu ja paino.
     * @param alku
     * @param loppu
     * @param paino
     */
    public Kaari(Solmu alku, Solmu loppu, double paino) {
        this.alku = alku;
        this.loppu = loppu;
        this.paino = paino;
    }
}
