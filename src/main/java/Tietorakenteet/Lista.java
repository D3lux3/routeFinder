package Tietorakenteet;

public class Lista <T> {

    private  T[] arvot;
    private int alkioita;

    /**
     * Luo listan, johon voi tallettaa haluttuja alkioita.
     */
    public Lista() {
        this.arvot = (T[]) new Object[10];
        this.alkioita = 0;
    }

    /**
     * Luo lista olion jolle voi antaa koon.
     */
    public Lista(int koko) {
        this.arvot = (T[]) new Object[koko];
        this.alkioita = 0;
    }


    /**
     * Lisää parametrina annetun arvon listaan.
     * @param arvo
     */
    public void lisaa(T arvo) {
        if (alkioita == this.arvot.length) {
            kasva();
        }
        arvot[alkioita] = arvo;
        alkioita++;
    }

    /**
     * Jos lista on menossä täyteen, niin tämä metodi kasvattaa listan kokoa.
     */
    private void kasva() {
        T[] uus = (T[]) new Object[arvot.length * 2];
        for (int i = 0; i < arvot.length; i++) {
            uus[i] = arvot[i];
        }
        this.arvot = uus;
    }

    /**
     * Palauttaa listan koon.
     * @return
     */
    public int koko() {
        return this.alkioita;
    }

    /**
     * Poistaa kaiken listalta.
     */
    public void alusta() {
        this.arvot = (T[]) new Object[10];
        this.alkioita = 0;
    }

    /**
     * Hakee ja palauttaa halutusta indeksistä alkion.
     * @param indeksi
     * @return
     */
    public T hae(int indeksi) {
        if (indeksi < 0 || indeksi >= alkioita) {
            throw new ArrayIndexOutOfBoundsException("Indeksi " + indeksi + " on liian iso tai liian pieni");
        }
        return arvot[indeksi];
    }

    /**
     * Kääntää kaikki listan järjestyksen ympäri.
     */
    public void kaannaYmpari() {
        T[] kaannetytArvot =(T[]) new Object[this.arvot.length];
        int indeksi = this.alkioita - 1;
        for (int i = 0; i <= this.arvot.length; i++) {
            if(arvot[indeksi] != null) {
                kaannetytArvot[i] = arvot[indeksi];
            }
            if (indeksi - 1 < 0) {
             break;
            }
            indeksi--;
        }
        this.arvot = kaannetytArvot;
    }
}
