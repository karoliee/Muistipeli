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

    public void paint(Graphics g) {
        super.paint(g);
    }

    public void teeKortit() {
        //ehkä taulukko JButtoneista tai arraylist?
        JButton[] kortit = new JButton[10];
        for (int i = 0; i < kortit.length; i++) {
            kortit[i] = new JButton();
            kortit[i].addActionListener(new KortinKuuntelija());
            //tässä luo jButtonit, määrä myöhemmin riippuvaksi jostain muusta
        }

    }
}
