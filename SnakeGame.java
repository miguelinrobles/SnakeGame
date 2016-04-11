import java.util.Random;
import java.util.ArrayList;

public class SnakeGame
{
    private Canvas lienzo;
    private Snake serpiente;
    private static final int ANCHO_LIENZO = 500;
    private static final int ALTO_LIENZO = 500;
    private Galleta galleta;

    /*
     * Constructor de la clase Snake
     */
    public SnakeGame()
    {        
        lienzo = new Canvas("Snake game", ANCHO_LIENZO, ALTO_LIENZO);
        galleta = null;
        serpiente = new Snake(ANCHO_LIENZO,ALTO_LIENZO, "w", "x", "a", "d");
        lienzo.addKeyboard(serpiente);
    }

    /*
     * Dibuja una serpiente en la pantalla
     */
    private void drawSnake()
    {
        lienzo.erase();        
        serpiente.dibujar(lienzo);
    }

    /**
     * Mueve la serpiente por toda la pantalla. 
     * La animación termina en caso de que la serpiente quede encerrada sobre ella misma o sobre un borde del lienzo. 
     * Cada vez que come una galleta, la serpiente aumenta de longitud
     * En este caso el juego acaba y muestra el mensaje "Game Over"
     */
    private void animateSnake() {
        boolean valorMover = true;
        while (valorMover) {
            if (serpiente.comerGalleta(galleta)) {
                galleta.borrar(lienzo);
                serpiente.borrar(lienzo);
                valorMover = serpiente.addSegmentMovimiento();
                serpiente.dibujar(lienzo);
                dibujarGalleta();
                lienzo.wait(100);
            }           
            serpiente.borrar(lienzo);
            valorMover = serpiente.mover();
            serpiente.dibujar(lienzo);
            lienzo.wait(200);           
        }
        final int SITUACION_X_TITULO = ANCHO_LIENZO / 2 -30;
        final int SITUACION_Y_TITULO = ALTO_LIENZO / 2 -30;
        lienzo.drawString("Game Over", SITUACION_X_TITULO, SITUACION_Y_TITULO);
    }

    /**
     * Pone una galleta en la pantalla sin que coincida
     * con la posición de la serpiente
     */
    private void dibujarGalleta() 
    {
        galleta = new Galleta(ANCHO_LIENZO, ALTO_LIENZO, serpiente.getSegmentos());
        galleta.dibujar(lienzo);
    }

    /**
     * Inicia el juego dibujando la serpiente, la galleta y comenzando el movimiento de la serpiente
     */
    public void startGame()
    {
        drawSnake();
        dibujarGalleta();
        animateSnake();
    }
}