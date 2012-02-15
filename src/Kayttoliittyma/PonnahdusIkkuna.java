/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import javax.swing.*;

/**
 * Luokka tekee ponnahdusikkunoita, joissa kysytään pelaajalta kysymyksiä
 * @author karoliee
 */
public class PonnahdusIkkuna {
    /**
     * Metodi tekee ponnahdusikkunan, jossa kysytään käyttäjältä lukua, eli
     * korttiparien määrää
     * 
     * @param kysymys kysymys, joka kysytään käyttäjältä ikkunassa
     *
     * @return luku kokonaisluku, joka on käyttäjän antama vastaus kysymykseen
     */
    public static int kysyLuku(String kysymys) {
        String tarjottuArvo = "";
        int luku = 0;
        boolean tarjottuArvoOnKokonaisluku = false;
        do {
            try {
                tarjottuArvo = JOptionPane.showInputDialog(kysymys);
                luku = Integer.parseInt(tarjottuArvo);
                tarjottuArvoOnKokonaisluku = true;
            } catch (Exception e) {
            }
        } while (!tarjottuArvoOnKokonaisluku);

        return luku;
    }
     /**
     * Metodi tekee ponnahdusikkunan, jossa kysytään käyttäjältä jotain, ja
     * annetaan vastausvaihtoehtoina nappuloita, joista yhtä pitää painaa
     * 
     * @param kysymys kysymys, johon käyttäjän pitää vastata
     * 
     * @param valinnat valinnat, jotka näytetään nappuloina, joista käyttäjän
     * pitää valita yksi, joka on hänen vastauksensa kysymykseen
     *
     * @return nappulan indeksi, eli tieto siitä, monettako nappulaa
     * painettiin
     */

    public static int valitseNappula(String kysymys, String... valinnat) {
        return JOptionPane.showOptionDialog(null,
                kysymys,
                "",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                valinnat,
                valinnat[0]);
    }
}
