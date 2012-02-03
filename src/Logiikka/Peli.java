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
    ArrayList<Integer> korttienArvot;
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

        korttienArvot = new ArrayList<Integer>();
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
    public void teeArvotKorttejaVartenJaSekoitaNe(int korttienMaara) {
        for (int i = 1; i <= korttienMaara / 2; i++) {
            korttienArvot.add(i);
            korttienArvot.add(i);
        }
        Collections.shuffle(korttienArvot);

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
            } else {
                onEnsimmainenKortti = true;
                pelaaja.yritystenMaaranKasvu();
                toisenKortinJarjestysNumero = kortinJarjestysNumero;
                if (testaaOvatkoKortitSamoja(ensimmaisenKortinJarjestysNumero,
                        kortinJarjestysNumero)) {
                    pelaaja.loydettyjenKorttiparienMaaranKasvu();
                    return "Kortit olivat samoja";
                } else {
                    return "Kortit eivät olleet samoja";
                }
            }
        }
    }

    /**
     * Metodi testaa ovatko kaksi korttia samoja, eli ovatko niihin liittyvät
     * arvot samoja
     *
     * @param ensimmaisenKortinJarjestysNumero kertoo, monesko ensimmäiseksi
     * valittu kortti on korttitaulukossa
     * @param toisenKortinJarjestysNumero kertoo, monesko toiseksi valittu
     * kortti on korttitaulukossa
     */
    public boolean testaaOvatkoKortitSamoja(int ensimmaisenKortinJarjestysNumero,
            int toisenKortinJarjestysNumero) {
        if (korttienArvot.get(ensimmaisenKortinJarjestysNumero).equals(
                korttienArvot.get(toisenKortinJarjestysNumero))) {
            return true;

        }
        return false;
    }

    /**
     * Metodi palauttaa valitun kortin arvon merkkijonona
     *
     * @param kortinJarjestysNumero kertoo, monesko valittu kortti on
     * korttitaulukossa
     * @return kortin arvo merkkijonona
     */
    public String kortinArvoMerkkiJonona(int kortinJarjestysNumero) {
        return Integer.toString(korttienArvot.get(kortinJarjestysNumero));
    }

    /**
     * Metodi palauttaa valitun kortin arvon
     *
     * @return kortin arvo
     */
    public int getKortinArvo(int kortinJarjestysNumero) {
        return korttienArvot.get(kortinJarjestysNumero);
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
}
