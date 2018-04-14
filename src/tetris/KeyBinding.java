package tetris;

import java.awt.event.KeyEvent;

public class KeyBinding {
    public int left, right, softFall, hardFall;
    public int rotClock, rotCounter, hold, pause;
    
    public KeyBinding()
    {
       
        left = KeyEvent.VK_LEFT;
        right = KeyEvent.VK_RIGHT;
        softFall = KeyEvent.VK_DOWN;
        hardFall = KeyEvent.VK_UP;
        pause = KeyEvent.VK_ESCAPE; //temporary?
        
    }
    
}
