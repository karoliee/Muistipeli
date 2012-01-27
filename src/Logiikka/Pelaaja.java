/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logiikka;

/**
 *
 * @author karoliee
 */
class Pelaaja {
    
    int arvattujenKorttiparienMaara;
    
    public Pelaaja() {
        arvattujenKorttiparienMaara = 0;
    }
    public void arvattujenKorttienMaaranKasvu() {
        arvattujenKorttiparienMaara++;
    }
    public int arvattujenKorttiparienMaara() {
        return arvattujenKorttiparienMaara;
    }
    
}
