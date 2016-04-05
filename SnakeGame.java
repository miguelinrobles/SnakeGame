import java.util.Random;
import java.util.ArrayList;

public class SnakeGame
{
    private Canvas lienzo;
    private Snake serpiente;
    private static final int ANCHO_LIENZO = 500;
    private static final int ALTO_LIENZO = 500;
    private ArrayList<Galleta> galletas;
    
    /*
     * Constructor de la clase Snake
     */
    public SnakeGame()
    {
        lienzo = new Canvas("Snake game", ANCHO_LIENZO, ALTO_LIENZO);
    }

    /*
     * Dibuja una serpiente en la pantalla
     */
    public void drawSnake()
    {
        serpiente = new Snake(ANCHO_LIENZO,ALTO_LIENZO);
        lienzo.erase();
        serpiente.dibujar(lienzo);
    }
    
    /**
     * Dibuja una serie de galletas en la pantalla
     * Cada vez que la serpiente come una galleta se hace un segmento más larga 
     * La serpiente se mueve por pantalla sin parar hasta que se quede sin salida
     */
    public void startGame()
    {
        drawSnake();
        galletas = new ArrayList<>();
        final int NUMERO_GALLETAS = 10; 
        for (int index = 0; index < NUMERO_GALLETAS; index++) {
            galletas.add(new Galleta(ANCHO_LIENZO, ALTO_LIENZO));
            galletas.get(index).dibujar(lienzo);
        }
        serpiente.animateSnake(lienzo);
    }
}