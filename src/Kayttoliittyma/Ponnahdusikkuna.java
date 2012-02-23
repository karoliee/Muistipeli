/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import javax.swing.*;

/**
 * Luokka tekee ponnahdusikkunoita, joissa kysytään pelaajalta kysymyksiä
 *
 * @author karoliee
 */
public class Ponnahdusikkuna {

    /**
     * Metodi tekee ponnahdusikkunan, jossa kysytään pelaajan jokin kysymys,
     * tässä tapauksessa pelaajan nimeä
     *
     * @param kysymys kysymys, joka kysytään käyttäjältä ikkunassa
     *
     * @return ikkuna, jossa kysytään kysymys, joka palauttaa sanan, ja joka
     * ikkunaan kirjoitetaan
     */
    public static String kysySana(String kysymys) {
        return JOptionPane.showInputDialog(kysymys);
    }

    /**
     * Metodi tekee ponnahdusikkunan, jossa kysytään käyttäjältä jotain
     * kokonaislukua, tässä tapauksessa korttiparien määrää. Vain kokonaisluvut
     * kelpaavat vastaukseksi. Kysymys toistetaan niin kauan kunnes käyttäjä
     * antaa vastauksena kokonaisluvun
     *
     * @param kysymys kysymys, joka kysytään käyttäjältä ikkunassa
     *
     * @return luku kokonaisluku, joka on käyttäjän antama vastaus kysymykseen
     */
    public static int kysyLuku(String kysymys) {
        String tarjottuArvo;
        int luku = 0;
        boolean tarjottuArvoOnKokonaisluku = false;
        while (!tarjottuArvoOnKokonaisluku) {
            try {
                tarjottuArvo = JOptionPane.showInputDialog(kysymys);
                luku = Integer.parseInt(tarjottuArvo);
                tarjottuArvoOnKokonaisluku = true;
            } catch (Exception e) {
            }
        }

        return luku;
    }

    /**
     * Metodi tekee ponnahdusikkunan, jossa kysytään käyttäjältä jokin kysymys,
     * ja annetaan vastausvaihtoehtoina nappulat, joista yhtä pitää painaa
     * vastauksena. Tällä kysytään, haluaako käyttäjä pelata yksin- vai
     * kaksinpeliä ja kaksinpelin tapauksessa, haluaako hän nimetä pelaajat
     *
     * @param kysymys kysymys, johon käyttäjän pitää vastata
     *
     * @param valinnat valinnat, jotka näytetään nappuloina, joista käyttäjän
     * pitää valita yksi, joka on hänen vastauksensa kysymykseen. Valinnat ovat
     * siis merkkijonoja jossain merkkijonotaulukossa
     *
     * @return ikkuna, jossa on kysymys ja vaihtoehdot, ja joka palauttaa
     * nappulan indeksin, eli tiedon siitä, monettako nappulaa painettiin
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
