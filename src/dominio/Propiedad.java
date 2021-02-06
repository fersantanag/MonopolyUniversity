package dominio;
/**
 Casilla dedicada a las propiedades del tablero que se pueden comprar para recibir dinero después por su alquiler
 */
public class Propiedad extends Casilla {

    private int precio;
    private int alquiler;
    private Jugador propietario;

    public Propiedad(int numero, String nombre,int precio, int alquiler){
        super(numero,nombre);
        this.precio=precio;
        this.alquiler=alquiler;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(int alquiler) {
        this.alquiler = alquiler;
    }

    public Jugador getPropietario()
    {
        return propietario;
    }

    public void setPropietario(Jugador propietario)
    {
        this.propietario = propietario;
    }

    public String getInfo()
    {
        return super.getInfo() + ", " + this.getPrecio() + ", " + this.getAlquiler() + ", " + this.getPropietario();
    }
}
