import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

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
    // Diferencia de grados de una direccion a otra por ser en angulos rectos
    private static final int DIFERENCIA_DE_GRADOS_ENTRE_DIRECCIONES = 90;
    // margenes que dejamos en el lienzo
    private static final int MARGEN_LIENZO = 10;

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

    /**
     * Comprueba si el segmento colisiona con otros segmentos
     */
    private boolean colisionaConOtrosSegmentos(Segment segmento)
    {
        boolean colisiona = false;
        int index = 0;
        int cantidadSegmentos = segmentos.size();
        while (index < cantidadSegmentos && !colisiona) {
            colisiona = segmentos.get(index).colisionaCon(segmento); 
            index++;
        }
        return colisiona;
    }
}
