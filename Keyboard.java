import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("hola")
public class Keyboard extends JPanel {
    private MyKeyListener listener;
    public Keyboard() {
        listener = new MyKeyListener();
        addKeyListener(listener);
        setFocusable(true);
    }

    public void addSnake(Snake snake)
    {
        listener.addSnake(snake);
    }

    public class MyKeyListener implements KeyListener {
        private Snake snake;
        public void addSnake(Snake snake)
        {
            this.snake = snake;
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {            
            snake.moverSnakeTeclado(KeyEvent.getKeyText(e.getKeyCode()));
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
