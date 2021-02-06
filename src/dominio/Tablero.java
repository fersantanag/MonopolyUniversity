package dominio;

import io.IOTablero;
import ui.Juego;
import ui.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 Clase gestora de jugadores y casillas
 */
public class Tablero extends JPanel
{

    private Casilla[] casillas;
    private Juego juego;

    private int jugando;
    private final ArrayList<Jugador> jugadores;

    //coordenadas para pintar fichas jugador
    int[] xJug = {465, 392, 317, 242, 167, 55, 100, 100, 100, 100, 56, 167, 242, 317, 392, 505, 460, 460, 460, 460};
    int[] yJug = {535, 555, 555, 555, 555, 506, 407, 331, 256, 180, 60, 100, 100, 100, 100, 56, 180, 256, 331, 407};
    //coordenadas para pintar propietario
    int[] xProp = {000, 392, 317, 242, 167, 000, 152, 152, 152, 152, 000, 167, 242, 317, 392, 000, 428, 428, 428, 428};
    int[] yProp = {000, 428, 428, 428, 428, 000, 408, 332, 255, 179, 000, 152, 152, 152, 152, 000, 179, 255, 332, 408};

    public Tablero(Juego juego)
    {
        this.casillas = IOTablero.importarcsv();
        this.jugadores = new ArrayList<Jugador>();
        jugadores.add(new Jugador(0, Menu.getNombre(0)));
        jugadores.add(new Jugador(1, Menu.getNombre(1)));
        jugando = 0;
    }

    /**
     *      Pinta la imagen del tablero
     *      Además pinta en el tablero las fichas de los dos jugadores
     *      y su icono al lado de las propiedades que  poseen
     */
    public void paint(Graphics g)
    {
        Toolkit t = Toolkit.getDefaultToolkit();
        Image fondo = t.getImage("archivos/tablero.png");
        g.drawImage(fondo, 0, 0, 600, 600, this);


        //pinta cada jugador
        for (Jugador j : jugadores)
        {
            g.drawImage(j.getIcono(), xJug[j.getUbicacion()], yJug[j.getUbicacion()], 40, 40, this);
        }

        //pinta propiedades con propietario
        for (int i = 0; i < 19; i++)
        {
            if (casillas[i] instanceof Propiedad)
            {
                Propiedad p = (Propiedad) casillas[i];
                if (p.getPropietario() != null)
                    g.drawImage(p.getPropietario().getIcono(), xProp[i], yProp[i], 20, 20, this);
            }

        }
    }
    public int getJugandoNum()
    {
        return jugando;
    }
    public Jugador getJugando()
    {
        return this.jugadores.get(jugando);
    }
    public Jugador getJugador(int n)
    {
        return this.jugadores.get(n);
    }
    public Casilla getCasilla(int ubicacion)
    {
        return casillas[ubicacion];
    }

    /**
     Cambia de jugador
     */
    public void siguienteJugador()
    {
        if (jugando == 0)
        {
            jugando = 1;
        } else if (jugando == 1)
            jugando = 0;
    }

    public boolean jugandoPuedeComprar(Casilla casilla)
    {
        if (casilla instanceof Propiedad)
        {
            final Propiedad propiedad = (Propiedad) casilla;
            if(getJugando().getDinero() >= propiedad.getPrecio() && propiedad.getPropietario() == null)
            return true;

            else
                return false;

        } else
        {
            return false;
        }
    }
}
