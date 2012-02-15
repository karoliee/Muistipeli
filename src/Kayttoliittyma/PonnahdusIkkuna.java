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
     * Metodi 
     *
     * @return luku 
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
