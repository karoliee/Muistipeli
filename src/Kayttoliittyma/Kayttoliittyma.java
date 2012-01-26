/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Logiikka.*;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author karoliee
 */
public class Kayttoliittyma extends JPanel {

    public JButton[] kortit;
    KortinKuuntelija kortinkuuntelija;
    JFrame ikkuna;
    Container kayttoliittyma;

    public Kayttoliittyma() {
        teeKortit();
        kortinkuuntelija = new KortinKuuntelija();
        ikkuna = new JFrame();
        kayttoliittyma = ikkuna.getContentPane();
        ikkuna.setSize(500, 500);
        ikkuna.setTitle("Muistipeli");
        ikkuna.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        asetaKortit();
        ikkuna.setVisible(true);

    }

    public void teeKortit() {
        JButton[] kortit = new JButton[5];
        //tässä luo jButtonit, määrä myöhemmin riippuvaksi jostain muusta
        for (int i = 0; i < kortit.length; i++) {
            kortit[i] = new JButton();
            kortit[i].addActionListener(kortinkuuntelija);
        }
    }

    public void asetaKortit() {
        //aseta täällä myös ehkä pelin aloittamis- ja lopettamisnapit? JButtonit 
        // myös, mutta tee ne kanssa jossain metodissa ensin
        for (int i = 0; i < kortit.length; i++) {
            kayttoliittyma.add(kortit[i]);
        }
    }
}
