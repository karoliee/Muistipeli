/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Logiikka.Logiikka;
import java.awt.*;
import java.awt.event.*;
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
//            kortit[i].addActionListener(new NapinKuuntelija());
            //tässä luo jButtonit, määrä myöhemmin riippuvaksi jostain muusta
        }

    }
    //    tänne actionListener jne...

//    public class NapinKuuntelija implements ActionListener {
//
//       public void actionPerformed(ActionEvent e) {
//            System.out.println("Nappia painettu!");
//        }
//    }
}
