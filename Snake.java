import java.awt.Color;
import java.util.ArrayList;

/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake
{
    // Ancho del lienzo
    private int anchoLienzo;
    // Alto del lienzo
    private int altoLienzo;
    // Color de la serpiente
    public static final Color COLOR_SERPIENTE = Color.BLACK;
    // Número de segmentos iniciales
    public static final int MUMERO_SEGMENTOS_INICIALES = 3;
    // Almacena todos los segmentos que forman la serpiente
    private ArrayList<Segment> segmentos;

    /**
     * Constructor for objects of class Snake
     */
    public Snake(int altoLienzo, int anchoLienzo)
    {
        // initialise instance variables
        this.altoLienzo = altoLienzo;
        this.anchoLienzo = anchoLienzo;
        segmentos = new ArrayList<>();
    }

   
}
