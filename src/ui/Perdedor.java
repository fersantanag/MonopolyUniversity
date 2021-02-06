package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 Ventana que se lanza cuando un jugador pierde
 */
public class Perdedor extends JFrame
{
    private JButton cerrar;

    public Perdedor(int n)
    {
        super("Menu");

        setLayout(new FlowLayout());


        JLabel imagen = new JLabel(new ImageIcon("archivos/jugadores/"+n+"loses.png"));

        cerrar=new JButton("Cerrar Juego");

        this.add(imagen);
        this.add(cerrar);

        this.setSize(600, 500);

        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cerrar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

    }




}
