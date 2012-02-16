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
     * Kertoo, onko pelilaudalla kaksi korttia vielä ylöspäin käännettynä
     * odottamassa ajastimen ajan loppumista. Tällöin ei tietysti voi kääntää
     * uutta korttia
     */
    boolean kaksiKorttiaOnKaannettyna;
    /**
     * Lista korttien "kuvista", eli arvoista. Jokaista arvoa on siis kaksi
     * kappaletta
     */
    ArrayList<Integer> korttienArvot;
    /**
     * Olio, joka pelaa peliä, eli pelaajan ilmentymä
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
     * Konstruktori luo luokan oliot ja asettaa muuttujille arvot
     */
    public Peli() {

        korttienArvot = new ArrayList<Integer>();
        pelaaja = new Pelaaja("matti");
        onEnsimmainenKortti = true;
        ensimmaisenKortinJarjestysNumero = -1;
        kaksiKorttiaOnKaannettyna = false;
    }

    /**
     * Metodi vaihtaa listan, jonka alkiot ovat pelikorttejen "kuvat" eli arvot,
     * alkiot parametrina olevan listan alkioilla, eli asettaa korteille uudet
     * arvot
     *
     * @param ArrayList<Integer> korttienArvot pelikorttien uudet arvot listana
     */
    public void setKorttienArvot(ArrayList<Integer> korttienArvot) {
        this.korttienArvot = korttienArvot;
    }

    /**
     * Metodi luo listan, jonka alkiot ovat pelikorttejen "kuvat" eli arvot.
     * Kaikkia arvoja on kaksi kappaletta taulukossa. Tämän jälkeen metodi
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
     * Metodi palauttaa korttien arvojen määrän, eli korttien lukumäärän
     *
     * @return korttien arvojen määrä
     */
    public int getArvojenMaara() {
        return korttienArvot.size();
    }

    /**
     * Metodi katsoo, onko käännetty kortti ensimmäinen vai toinen vai
     * yritettiinkö kääntää samaa korttia, mikä on vielä käännettynä
     * pelilaudalla
     *
     * @param kortinJarjestysNumero kertoo, monesko kortti on
     *
     * @return merkkijono, joka kertoo mitä huomattiin kun kortti käännettiin
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
                kaksiKorttiaOnKaannettyna = true;
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
     *
     * @return true tai false riippuen siitä, ovatko kortit samoja
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
     * Metodi vaihtaa totuusarvoa muuttujalle, joka kertoo onko kaksi korttia
     * vielä ylöspäin käännettynä pelilaudalla
     *
     * @param onkoKaksiKorttiaKaannetty true tai false sen mukaan, onko
     * pelilaudalla kaksi korttia vielä ylöspäin käännettyinä
     */
    public void setKaksiKorttiaOnKaannettyna(boolean onkoKaksiKorttiaKaannetty) {
        kaksiKorttiaOnKaannettyna = onkoKaksiKorttiaKaannetty;
    }

    /**
     * Kertoo, onko pelilaudalla vielä kaksi korttia ylöspäin käännettynä
     * odottamassa ajastimen ajan loppumista
     *
     * @return true tai false sen mukaan, onko pelilaudalla kaksi korttia vielä
     * käännettyinä
     */
    public boolean getKaksiKorttiaOnKaannettyna() {
        return kaksiKorttiaOnKaannettyna;
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
     * Metodi palauttaa valitun kortin arvon merkkijonona
     *
     * @param kortinJarjestysNumero kertoo, monesko valittu kortti on
     * korttitaulukossa
     * @return kortin arvo merkkijonona
     */
    public String getKortinArvoMerkkiJonona(int kortinJarjestysNumero) {
        return Integer.toString(korttienArvot.get(kortinJarjestysNumero));
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
     * Metodi palauttaa pelin pelaajan
     *
     * @return pelin pelaaja
     */
    public Pelaaja getPelaaja() {
        return pelaaja;
    }
}
