package ui;

import util.PDFManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 Menu principal que contiene instrucciones y boton para iniciar
 Las instrucciones se leen de un PDF utilizando una libreria y una clase externa
 */
public class Menu extends JFrame {

    private JButton start;
    private JTextArea instrucciones;
    private static JTextField jug0;
    private static JTextField jug1;

    /**
     * Main desde donde se lanza la aplicación
     */
    public static void main(String[] args)
    {
        new Menu();
    }

    public Menu()
    {
        super("Menu");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new GridLayout(1,2));


        JPanel pIzq = new JPanel(new GridLayout(5,1));

        start= new JButton("INICIAR JUEGO");
        jug0= new JTextField("Teleco");
        jug1= new JTextField("Industriales");

        pIzq.add(start);
        pIzq.add(new JLabel("Introduce el nombre del primer jugador"));
        pIzq.add(jug0);
        pIzq.add(new JLabel("Introduce el nombre del segundo jugador"));
        pIzq.add(jug1);


        instrucciones = new JTextArea();
        instrucciones.setLineWrap(true);
        instrucciones.setWrapStyleWord(true);
        JScrollPane pDer= new JScrollPane(instrucciones);
        instrucciones.setEditable(false);


        PDFManager pdfManager = new PDFManager();
        pdfManager.setFilePath("archivos/instrucciones.pdf");
        try {
            String text = pdfManager.toText();
            instrucciones.setText(text);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        this.add(pIzq);
        this.add(pDer);

        this.setSize(700,400);

        this.setResizable(false);
        //this.pack();
        this.setVisible(true);

        start.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new Juego();
                dispose();
            }
        });

    }

    public static String getNombre(int n)
    {
        if (n==0)
            return jug0.getText();
        else
            return jug1.getText();
    }
}
