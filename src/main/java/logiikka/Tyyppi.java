package logiikka;

/**
 * Sisältää neljä eri tyyppiä jota Solmu olio voi olla.
 *
 * Jos solmun tyyppi on fringe/dijkstra, niin solmu on osa fringen/dijkstra muodostamaa reittiä.
 *
 */
public enum Tyyppi {
    TYHJA, SEINA, ALOITUS, MAALI, FRINGE, DIJKSTRA;
}
