package dominio;

import javax.swing.*;
/**
 Clase padre de las clases Propiedad, Go, Biblioteca, Suerte
 */
public class Casilla extends JPanel {

    private int numero;
    private String nombre;
    private String info;

    public Casilla(int numero, String nombre) {
        this.numero=numero;
        this.nombre=nombre;
   }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    public String getInfo()
    {
        return this.getNumero() + ", " + this.getNombre();
    }
}
