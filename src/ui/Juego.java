package ui;

import dominio.*;
import util.PDFManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 Ventana principal donde se desarrolla la partida
 */
public class Juego extends JFrame
{

    private Tablero tablero;
    private JButton bFin;
    private JButton bTirar;
    private JButton bComprar;
    private JButton bInstrucciones;
    private JTextArea mensaje;
    private JLabel imagenJug;
    private JLabel imagenDado;
    private JLabel dinero0;
    private JLabel dinero1;
    private DefaultTableModel modelo0;
    private DefaultTableModel modelo1;
    private JTable tabla0;
    private JTable tabla1;

    public Juego()
    {
        super("ICAIPOLY");
        setIconImage(Toolkit.getDefaultToolkit().getImage("archivos/logor.png"));

        this.setLayout(new BorderLayout());

        tablero = new Tablero(this);

        JPanel pnlNorte = new JPanel(new FlowLayout());
        JPanel pnlSur = new JPanel(new GridLayout(1, 3));
        JLabel lblMensaje1 = new JLabel("Proyecto final: ICAIPOLY");
        JLabel lblMensaje2 = new JLabel("Programacion Orientada a Objetos (ICAI)");
        JLabel lblMensaje3 = new JLabel("por Fernando Santana");
        pnlSur.add(lblMensaje1);
        pnlSur.add(lblMensaje2);
        pnlSur.add(lblMensaje3);

        JPanel pnlEste = new JPanel(new GridLayout(4, 1));


        JPanel pnl1 = new JPanel();
        pnl1.setLayout(new FlowLayout());
        imagenJug = new JLabel(new ImageIcon("archivos/jugadores/0.png"));
        imagenDado = new JLabel(new ImageIcon("archivos/dado/logo.png"));
        pnl1.add(imagenJug);
        pnl1.add(imagenDado);


        JPanel pnl2 = new JPanel(new GridLayout(3, 2));
        bTirar = new JButton("Tirar Dados");
        bComprar = new JButton("Comprar");
        bFin = new JButton("Finalizar turno");
        bInstrucciones = new JButton("Instrucciones");

        dinero0 = new JLabel();
        dinero1 = new JLabel();

        pnl2.add(bTirar);
        pnl2.add(bComprar);
        pnl2.add(bFin);
        pnl2.add(bInstrucciones);
        pnl2.add(dinero0);
        pnl2.add(dinero1);


        mensaje = new JTextArea();
        mensaje.setLineWrap(true);
        mensaje.setWrapStyleWord(true);
        mensaje.setEditable(false);
        JScrollPane pnl3 = new JScrollPane(mensaje);


        JPanel pnl4 = new JPanel(new GridLayout(1,2));
        Dimension dim =new Dimension();
        dim.setSize(400,200);
        pnl4.setPreferredSize(dim);

        tabla0 = new JTable();
        tabla1 = new JTable();
        String[] columnas = {"Nombre", "Precio", "Visita"};
        modelo0 = new DefaultTableModel();
        modelo0.setColumnIdentifiers(columnas);
        tabla0.setModel(modelo0);
        modelo1 = new DefaultTableModel();
        modelo1.setColumnIdentifiers(columnas);
        tabla1.setModel(modelo1);
        tabla0.setDefaultEditor(Object.class, null);
        tabla1.setDefaultEditor(Object.class, null);

        JScrollPane pTabla0 = new JScrollPane(tabla0);
        pTabla0.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Propiedades de "+  Menu.getNombre(0),
                TitledBorder.CENTER,
                TitledBorder.TOP));

        JScrollPane pTabla1 = new JScrollPane(tabla1);
        pTabla1.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Propiedades de "+ Menu.getNombre(1),
                TitledBorder.CENTER,
                TitledBorder.TOP));


        pnl4.add(pTabla0);
        pnl4.add(pTabla1);

        tabla0.getColumn("Precio").setMaxWidth(45);
        tabla0.getColumn("Visita").setMaxWidth(40);
        tabla1.getColumn("Precio").setMaxWidth(45);
        tabla1.getColumn("Visita").setMaxWidth(40);


        pnlEste.add(pnl1);
        pnlEste.add(pnl2);
        pnlEste.add(pnl3);
        pnlEste.add(pnl4);

        this.add(pnlNorte, BorderLayout.NORTH);
        this.add(tablero, BorderLayout.CENTER);
        this.add(pnlSur, BorderLayout.SOUTH);
        this.add(pnlEste, BorderLayout.EAST);

        pack();

        bComprar.setEnabled(false);
        bFin.setEnabled(false);
        actualizar();

        //botones
        bTirar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tirar();
                actualizar();
            }
        });
        bComprar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                comprar();
                actualizar();
            }
        });

        bFin.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                finalizarTurno();
                actualizar();
            }
        });
        bInstrucciones.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                PDFManager pdfManager = new PDFManager();
                pdfManager.setFilePath("archivos/instrucciones.pdf");
                try {
                    String text = pdfManager.toText();
                    println(text);
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
                println("---------------------------------");
                actualizar();
            }
        });

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(1030, 665);
        this.setResizable(true);
    }

    /**
     * Imprime un texto en el JTextArea del panel derecho
     * @param line texto a imprimir
     */
    public void println(String line)
    {
        mensaje.setText(mensaje.getText() + "\n" + line);
    }

    /**
     * Actualiza el dinero de cada jugador y ejecuta un repaint()
     */
    private void actualizar()
    {
        repaint();
        dinero0.setText(tablero.getJugador(0).getNombre()+": " +tablero.getJugador(0).getDinero()+" ECTS");
        dinero1.setText(tablero.getJugador(1).getNombre()+": " +tablero.getJugador(1).getDinero()+" ECTS");
    }
    /**
     *     Realiza el desplazamiento del jugador acorde al resultado obtenido en el dado
     *      y decide las acciones correspondientes que puede realizar el jugador dependiendo de donde cae
     *      Utiliza el método de tablero getJugando() para obtener el jugador que esta realizando el turno
     *      y el método de jugador getUbicacion() para obtener la casilla.
     *      -Si cae en la biblioteca y el dado<5 no avanza
     *      -Si cae en una casilla de suerte se le aplica una carta de suerte
     *      -Si cae en una propiedad se comprueba si tiene propietario y si no lo tiene se activa el boton Comprar
     *      -Si el propietario es el otro jugador, automaticamente se le abona el alquiler
     *      Finalmente se comprueba que el jugador tiene dinero>0 y si no es así se abre la ventana Perdedor
     */
    private void tirar()
    {
        final Jugador jugando = tablero.getJugando();
        int valorDados = Dado.tirada();
        //valorDados = 5; //por si quiero probar una casilla en concreto
        imagenDado.setIcon(new ImageIcon("archivos/dado/"+valorDados+".png"));

        bFin.setEnabled(true);
        bTirar.setEnabled(false);

        if (jugando.getUbicacion()==10 && valorDados < 5)
        {
            println("Necesitas un por lo menos un 5 para salir de la biblioteca");
        } else
        {
            jugando.mover(valorDados);
            println("Avanzas " + valorDados+ " casillas");

            Casilla casillaActual = tablero.getCasilla(jugando.getUbicacion());
            if (casillaActual instanceof Suerte)
            {
                println("Suerte!");
                JOptionPane.showMessageDialog(Juego.this,"Has caido en una casilla de Suerte");
                bComprar.setEnabled(false);

                Suerte suerte= new Suerte(jugando.getUbicacion(),"Suerte");
                suerte.randomSuerte();

                JOptionPane.showMessageDialog(Juego.this,suerte.getMensajeSuerte());

                jugando.setUbicacion(suerte.getCasillaDestino());
                jugando.addDinero(suerte.getCambioDinero());
                println("Has sido desplazdo a " + tablero.getCasilla(jugando.getUbicacion()).getNombre());
                if (suerte.getCambioDinero()>0)
                    println("Ganas " +suerte.getCambioDinero() + " ECTS");
                else if (suerte.getCambioDinero()<0)
                    println("Pierdes " +suerte.getCambioDinero() + " ECTS");

                casillaActual = tablero.getCasilla(jugando.getUbicacion());
            }
            if (casillaActual instanceof Propiedad)
            {

                final Propiedad propiedad = (Propiedad) casillaActual;

                if (tablero.jugandoPuedeComprar(casillaActual))
                {
                    bComprar.setEnabled(true);
                    println("Puedes comprar " + propiedad.getNombre() + " por " + propiedad.getPrecio() + " ECTS.");
                } else if(propiedad.getPropietario()==jugando)
                {
                    println("Ya eres el propietario de este lugar");
                }else if (propiedad.getPropietario() != jugando && propiedad.getPropietario() != null)
                {
                    Jugador jugPropietario = propiedad.getPropietario();

                    //si posee las dos del mismo color se paga más
                    Propiedad propiedadColindante=null;
                    if (jugando.getUbicacion()==1 || jugando.getUbicacion()==3 || jugando.getUbicacion()==6 || jugando.getUbicacion()==8 || jugando.getUbicacion()==11 || jugando.getUbicacion()==13 || jugando.getUbicacion()==16 || jugando.getUbicacion()==18)
                    {
                        propiedadColindante= (Propiedad)tablero.getCasilla(jugando.getUbicacion()+1);
                    }
                    else if (jugando.getUbicacion()==2 || jugando.getUbicacion()==4 || jugando.getUbicacion()==7 || jugando.getUbicacion()==9 || jugando.getUbicacion()==12 || jugando.getUbicacion()==14 || jugando.getUbicacion()==17 || jugando.getUbicacion()==19)
                    {
                        propiedadColindante= (Propiedad)tablero.getCasilla(jugando.getUbicacion()-1);
                    }
                    if (jugPropietario==propiedadColindante.getPropietario())
                    {
                        println("Has caido en propiedad de " + jugPropietario.getNombre() + ". Como tiene ambas propiedades del mismo color la cantidad a abonar es mayor. Son " + (int)(propiedad.getAlquiler()*1.5) + " ECTS!");
                        jugPropietario.addDinero((int)(propiedad.getAlquiler()*1.5));
                        jugando.addDinero(-(int)(propiedad.getAlquiler()*1.5));
                        println(jugPropietario.getNombre() + " recibe " + (int)(propiedad.getAlquiler()*1.5) + " de " + jugando.getNombre() + ".");
                    }
                    else
                    {
                        println("Has caido en propiedad de " + jugPropietario.getNombre() + ". Debes abonarle " + propiedad.getAlquiler() + " ECTS!");
                        jugPropietario.addDinero(propiedad.getAlquiler());
                        jugando.addDinero(-propiedad.getAlquiler());
                        println(jugPropietario.getNombre() + " recibe " + propiedad.getAlquiler() + " de " + jugando.getNombre() + ".");
                    }
                }
            } else if (casillaActual.getNumero() == 10)
            {
                bComprar.setEnabled(false);
                JOptionPane.showMessageDialog(Juego.this, "Has caido en la biblioteca, antes de salir debes estudiar lo suficiente como para aprobar el siguiente examen.");
                println("Al caer en la biblioteca debes sacar por lo menos un 5 en tus  siguientes tiradas para poder salir.");
            } else if (casillaActual.getNumero() == 0)
            {
                bComprar.setEnabled(false);
                println("Casilla de salida");
            }

            if (jugando.getDinero()<=0)
            {
                bComprar.setEnabled(false);
                bFin.setEnabled(false);
                bTirar.setEnabled(false);
                bInstrucciones.setEnabled(false);
                new Perdedor(jugando.getNumero());
            }
        }
    }

    /**
     *      Realiza las compras de propiedades por parte de los jugadores restando el precio
     *      de compra al dinero del jugador que la realiza y añadiendo esta propiedad a la tabla
     *      de propiedades correspondiente.
     */
    private void comprar()
    {
        final Jugador jugando = tablero.getJugando();
        final Casilla casillaActual = tablero.getCasilla(jugando.getUbicacion());
        bComprar.setEnabled(false);
        if (casillaActual instanceof Propiedad) {

            println("Has comprado " + ((Propiedad) casillaActual).getNombre());

            jugando.addDinero(-((Propiedad) casillaActual).getPrecio());
            ((Propiedad) casillaActual).setPropietario(jugando);
            jugando.addPropiedad(((Propiedad) casillaActual));
            //updatePlayerInfo();
            Object[] fila = new Object[3];
            fila[0] = ((Propiedad)casillaActual).getNombre();
            fila[1]= ((Propiedad)casillaActual).getPrecio();
            fila[2] = ((Propiedad)casillaActual).getAlquiler();

            if (jugando.getNumero()==0)
                modelo0.addRow(fila);
            else if (jugando.getNumero()==1)
                modelo1.addRow(fila);
        } else {
            throw new IllegalArgumentException("No hay propiedad");
        }
    }

    /**
     *      Pasa el turno al siguiente jugador utilizando el metodo de tablero siguienteJugador()
     *      y activa de nuevo los botones correspondientes
     */
    private void finalizarTurno()
    {
        tablero.siguienteJugador();
        final Jugador jugando = tablero.getJugando();

        final int jugandoNum=tablero.getJugandoNum();
        imagenDado.setIcon(new ImageIcon("archivos/dado/logo.png"));
        imagenJug.setIcon(new ImageIcon("archivos/jugadores/"+jugandoNum+".png"));
        repaint();

        println("---------------------------------");
        println("Turno de "+jugando.getNombre());
        println("---------------------------------");

        bComprar.setEnabled(false);
        bTirar.setEnabled(true);
        bFin.setEnabled(false);
    }

    public Juego getJuego()
    {
        return this;
    }

}
