package algoritmit;

import Tietorakenteet.Lista;
import logiikka.Kaari;
import logiikka.Ruudukko;
import logiikka.Solmu;
import logiikka.Tyyppi;

public class Fringe {

    private Ruudukko ruudukko;
    private double[] cache1;
    private Solmu[] cache2;
    private Lista<Solmu> reitti;

    public Fringe(Ruudukko ruudukko) {
        this.ruudukko = ruudukko;
    }

    public void algo() {

        this.cache1 = new double[ruudukko.getX()*ruudukko.getY() + 1];
        this.cache2 = new Solmu[ruudukko.getX()*ruudukko.getY() + 1];
        this.reitti = new Lista();



        Lista<Solmu> now = new Lista<>();
        Lista<Solmu> later = new Lista<>();

        double fRaja = ruudukko.pisteidenHeuristinen(ruudukko.getAloitus().getX(), ruudukko.getAloitus().getY(), ruudukko.getMaali().getX(), ruudukko.getMaali().getY());
        boolean loydetty = false;


        now.lisaa(ruudukko.getAloitus());

        cache1[ruudukko.getJarjestysnumero(ruudukko.getAloitus().getX(), ruudukko.getAloitus().getY())] = 0;
        cache2[ruudukko.getJarjestysnumero(ruudukko.getAloitus().getX(), ruudukko.getAloitus().getY())] = null;


        while (loydetty == false && now.koko() != 0) {
            double fmin = Integer.MAX_VALUE;

            for (int i = 0; i < now.koko(); i++) {
                Solmu solmu = now.hae(i);
                double gAskeleet = cache1[ruudukko.getJarjestysnumero(solmu.getX(), solmu.getY())];


                //Solmun koordinaatit.
                int x = solmu.getX();
                int y = solmu.getY();

                double f = gAskeleet + ruudukko.pisteidenHeuristinen(x,y,
                        ruudukko.getMaali().getX(),ruudukko.getMaali().getY());

                if (f > fRaja) {
                    fmin = Math.min(f, fmin);
                    continue;
                }
                if (ruudukko.getTyyppi(x,y) == Tyyppi.MAALI) {
                    loydetty = true;
                    break;
                }

                //Lapsien läpikäynti
                for (int j = 0; j < ruudukko.getVieruslista()[x][y].koko(); j++) {
                    Kaari kaari = (Kaari) ruudukko.getVieruslista()[x][y].hae(j);
                    Solmu lapsi = kaari.loppu;

                    double gLapsi = gAskeleet + kaari.paino;



                    if (cache2[ruudukko.getJarjestysnumero(lapsi.getX(),lapsi.getY())] != null) {
                        if (gLapsi >= cache1[ruudukko.getJarjestysnumero(lapsi.getX(),lapsi.getY())]) {
                            continue;
                        }
                    }

                    if (now.sisaltaako(lapsi)) {
                        now.poista(lapsi);
                    }

                    later.lisaa(lapsi);

                    cache1[ruudukko.getJarjestysnumero(lapsi.getX(),lapsi.getY())] = gLapsi;
                    cache2[ruudukko.getJarjestysnumero(lapsi.getX(),lapsi.getY())] = solmu;

                }
                now.poista(solmu);

                for (int j  = 0; j < later.koko(); j++) {
                    now.lisaa(later.hae(j));
                }
                later = new Lista();

            }

            fRaja = fmin;

        }



        if (loydetty) {
            cache2[ruudukko.getJarjestysnumero(ruudukko.getAloitus().getX(), ruudukko.getAloitus().getY())] = null;
            muodostaReitti(ruudukko.getMaali());
            ruudukko.piirraReitti(reitti, Tyyppi.FRINGE);
        }

    }

    private void muodostaReitti(Solmu solmu) {
        Solmu vanhempi = cache2[this.ruudukko.getJarjestysnumero(solmu.getX(), solmu.getY())];
        if (vanhempi != null) {
            muodostaReitti(vanhempi);
        }
        reitti.lisaa(solmu);
    }



}
