import java.awt.Color;

/**
 * Write a description of class Segmen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Segment
{
    // Punto inicial X
    private int posicionX;
    // Punto inicial Y
    private int posicionY;
    // Longitud del segmento
    public static final int LONGITUD_SEGMENTO = 9;
    // Direccion en la que se dibuja el segmento
    private int direccion;

    /**
     * Constructor for objects of class Segmen
     */
    public Segment(int posicionX, int posicionY, int direccion)
    {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.direccion = direccion;
    }

    /**
     * Dibuja el segmento sobre el lienzo pasado como parámetro
     */
    public void dibujar(Canvas lienzo)
    {
        lienzo.fillRectangle(posicionX, posicionY, LONGITUD_SEGMENTO, LONGITUD_SEGMENTO);
    }

    /**
     * Borra el segmeto
     */
    public void borrar(Canvas lienzo)
    {
       lienzo.eraseRectangle(posicionX, posicionY, LONGITUD_SEGMENTO, LONGITUD_SEGMENTO);
    }

    /**
     * Devuelve posicion inicial X 
     */
    public int getPosicionInicialX()
    {
        return posicionX;
    }

    /**
     * Devuelve posicion inicial Y 
     */
    public int getPosicionInicialY()
    {
        return posicionY;
    }

    /**
     * Devuelve posicion final X 
     */
    public int getPosicionFinalX()
    {
        int posicionXFinal = posicionX;
        if (direccion == 0) {
            posicionXFinal += LONGITUD_SEGMENTO + 1;
        }
        else if (direccion == 180) {
            posicionXFinal -= LONGITUD_SEGMENTO + 1;
        }
        return posicionXFinal;
    }

    /**
     * Devuelve posicion final Y 
     */
    public int getPosicionFinalY()
    {
        int posicionYFinal = posicionY;
        if (direccion == 90) {
            posicionYFinal += LONGITUD_SEGMENTO + 1;
        }
        else if (direccion == 270) {
            posicionYFinal -= LONGITUD_SEGMENTO + 1;
        }
        return posicionYFinal;
    }

    /**
     * Devuelve la direccion del segmento en grados
     */
    public int getDireccion()
    {
        return direccion;
    }

    /**
     * Comprueba si el segmento colisiona con el segmento pasado como parámetro
     */
    public boolean colisiona(Segment segmento)
    {
        return ((segmento.getPosicionFinalX() == posicionX) && (segmento.getPosicionFinalY() == posicionY));
    }
}