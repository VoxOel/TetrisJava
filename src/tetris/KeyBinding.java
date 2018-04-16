package tetris;

import java.awt.event.KeyEvent;

public class KeyBinding {
    public int left, right, softFall, hardFall;
    public int rotClock, rotCounter, hold, pause;
    public int debug_lock;
    
    public KeyBinding()
    {
       
        left = KeyEvent.VK_LEFT;
        right = KeyEvent.VK_RIGHT;
        softFall = KeyEvent.VK_DOWN;
        hardFall = KeyEvent.VK_UP;
        rotClock = KeyEvent.VK_X;
        rotCounter = KeyEvent.VK_Z;
        hold = KeyEvent.VK_SHIFT;
        pause = KeyEvent.VK_ESCAPE; //temporary?
        
        // debug commands
        debug_lock = KeyEvent.VK_CONTROL;
    }
    
}
