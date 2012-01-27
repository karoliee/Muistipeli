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

    public Logiikka() {

        korttienNumerot = new ArrayList<Integer>();
    }
    /**
     * Metodi luo listan, jonka alkiot ovat pelikorttejen "kuvat" eli numerot.
     * Kaikkia numeroita on kaksi kappaletta taulukossa. Tämän jälkeen metodi
     * sekoittaa listan
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
    public void kaannaKortti() {
    }

    /**
     * Metodi testaa ovatko kaksi korttia samoja, eli ovatko niihin liittyvät
     * numerot samoja
     */
    public boolean testaaOvatkoKortitSamoja() {
        return false;
    }
}
