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
    private int anchoLienzo;
    private int altoLienzo;
    public static final int NUMERO_SEGMENTOS_INICIALES = 5;
    public static final Color COLOR_SERPIENTE = Color.BLACK;
    private ArrayList<Segment> segmentos;   
    public static final int DIFERENCIA_DE_GRADOS_ENTRE_DIRECCIONES = 90;
    public static final int MARGEN_LIENZO = 20;
    public static final int TAMANO_CABEZA = 8;
    // Atributos de las teclas para el movimiento
    private String up, down, lef, rig;
    // Direccion inicial hacia abajo
    private int direccion = 90;

    /*
     * Constructor de la clase Snake
     */
    public Snake(int anchoLienzo, int altoLienzo, String up, String down, String lef, String rig)
    {
        this.anchoLienzo = anchoLienzo;
        this.altoLienzo = altoLienzo;
        segmentos = new ArrayList<>();
        // Teclas para el movimiento
        this.rig = rig; this.lef = lef; this.up = up; this.down = down;
        addPrimerSegment();
        for (int i = 1; i < NUMERO_SEGMENTOS_INICIALES; i++) {
            addSegmentMovimiento();
        }
    }

    /*
     * Dibuja la serpiente en el lienzo dado
     */
    public void dibujar(Canvas lienzo)
    {
        for (Segment segmento : segmentos) {
            segmento.dibujar(lienzo);
        }
        Segment ultimoSegmento = segmentos.get(segmentos.size()-1);
        lienzo.fillCircle(ultimoSegmento.getPosicionFinalX()-(TAMANO_CABEZA/2),ultimoSegmento.getPosicionFinalY()-(TAMANO_CABEZA/2), TAMANO_CABEZA);
    }

    /*
     * Borra la serpiente del lienzo dado
     */
    public void borrar(Canvas lienzo)
    {
        for (Segment segmento : segmentos) {
            segmento.borrar(lienzo);
        }
        Segment ultimoSegmento = segmentos.get(segmentos.size()-1);
        lienzo.eraseCircle(ultimoSegmento.getPosicionFinalX()-(TAMANO_CABEZA/2), ultimoSegmento.getPosicionFinalY()-(TAMANO_CABEZA/2), TAMANO_CABEZA);
    }

    /*
     * Añade el primer segmento de la serpiente en la posicion indicada
     */
    public void addPrimerSegment() 
    {
        int xPosition = 200;
        int yPosition = 100;
        segmentos.add(new Segment(xPosition, yPosition, direccion, COLOR_SERPIENTE));
    }

    /*
     * Indica si un segmento es valido, es decir, si se puede adicionar
     * a la serpiente sin que colisione con otros segmentos existentes de la serpiente
     * o con los bordes del lienzo
     */
    private boolean esSegmentoValido(Segment segmento)
    {
        return (!colisionaConOtrosSegmentos(segmento) && !colisionaConBordes(segmento));        
    }

    /*
     * Indica si el segmento dado colisiona con los bordes del lienzo
     */
    public boolean colisionaConBordes(Segment segmento)
    {
        boolean colisiona = false;
        if ((segmento.getPosicionFinalX() > anchoLienzo - MARGEN_LIENZO) ||
        (segmento.getPosicionFinalY() > altoLienzo - MARGEN_LIENZO) ||
        (segmento.getPosicionFinalX() < MARGEN_LIENZO) ||
        (segmento.getPosicionFinalY() < MARGEN_LIENZO)) {
            colisiona = true;
        }
        return colisiona;
    }

    /*
     * Indica si el segmento colisiona con otros segmentos de la serpiente 
     */
    public boolean colisionaConOtrosSegmentos(Segment segmento)
    {
        boolean colisiona = false;
        for (Segment segmentoSerpiente : segmentos) {
            if (segmentoSerpiente.colisiona(segmento)) {
                colisiona = true;
            }
        }
        return colisiona;
    }

    /**
     * Simula el movimiento de la serpiente. 
     */
    public boolean mover()
    {
        segmentos.remove(0);
        return addSegmentMovimiento();
    }

    /**
     * Devuelve la posicion final X del segmento en el que se encuentra la cabeza
     */
    public int getPosicionXFinalUltimo()
    {
        return segmentos.get(segmentos.size() - 1).getPosicionFinalX();
    }

    /**
     * Devuelve la posicion final Y del segmento en el que se encuentra la cabeza
     */
    public int getPosicionYFinalUltimo()
    {
        return segmentos.get(segmentos.size() - 1).getPosicionFinalY();
    }

    /**
     * Coprueba si la galleta que le pasamos como parámetro
     * puede ser comida por la serpiente
     */
    public boolean comerGalleta(Galleta galleta)
    {
        return (galleta.getPosicionY() == getPosicionYFinalUltimo() && galleta.getPosicionX() == getPosicionXFinalUltimo());
    }

    /**
     * Cambia la direccion de la serpiente según la tecla que se pulse
     */
    public void moverSnakeTeclado(String tecla)
    {
        if (tecla.equalsIgnoreCase(up)) {
            direccion = 270;
        }
        else if (tecla.equalsIgnoreCase(down)) {
            direccion = 90;
        }
        else if (tecla.equalsIgnoreCase(lef)) {
            direccion = 180;
        }
        else if (tecla.equalsIgnoreCase(rig)) {
            direccion = 0;
        }        
    }

    /**
     * Añade los segmentos inicales de la serpiente menos el primero
     * y los que se aumentan cuando come una galleta
     */
    public boolean addSegmentMovimiento() {
        int posicionOrigenX = segmentos.get(segmentos.size() - 1).getPosicionFinalX();
        int posicionOrigenY = segmentos.get(segmentos.size() - 1).getPosicionFinalY();
        Segment posibleNuevoSegmento = new Segment(posicionOrigenX, posicionOrigenY, direccion, COLOR_SERPIENTE);
        boolean segmentoAdicionado = esSegmentoValido(posibleNuevoSegmento);                             

        //Si hemos encontrado un segmento valido lo añadimos a la
        //serpiente; si no, informamos por pantalla
        if (segmentoAdicionado) {
            segmentos.add(posibleNuevoSegmento);
        }
        return segmentoAdicionado;
    }

    /*
     * Devuelve una coleccion con todos los segmentos de la serpiente
     */
    public ArrayList<Segment> getSegmentos()
    {
        return segmentos;
    }
}
