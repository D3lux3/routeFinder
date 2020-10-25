# Toteutusdokumentaatio


## Rakenne

- Algoritmit
    - Fringe
    - Dijkstra
- Logiikka
    - Kaari
    - Ruudukko
    - Solmu
    - Tyyppi
- Tietorakenteet
    - Keko
    - Lista
- Työkalut
    - Matematiikka

![alt text](https://github.com/D3lux3/routeFinder/blob/master/documentation/luokkakaavio.jpg "UML luokkakaavio")

## Toteutus

#### Fringe
Toteutin Fringen pääasiassa Wikipedia sivun ja artikkelin(https://webdocs.cs.ualberta.ca/~holte/Publications/fringe.pdf) avulla.
Päättelin oman analyysin perusteella, että pahin aikavaativuus olisi O(n^2).

#### Dijkstra
Toteutin Dijkstran tirakirjan ja luentomateriaalien avulla. Dijkstran pahin aikavaativuus on O(n + m log m).

#### Kaari
Kaari olio, sisältää tiedon kaaren painosta ja sen alku ja loppu solmuista.

#### Ruudukko
Kaksi uloitteinen taulu, jolla on tieto jokaisessa koordinaatissa olevasta solmusta ja sen järjestysnumerosta.
Ruudukko myös pitää kirjaa aloitus & maali solmuista.

#### Solmu
Kantaa tietoa koordinaateista, jossa solmu on, ja tietoa solmun tyypistä, jotka voivat olla muotoa Tyyppi.

#### Tyyppi
Enum luokka joka voi olla jotain seuraavista: (TYHJÄ, SEINÄ, ALOITUS, MAALI, FRINGE, DIJKSTRA). Dijkstra on dijkstran algoritmin muodostamaa reittiä varten, ja Fringe taas fringen reittiä varten.

#### Keko
Luo keko tietorakenteen, jolla on vain tähän projektiin tarvittavat toiminnallisuudet.

#### Lista
Luo lista tietorakenteen, jolla on vain tähän projektiin tarvittavat toiminnallisuudet.

#### Matematiikka
Pitää sisällään vastineet javan Math.min ja Math.max vastineet.

## Puutteet ja parannettavaa



## Lähteet
<br/>
https://www.cs.helsinki.fi/u/ahslaaks/tirakirja/
<br/>
https://en.wikipedia.org/wiki/Fringe_search
<br/>
https://webdocs.cs.ualberta.ca/~holte/Publications/fringe.pdf
