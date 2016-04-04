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
    public static final int LONGITUD_SEGMENTO = 100;
    // Direccion en la que se dibuja el segmento
    private int direccion;
    // Color del segmento
    private Color color;

    /**
     * Constructor for objects of class Segmen
     */
    public Segment(int posicionX, int posicionY, int direccion, Color color)
    {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.direccion = direccion;
        this.color = color;
    }

    /**
     * Dibuja el segmento sobre el lienzo pasado como parámetro
     */
    public void dibujar(Canvas lienzo)
    {
        Pen pen = new Pen(posicionX, posicionY, lienzo);
        pen.setColor(color);
        pen.turnTo(direccion);
        pen.move(LONGITUD_SEGMENTO);
    }

    /**
     * Borra el segmeto
     */
    public void borrar(Canvas lienzo)
    {
        Pen pen = new Pen(posicionX, posicionY, lienzo);
        pen.setColor(lienzo.getBackgroundColor());
        pen.turnTo(direccion);
        pen.move(LONGITUD_SEGMENTO);
    }
    
    /**
     * Devuelve posicion inicial X 
     */
    public int getPosicionXInicial()
    {
        return posicionX;
    }
    
    /**
     * Devuelve posicion inicial Y 
     */
    public int getPosicionYInicial()
    {
        return posicionY;
    }
    
    /**
     * Devuelve posicion final X 
     */
    public int getPosicionXFinal()
    {
        int posicionXFinal = posicionX;
        if (direccion == 0) {
            posicionXFinal += LONGITUD_SEGMENTO;
        }
        else if (direccion == 180) {
            posicionXFinal -= LONGITUD_SEGMENTO;
        }
        return posicionXFinal;
    }
    
    /**
     * Devuelve posicion final Y 
     */
    public int getPosicionYFinal()
    {
        int posicionYFinal = posicionY;
        if (direccion == 90) {
            posicionYFinal += LONGITUD_SEGMENTO;
        }
        else if (direccion == 270) {
            posicionYFinal -= LONGITUD_SEGMENTO;
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
}