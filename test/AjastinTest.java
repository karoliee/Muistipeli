/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;
import Kayttoliittyma.*;

/**
 *
 * @author karoliee
 */
public class AjastinTest {

    Ajastin ajastin;
    Kayttoliittyma kayttis;

    @Test
    public void konstruktoriLuoAjastimen() {
        ajastin = new Ajastin(kayttis, 5);
    }

    @Test
    public void konstruktoriLuoMyosTyhjanAjastimen() {
        ajastin = new Ajastin(kayttis, 0);
    }
}
