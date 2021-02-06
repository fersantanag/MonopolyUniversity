package dominio;

import dominio.Propiedad;
import java.awt.*;
import java.util.ArrayList;

/**
 Representa a los jugadores que se instancian en un ArrayList en el tablero
 */
public class Jugador
{

    private int numero;
    private String nombre;
    private Image icono;
    private int dinero;
    private ArrayList<Propiedad> propiedades;
    private int ubicacion;

    public static final int DINEROSTART = 1500;

    /**
     Inicializa los atributos numero y nombre del objeto
     @param numero Numero 0 o 1 para identificar al jugador
     @param nombre nombre introducido en el menu
     */
    public Jugador(int numero, String nombre)
    {
        this.numero = numero;
        this.nombre = nombre;
        this.dinero = DINEROSTART;
        this.ubicacion = 0;
        propiedades = new ArrayList<Propiedad>();

        Toolkit t = Toolkit.getDefaultToolkit();
        icono = t.getImage("archivos/jugadores/" + numero + ".png");
    }

    public void addDinero(int dineroAdd)
    {
        dinero = dinero + dineroAdd;
    }

    public boolean sigueJugando()
    {
        if (dinero > 0)
            return true;
        else
            return false;
    }
    /**
     Desplazaa al jugador por el tablero teniendo en cuenta que no se salga de los límites del Array
     @param numMover numero de casillas a moverse
     */
    public void mover(int numMover)
    {
        ubicacion = ubicacion + numMover;
        if (ubicacion >= 20)
        {
            ubicacion = ubicacion - 20;
            addDinero(200);
        }
    }

    public int getNumero()
    {
        return numero;
    }

    public void setNumero(int numero)
    {
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

    public Image getIcono()
    {
        return icono;
    }

    public void setIcono(Image icono)
    {
        this.icono = icono;
    }

    public int getDinero()
    {
        return dinero;
    }

    public void setDinero(int dinero)
    {
        this.dinero = dinero;
    }

    public ArrayList<Propiedad> getPropiedades()
    {
        return propiedades;
    }

    public void setPropiedades(ArrayList<Propiedad> propiedades)
    {
        this.propiedades = propiedades;
    }

    public void addPropiedad(Propiedad propiedad)
    {
        this.propiedades.add(propiedad);
    }

    public int getUbicacion()
    {
        return ubicacion;
    }

    public void setUbicacion(int ubicacion)
    {
        this.ubicacion = ubicacion;
    }
}