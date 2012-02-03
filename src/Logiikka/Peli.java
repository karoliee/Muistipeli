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
    boolean ensimmainenKortti;
    /**
     * Kertoo ensimmäiseksi käännetyn kortin järjestysnumeron
     */
    int ensimmaisenKortinJarjestysNumero;

    /**
     * Konstruktori luo luokan oliot
     */
    public Peli() {

        korttienNumerot = new ArrayList<Integer>();
        pelaaja = new Pelaaja("matti");
        ensimmainenKortti = true;
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
     * Metodi kääntää kortin, eli kertoo siihen liittyvän numeron ja katsoo,
     * ovatko korttejen arvot samat
     */
    public void kaannaKortti(int kortinJarjestysNumero) {
        if (ensimmainenKortti = true) {
            //käännä kortti, setText(kortin arvo)
            ensimmaisenKortinJarjestysNumero = kortinJarjestysNumero;
            ensimmainenKortti = false;
        } else {
            //käännä kortti, setText(kortin arvo)
            if (testaaOvatkoKortitSamoja(ensimmaisenKortinJarjestysNumero,
                    kortinJarjestysNumero)) {
                //jos on, poista molemmat kortit, remove()
            } else {
                //jos ei, käännä kortit, setText("Muistipeli")
            }
            ensimmainenKortti = true;
            ensimmaisenKortinJarjestysNumero = -1;
        }
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
     * Metodi palauttaa pelin pelaajan
     *
     * @return pelin pelaaja
     */
    public Pelaaja getPelaaja() {
        return pelaaja;
    }
}
