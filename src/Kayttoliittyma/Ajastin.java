/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
/**
 *
 * @author karoliee
 */
public class Ajastin extends Timer implements ActionListener {

    private Kayttoliittyma kayttis;

    public Ajastin(Kayttoliittyma peli, int aika) {
        super(aika, null);
        this.kayttis = peli;
        this.addActionListener(this);
        this.setRepeats(false);
    }

    public void actionPerformed(ActionEvent e) {
        kayttis.aikaOnKulunut();
    }
}
