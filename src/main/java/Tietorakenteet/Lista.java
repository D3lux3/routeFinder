package Tietorakenteet;

public class Lista <T> {

    private  T[] arvot;
    private int alkioita;

    public Lista() {
        this.arvot = (T[]) new Object[10];
        this.alkioita = 0;
    }

    public void lisaa(T arvo) {
        if (alkioita == this.arvot.length) {
            kasva();
        }
        arvot[alkioita] = arvo;
        alkioita++;
    }

    private void kasva() {
        T[] uus = (T[]) new Object[arvot.length * 2];
        for (int i = 0; i < arvot.length; i++) {
            uus[i] = arvot[i];
        }
        this.arvot = uus;
    }

    public int koko() {
        return this.alkioita;
    }

    public void alusta() {
        this.arvot = (T[]) new Object[10];
        this.alkioita = 0;
    }

    public T hae(int indeksi) {
        if (indeksi < 0 || indeksi >= alkioita) {
            throw new ArrayIndexOutOfBoundsException("Indeksi " + indeksi + " alueen [0, " + this.alkioita + "[ ulkopuolella.");
        }
        return arvot[indeksi];
    }
}
