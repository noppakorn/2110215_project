package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyinput {
    public class KeyInput implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT -> {
                    break;
                }
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
