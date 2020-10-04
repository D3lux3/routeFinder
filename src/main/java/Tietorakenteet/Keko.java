package Tietorakenteet;

import logiikka.Solmu;

public class Keko{

    private Solmu[] solmut;
    private int indeksi;

    /**
     * Luo Keko olion. Keon alkukooksi tulee 20.
     */
    public Keko(){
        this.solmut = new Solmu[20];
        indeksi = 0;
    }

    /**
     * Palauttaa pienimmän arvon keosta.
     * @return
     */
    public Solmu min() {
        return solmut[1];
    }

    /**
     * Lisää kekoon parametrina annetun Solmun.
     * @param solmu
     */
    public void lisaa(Solmu solmu) {
        if (indeksi + 1 == solmut.length) {
            this.kasva();
        }
        indeksi++;
        solmut[indeksi] = solmu;
        int p = indeksi;
        //Jos ei toimi, nii se on tästä comparesta kiinni. Käännnä ympäri jos ei muuta
        while (p > 1 && solmu.compareTo(solmut[vanhempi(p)]) < 1) {
            Solmu temp = solmut[vanhempi(p)];
            solmut[vanhempi(p)] = solmut[p];
            p = vanhempi(p);
        }
        solmut[p] = solmu;
    }

    /**
     * Poistaa pienimmän solmun keosta.
     * @return
     */
    public Solmu poistaMin() {
        Solmu solmu = solmut[1];
        solmut[1] = solmut[indeksi];
        indeksi--;
        painaAlas(1);
        return solmu;
    }

    /**
     * Varmistaa, että kekoehto pysyy voimassa. Järjestämällä keon uudestaan koon mukaan.
     * @param p
     */
    public void painaAlas(int p) {
        int pienempiLapsi = -1;
        if (vasen(p) == 0) {
            return;
        } else if (vasen(p) == indeksi) {
             pienempiLapsi = vasen(p);
        } else {
            //Jos ei toimi, nii se on tästä comparesta kiinni. Käännnä ympäri jos ei muuta
            if (solmut[vasen(p)].compareTo(solmut[oikea(p)]) < 1) {
                pienempiLapsi = vasen(p);
            } else {
                pienempiLapsi = oikea(p);
            }
        }
        if (solmut[p].compareTo(solmut[pienempiLapsi]) > -1) {
            Solmu temp = solmut[p];
            solmut[p] = solmut[pienempiLapsi];
            solmut[pienempiLapsi] = temp;
            painaAlas(pienempiLapsi);
        }
    }

    /**
     * Jos keko on menossä täyteen, niin tämä metodi kasvattaa keon kokoa.
     */
    private void kasva() {
        Solmu uus[] = new Solmu[this.solmut.length * 2];
        for (int i = 0; i < solmut.length; i++) {
            uus[i] = solmut[i];
        }
        this.solmut = uus;
    }

    private int vasen(int p) {
        if (2 * p > indeksi) {
            return 0;
        }
        else return 2 * p;
    }

    private int oikea(int p) {
        if (2 * p + 1 > indeksi) {
            return 0;
        }
        else return 2 * p + 1;
    }

    private int vanhempi(int p) {
        return p/2;
    }

    /**
     * Palauttaa true, jos keossa ei ole yhtään solmua.
     * @return
     */
    public boolean onkoTyhja() {
        return solmut[1] == null;
    }

    /**
     * Palauttaa Keon koon.
     * @return
     */
    public int koko() {
        return indeksi;
    }

}
