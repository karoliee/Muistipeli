/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Luokka hallitsee ajan kulumista
 *
 * @author karoliee
 */
public class Ajastin extends Timer implements ActionListener {

    /**
     * Käyttöliittymän ilmentymä
     */
    private Kayttoliittyma kayttis;

    /**
     * Konstruktori luo ajastimen
     *
     * @param kayttis käyttöliittymä
     * @param aika tietty aika, jonka ajan ajastin käy
     */
    public Ajastin(Kayttoliittyma kayttis, int aika) {
        super(aika, null);
        this.kayttis = kayttis;
        this.addActionListener(this);
        this.setRepeats(false);
    }

    /**
     * Metodi kutsuu käyttoliittymän metodia, kun ajastimen aika on kulunut
     *
     * @param e tapahtuma, eli ajan loppuminen
     */
    public void actionPerformed(ActionEvent e) {
        kayttis.aikaOnKulunut();
    }
}