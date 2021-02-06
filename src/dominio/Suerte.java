package dominio;
/**
 Casilla de Suerte donde se escoge un efecto pseudoaleatorio para aplicar al jugador
 */
public class Suerte extends Casilla
{
    private int casillaDestino;
    private int cambioDinero;
    private String mensajeSuerte;

    public Suerte(int numero, String nombre)
    {
        super(numero, nombre);
    }

    /**
     * Se escoge pseudoaleatoriamente una de siete cartas.
     * Cada carta incluye un mensaje, translada al jugador a otra casilla y le suma o resta dinero
     */
    public void randomSuerte()
    {
        int random = (int)(Math.random() * 7) + 1;;
        switch (random) {
            case 1:
                this.casillaDestino=10;
                this.cambioDinero=0;
                this.mensajeSuerte="Tienes un examen de Java, a la biblioteca a estudiar!";
                break;
            case 2:
                this.casillaDestino=2;
                this.cambioDinero=-50;
                this.mensajeSuerte="Parece que las escaleras etan bloqueadas, deberas subir por el ascensor pero tiene un coste (-50 ECTS)";
                break;
            case 3:
                this.casillaDestino=12;
                this.cambioDinero=100;
                this.mensajeSuerte="Vas a preguntar por tus notas de los intercuatrimestrales, han sido buenas y recibes 100 ECTS";
                break;
            case 4:
                this.casillaDestino=16;
                this.cambioDinero=-60;
                this.mensajeSuerte="Necesitas imprimir las transparencias, menos mal que es barato (-60ECTS)!";
                break;
            case 5:
                this.casillaDestino=8;
                this.cambioDinero=0;
                this.mensajeSuerte="Te apetece hacer deporte, a ver que hay en el gimnasio";
                break;
            case 6:
                this.casillaDestino=19;
                this.cambioDinero=70;
                this.mensajeSuerte="Realizas una gran proyecto de Sistemas Digitales y recibes 70 ECTS";
                break;
            case 7:
                this.casillaDestino=14;
                this.cambioDinero=-30;
                this.mensajeSuerte="Tienes hambre y vas a comprar algo a la Cafetería de ICAI (-30 ECTS)";
                break;
        }
    }

    public int getCasillaDestino()
    {
        return casillaDestino;
    }

    public int getCambioDinero()
    {
        return cambioDinero;
    }

    public String getMensajeSuerte()
    {
        return mensajeSuerte;
    }


}
