/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logiikka;

/**
 *
 * @author karoliee
 */
class Pelaaja<String> {
    
    int arvattujenKorttiparienMaara;
    String pelaajanNimi;
    
    public Pelaaja(String nimi) {
        arvattujenKorttiparienMaara = 0;
        pelaajanNimi = nimi;
    }
    public void arvattujenKorttienMaaranKasvu() {
        arvattujenKorttiparienMaara++;
    }
    public int arvattujenKorttiparienMaara() {
        return arvattujenKorttiparienMaara;
    }
    public String pelaajanNimi() {
        return pelaajanNimi;
    }
    
}
