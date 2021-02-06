package io;

import dominio.*;

import java.io.*;
/**
 Importa las casillas de un archivo csv
 */
public class IOTablero
{

    /**
     *      Introduce en un array todas las casillsa con sus atributos de un archivo csv
     *      @return Array de casillas
     * @return
     */
    public static Casilla[] importarcsv()
    {
        Casilla[] casillas = new Casilla[20];
        try
        {
            FileReader fr = new FileReader("archivos/casillas.csv");
            BufferedReader br = new BufferedReader(fr);

            String linea = null;
            while ((linea = br.readLine()) != null)
            {

                String s[] = linea.split(";");

                int numero = Integer.parseInt(s[0].trim());
                String nombre = s[1];
                String tipo = s[2];

                if (tipo.equals("Propiedad"))
                {
                    int precio = Integer.parseInt(s[3].trim());
                    int alquiler = Integer.parseInt(s[4].trim());
                    casillas[numero] = new Propiedad(numero, nombre, precio, alquiler);
                } else if (tipo.equals("Suerte"))
                    casillas[numero] = new Suerte(numero, nombre);
                else if (tipo.equals("Go"))
                    casillas[numero] = new Go(numero, nombre);
                else if (tipo.equals("Biblioteca"))
                    casillas[numero] = new Biblioteca(numero, nombre);

            }
        } catch (FileNotFoundException fnfe)
        {
            // fnfe.printStackTrace();
            System.out.println("No hay ningún archivo");
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        return casillas;
    }
}
