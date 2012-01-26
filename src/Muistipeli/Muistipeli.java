/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Muistipeli;

import Kayttoliittyma.*;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author karoliee
 */
public class Muistipeli {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame ikkuna = new JFrame();
        ikkuna.setSize(500, 500);
        ikkuna.setTitle("Muistipeli");
        ikkuna.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ikkuna.setVisible(true);
        Kayttoliittyma kayttis = new Kayttoliittyma();
        kayttis.setBackground(Color.BLUE);
        Container sisalto = ikkuna.getContentPane();
        sisalto.add(kayttis);

    }
}
