package dominio;

import javax.swing.*;
import java.awt.*;
/**
 Dado que genera un entero entre el 1 y el 6 para desplazarse por el tablero
 */
public abstract class Dado {
    /**
     @return entero pseudoaleatorio entre el 1 y el 6
     */
    public static int tirada(){
       return (int)(Math.random() * 6) + 1;
    }
}