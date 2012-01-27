/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logiikka;

/**
 *
 * @author karoliee
 */
public class Pelaaja {

    int arvattujenKorttiparienMaara;
    int yritystenMaara;
    String pelaajanNimi;

    public Pelaaja(String nimi) {
        arvattujenKorttiparienMaara = 0;
        yritystenMaara = 0;
        pelaajanNimi = nimi;
    }

    public void arvattujenKorttienMaaranKasvu() {
        arvattujenKorttiparienMaara++;
    }

    public void arvattujenKorttiparienMaaranNollaus() {
        arvattujenKorttiparienMaara = 0;
    }

    public int getArvattujenKorttiparienMaara() {
        return arvattujenKorttiparienMaara;
    }

    public void yritystenMaaranKasvu() {
        yritystenMaara++;
    }

    public void yritystenMaaranNollaus() {
        yritystenMaara = 0;
    }

    public int getYritystenMaara() {
        return yritystenMaara;
    }

    public void nimenVaihto(String nimi) {
        pelaajanNimi = nimi;
    }

    public String getPelaajanNimi() {
        return pelaajanNimi;
    }
}
