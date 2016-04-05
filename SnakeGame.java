import java.util.Random;
import java.util.ArrayList;

public class SnakeGame
{
    private Canvas lienzo;
    private Snake serpiente;
    private static final int ANCHO_LIENZO = 500;
    private static final int ALTO_LIENZO = 500;
    
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
}