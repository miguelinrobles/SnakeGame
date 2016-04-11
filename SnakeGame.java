import java.util.Random;
import java.util.ArrayList;
import java.awt.Color;
public class SnakeGame
{
    private Canvas lienzo;
    private Snake serpiente;
    private static final int ANCHO_LIENZO = 500;
    private static final int ALTO_LIENZO = 500;
    private Galleta galleta;
    private int puntos;
    private final int SITUACION_X_MARCADOR = 220;
    private final int SITUACION_Y_MARCADOR = 12;

    /*
     * Constructor de la clase Snake
     */
    public SnakeGame()
    {        
        lienzo = new Canvas("Snake game", ANCHO_LIENZO, ALTO_LIENZO);
        lienzo.setBackgroundColor(Color.CYAN);
        galleta = null;
        puntos = 0;
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
     * Coloca el marcador de puntos en la pantalla
     */
    private void situarMarcador()
    {   
        lienzo.drawString("PUNTOS: " + puntos, SITUACION_X_MARCADOR, SITUACION_Y_MARCADOR);
    }

    /**
     * Muestra los puntos cuando se come alguna galleta
     */
    private void puntuar()
    {
        lienzo.eraseString("PUNTOS: " + puntos, SITUACION_X_MARCADOR, SITUACION_Y_MARCADOR);
        puntos += 10;
        lienzo.drawString("PUNTOS: " + puntos, SITUACION_X_MARCADOR, SITUACION_Y_MARCADOR);
    }
    
    /**
     * Muestra mensaje cuando se acaba el juego
     */
    private void finJuego()
    {
        final int SITUACION_X_TITULO = ANCHO_LIENZO / 2 -30;
        final int SITUACION_Y_TITULO = ALTO_LIENZO / 2 -30;
        lienzo.drawString("GAME OVER", SITUACION_X_TITULO, SITUACION_Y_TITULO);
    }

    /**
     * Mueve la serpiente por toda la pantalla. 
     * La animación termina en caso de que la serpiente quede encerrada sobre ella misma o sobre un borde del lienzo. 
     * Cada vez que come una galleta, la serpiente aumenta de longitud
     * En este caso el juego acaba y muestra el mensaje "Game Over"
     */
    private void animateSnake() {
        boolean valorMover = true;
        final int PAUSA = 150;
        while (valorMover) {
            if (serpiente.comerGalleta(galleta)) {
                galleta.borrar(lienzo);
                serpiente.borrar(lienzo);
                valorMover = serpiente.addSegmentMovimiento();
                serpiente.dibujar(lienzo);
                dibujarGalleta();
                lienzo.wait(PAUSA);
                puntuar();
            }           
            serpiente.borrar(lienzo);
            valorMover = serpiente.mover();
            serpiente.dibujar(lienzo);
            lienzo.wait(PAUSA);           
        }
        finJuego();
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
        situarMarcador();
        animateSnake();        
    }
}