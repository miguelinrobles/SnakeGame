import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

/**
 * Write a description of class Galleta here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Galleta
{
    // instance variables - replace the example below with your own
    private int posicionX;
    private int posicionY;
    private int anchoLienzo;
    private int altoLienzo;
    public static final int DIAMETRO_GALLETA = 10;
    public static final Color COLOR_GALLETA = Color.RED;

    /**
     * Constructor for objects of class Galleta
     */
    public Galleta(int altoLienzo, int anchoLienzo, ArrayList<Segment> segments)
    {
        this.altoLienzo = altoLienzo;
        this.anchoLienzo = anchoLienzo;        
        poscionesValidas(segments);
    }

    /**
     * Dibuja la galleta sobre el lienzo que le pasamos
     */
    public void dibujar(Canvas lienzo)
    {
        lienzo.setForegroundColor(COLOR_GALLETA);
        lienzo.fillCircle(posicionX, posicionY, DIAMETRO_GALLETA);
    }

    /**
     * Borra la galleta del lienzo
     */
    public void borrar(Canvas lienzo)
    {
        lienzo.eraseCircle(posicionX, posicionY, DIAMETRO_GALLETA);
    }

    /**
     * Devuelve el punto mas a la izquierda de la galleta en el eje X
     */
    public int getPosicionX()
    {
        return posicionX;
    }

    /**
     * Devuelve el punto mas alto de la galleta en el eje Y
     */
    public int getPosicionY()
    {
        return posicionY;
    }

    /**
     * Genera puntos aleatorios para las galletas
     * simpre que puedan ser comidas por la serpiente
     */
    private void generaPuntosGalleta()
    {
        Random aleatorio = new Random();
        int margenLienzo = Snake.MARGEN_LIENZO;
        posicionX = aleatorio.nextInt((anchoLienzo - (2 * margenLienzo))  / (Segment.LONGITUD_SEGMENTO + 1)) 
        * (Segment.LONGITUD_SEGMENTO + 1) + margenLienzo;
        posicionY = aleatorio.nextInt((anchoLienzo - (2 * margenLienzo))  /(Segment.LONGITUD_SEGMENTO + 1))
        * (Segment.LONGITUD_SEGMENTO + 1) + margenLienzo;
    }

    /**
     * Comprueba que la nueva galleta no sea creada donde se
     * encuentra la serpiente
     */
    private void poscionesValidas(ArrayList<Segment> segments)
    {  
        boolean puntoValido = true;
        do {
            generaPuntosGalleta();
            for (Segment segment : segments) {
                if (segment.getPosicionFinalX() == posicionX && segment.getPosicionFinalY() == posicionY) {
                    puntoValido = false;
                }
            }            
        }while (!puntoValido);
    }
}
