/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logiikka;

import java.util.*;
import javax.swing.*;

/**
 * Luokka ohjaa pelin pelaamista ja muuta toimintaa pelissä
 *
 * @author karoliee
 */
public class Peli {

    /**
     * Lista korttien "kuvista", eli numeroista. Jokaista numeroa on siis kaksi
     * kappaletta
     */
    ArrayList<Integer> korttienNumerot;
    /**
     * Olio, joka pelaa peliä
     */
    Pelaaja pelaaja;
    /**
     * Kertoo, onko käännetty kortti ensimmäinen käännetty vai toinen
     */
    boolean onEnsimmainenKortti;
    /**
     * Kertoo ensimmäiseksi käännetyn kortin järjestysnumeron
     */
    int ensimmaisenKortinJarjestysNumero;
    /**
     * Kertoo toiseksi käännetyn kortin järjestysnumeron
     */
    int toisenKortinJarjestysNumero;

    /**
     * Konstruktori luo luokan oliot
     */
    public Peli() {

        korttienNumerot = new ArrayList<Integer>();
        pelaaja = new Pelaaja("matti");
        onEnsimmainenKortti = true;
        ensimmaisenKortinJarjestysNumero = -1;
    }

    /**
     * Metodi luo listan, jonka alkiot ovat pelikorttejen "kuvat" eli numerot.
     * Kaikkia numeroita on kaksi kappaletta taulukossa. Tämän jälkeen metodi
     * sekoittaa listan
     *
     * @param korttienMaara pelikorttien määrä
     */
    public void teeNumerotKorttejaVartenJaSekoitaNe(int korttienMaara) {
        for (int i = 1; i <= korttienMaara / 2; i++) {
            korttienNumerot.add(i);
            korttienNumerot.add(i);
        }
        Collections.shuffle(korttienNumerot);

    }

    /**
     * Metodi katsoo, onko käännetty kortti ensimmäinen vai toinen
     */
    public String kaannaKortti(int kortinJarjestysNumero) {
        if (onEnsimmainenKortti) {
            ensimmaisenKortinJarjestysNumero = kortinJarjestysNumero;
            onEnsimmainenKortti = false;
            return "Oli ensimmäinen kortti";
        } else {
            if (kortinJarjestysNumero == ensimmaisenKortinJarjestysNumero) {
                return "Painoit samaa korttia!";
            }
            pelaaja.yritystenMaaranKasvu();
            toisenKortinJarjestysNumero = kortinJarjestysNumero;
            if (testaaOvatkoKortitSamoja(ensimmaisenKortinJarjestysNumero,
                    kortinJarjestysNumero)) {
                pelaaja.loydettyjenKorttiparienMaaranKasvu();
                onEnsimmainenKortti = true;
                return "Kortit olivat samoja";
            } else {
                onEnsimmainenKortti = true;
                return "Kortit eivät olleet samoja";
            }

        }
    }

    /**
     * Metodi palauttaa ensimmäisenä käännetyn kortin järjestysnumeron
     *
     * @return kortin järjestysnumero
     */
    public int getEnsimmaisenKortinJarjestysNumero() {
        return ensimmaisenKortinJarjestysNumero;
    }

    /**
     * Metodi palauttaa toisena käännetyn kortin järjestysnumeron
     *
     * @return kortin järjestysnumero
     */
    public int getToisenKortinJarjestysNumero() {
        return toisenKortinJarjestysNumero;
    }

    /**
     * Metodi testaa ovatko kaksi korttia samoja, eli ovatko niihin liittyvät
     * numerot samoja
     *
     * @param ensimmaisenKortinJarjestysNumero kertoo, monesko ensimmäiseksi
     * valittu kortti on korttitaulukossa
     * @param toisenKortinJarjestysNumero kertoo, monesko toiseksi valittu
     * kortti on korttitaulukossa
     */
    public boolean testaaOvatkoKortitSamoja(int ensimmaisenKortinJarjestysNumero,
            int toisenKortinJarjestysNumero) {
        if (korttienNumerot.get(ensimmaisenKortinJarjestysNumero).equals(
                korttienNumerot.get(toisenKortinJarjestysNumero))) {
            return true;

        }
        return false;
    }

    /**
     * Metodi palauttaa kortin arvon merkkijonona
     *
     * @param kortinJarjestysNumero kertoo, monesko valittu kortti on
     * korttitaulukossa
     * @return kortin arvo merkkijonona
     */
    public String kortinArvoMerkkiJonona(int kortinJarjestysNumero) {
        return Integer.toString(korttienNumerot.get(kortinJarjestysNumero));
    }

    /**
     * Metodi palauttaa pelin pelaajan
     *
     * @return pelin pelaaja
     */
    public Pelaaja getPelaaja() {
        return pelaaja;
    }

    /**
     * Metodi palauttaa valitun kortin arvon
     *
     * @return kortin arvo
     */
    public int getKortinArvo(int kortinJarjestysNumero) {
        return korttienNumerot.get(kortinJarjestysNumero);
    }
}
