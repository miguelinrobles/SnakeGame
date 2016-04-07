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
    public static final int MARGEN_LIENZO = 10;
    public static final int TAMANO_CABEZA = 8;

    /*
     * Constructor de la clase Snake
     */
    public Snake(int anchoLienzo, int altoLienzo)
    {
        this.anchoLienzo = anchoLienzo;
        this.altoLienzo = altoLienzo;
        segmentos = new ArrayList<>();
        for (int i = 0; i < NUMERO_SEGMENTOS_INICIALES; i++) {
            addSegment();
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
        //lienzo.setForegroundColor(COLOR_SERPIENTE);
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
     * Adiciona un segmento aleatorio a la serpiente. Devuelve true en caso de que
     * haya sido capaz de añadir un nuevo segmento y false en otro caso.
     */
    public boolean addSegment() 
    {
        boolean segmentoAdicionado = false;

        Random aleatorio = new Random();
        ArrayList<Integer> direcciones = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            direcciones.add(i * DIFERENCIA_DE_GRADOS_ENTRE_DIRECCIONES);
        }

        //Calculamos las coordenadas de inicio del segmento: si no había
        //segmentos, lo ubicamos en una posicion aleatoria; si los había, al final del ultimo
        //segmento
        int posicionOrigenX = aleatorio.nextInt(anchoLienzo - (2 * MARGEN_LIENZO)) + MARGEN_LIENZO; 
        int posicionOrigenY = aleatorio.nextInt(altoLienzo - (2 * MARGEN_LIENZO)) + MARGEN_LIENZO; 
        if (segmentos.size() != 0) {
            posicionOrigenX = segmentos.get(segmentos.size() - 1).getPosicionFinalX();
            posicionOrigenY = segmentos.get(segmentos.size() - 1).getPosicionFinalY();
        }

        //Probamos todos los segmentos posibles hasta que demos con uno valido
        //o hayamos probado los posibles 4 nuevos segmentos
        Segment posibleNuevoSegmento = null;
        while (!direcciones.isEmpty() && !segmentoAdicionado) {
            int direccion = direcciones.remove(aleatorio.nextInt(direcciones.size()));
            posibleNuevoSegmento = new Segment(posicionOrigenX, posicionOrigenY, direccion, COLOR_SERPIENTE);
            segmentoAdicionado = esSegmentoValido(posibleNuevoSegmento);                             
        }

        //Si hemos encontrado un segmento valido lo añadimos a la
        //serpiente; si no, informamos por pantalla
        if (segmentoAdicionado) {
            segmentos.add(posibleNuevoSegmento);
        }

        return segmentoAdicionado;
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
        if ((segmento.getPosicionFinalX() >= anchoLienzo - MARGEN_LIENZO) ||
        (segmento.getPosicionFinalY() >= altoLienzo - MARGEN_LIENZO) ||
        (segmento.getPosicionFinalX() <= MARGEN_LIENZO) ||
        (segmento.getPosicionFinalY() <= MARGEN_LIENZO)) {
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
        return addSegment();
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
     * Devuelve la posicion inicial X del segmento en el que se encuentra la cabeza
     */
    public int getPosicionXInicialUltimo()
    {
        return segmentos.get(segmentos.size() - 1).getPosicionInicialX();
    }
    
    /**
     * Devuelve la posicion incial Y del segmento en el que se encuentra la cabeza
     */
    public int getPosicionYInicialUltimo()
    {
        return segmentos.get(segmentos.size() - 1).getPosicionInicialY();
    }
    
    /**
     * Coprueba si la galleta que le pasamos como parámetro
     * puede ser comida por la serpiente
     */
    public boolean comerGalleta(Galleta galleta)
    {
        boolean comer = false;
        int posicionFinalX = galleta.getPosicionFinalX();// Puntos de la galleta
        int posicionFinalY = galleta.getPosicionFinalY();
        int posicionInicialY = galleta.getPosicionInicialY();
        int posicionInicialX = galleta.getPosicionInicialX();
        
        int xFinal = getPosicionXFinalUltimo();// Sacamos estos puntos que vamos
        int yFinal = getPosicionYFinalUltimo();// a usar en las dos alternativas
        
        int radioCabeza = TAMANO_CABEZA / 2;
        
        if (getPosicionYFinalUltimo() == getPosicionYInicialUltimo()) {
            int xInicial = getPosicionXInicialUltimo();
            if ((xFinal >= posicionInicialX && xInicial <= posicionFinalX) || (xFinal <= posicionFinalX && xInicial >= posicionInicialX)){
                if ((yFinal + radioCabeza > posicionInicialY) && (yFinal - radioCabeza < posicionFinalY)) {
                    comer = true;
                }
            }
        }
        else {
            int yInicial = getPosicionYInicialUltimo();
            if ((yFinal >= posicionInicialY && yInicial <= posicionFinalY) || (yFinal <= posicionFinalY && yInicial >= posicionInicialY)){
                if (xFinal + radioCabeza > posicionInicialX && xFinal - radioCabeza < posicionFinalX) {
                    comer = true;
                }
            }
        }
        return comer;
    }
}
