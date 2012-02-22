/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logiikka;

/**
 * Luokka pitää kirjaa pelaajan nimestä ja tämän erilaisista pistelaskuista
 *
 * @author karoliee
 */
public class Pelaaja {

    /**
     * pelaajan löytämien parien määrä
     */
    int loydettyjenKorttiparienMaara;
    /**
     * montako kertaa pelaaja on yrittänyt arvata korttiparia (sekä löydetyt
     * parit että epäonnistuneet yritykset)
     */
    int yritystenMaara;
    /**
     * pelaajan nimi
     */
    String pelaajanNimi;

    /**
     * Konstruktori luo pelaajan, ja asettaa tälle nimen
     *
     * @param nimi Pelaajan nimi
     *
     */
    public Pelaaja(String nimi) {
        loydettyjenKorttiparienMaara = 0;
        yritystenMaara = 0;
        pelaajanNimi = nimi;
    }

    /**
     * Kun pelaaja on löytänyt uuden korttiparin, metodi kasvattaa löydettyjen
     * parien määrää
     */
    public void loydettyjenKorttiparienMaaranKasvu() {
        loydettyjenKorttiparienMaara++;
    }

    /**
     * Metodi nollaa löydettyjen parien määrän
     */
    public void loydettyjenKorttiparienMaaranNollaus() {
        loydettyjenKorttiparienMaara = 0;
    }

    /**
     * Metodi palauttaa löydettyjen parien määrän
     *
     * @return löydettyjen parien määrä
     */
    public int getLoydettyjenKorttiparienMaara() {
        return loydettyjenKorttiparienMaara;
    }

    /**
     * Metodi kasvattaa yrityksien määrää yhdellä
     */
    public void yritystenMaaranKasvu() {
        yritystenMaara++;
    }

    /**
     * Metodi nollaa yritysten määrän
     */
    public void yritystenMaaranNollaus() {
        yritystenMaara = 0;
    }

    /**
     * Metodi palauttaa yrityksien määrän
     *
     * @return yrityksien määrä
     */
    public int getYritystenMaara() {
        return yritystenMaara;
    }

    /**
     * Metodi nollaa pelaajan tulokset
     */
    public void tulostenNollaus() {
        loydettyjenKorttiparienMaaranNollaus();
        yritystenMaaranNollaus();
    }

    /**
     * Metodi vaihtaa pelaajan nimeä
     *
     * @param nimi pelaajan uusi nimi
     *
     */
    public void setPelaajanNimi(String nimi) {
        pelaajanNimi = nimi;
    }

    /**
     * Metodi palauttaa pelaajan sen hetkisen nimen
     *
     * @return pelaajan nykyinen nimi
     */
    public String getPelaajanNimi() {
        return pelaajanNimi;
    }
}
