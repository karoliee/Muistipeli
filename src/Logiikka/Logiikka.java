/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logiikka;

import java.util.*;
import javax.swing.*;

/**
 *
 * @author karoliee
 */
public class Logiikka {

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
     * Konstruktori luo luokan oliot
     */
    public Logiikka() {

        korttienNumerot = new ArrayList<Integer>();
        pelaaja = new Pelaaja("matti");
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
     * Metodi kääntää kortin, eli kertoo siihen liittyvän numeron
     */
    public void kaannaKaksiKorttia() {
        // käännä yksi, talleta arvo, käännä toinen, talleta arvo, katso 
        //ovatko samoja
        //jos on, poista kortit
        //jos ei, käännä kortit
    }

    /**
     * Metodi testaa ovatko kaksi korttia samoja, eli ovatko niihin liittyvät
     * numerot samoja
     *
     * @param ensimmaisenKortinJarjestysnumero kertoo, monesko ensimmäiseksi
     * valittu kortti on korttitaulukossa
     * @param toisenKortinJarjestysnumero kertoo, monesko toiseksi valittu
     * kortti on korttitaulukossa
     */
    public boolean testaaOvatkoKortitSamoja(int ensimmaisenKortinJarjestysnumero,
            int toisenKortinJarjestysnumero) {
        if (korttienNumerot.get(ensimmaisenKortinJarjestysnumero).equals(
                korttienNumerot.get(toisenKortinJarjestysnumero))) {
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
