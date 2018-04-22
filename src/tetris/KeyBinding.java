package tetris;

import java.awt.event.KeyEvent;

public class KeyBinding {
    public int left, right, softFall, hardFall;
    public int rotClock, rotCounter, hold, pause;
    
    //debug tools
    public int debugToggle, endGame, lock, up, clearBoard;
    
    public KeyBinding()
    {
       
        left = KeyEvent.VK_LEFT;
        right = KeyEvent.VK_RIGHT;
        softFall = KeyEvent.VK_DOWN;
        hardFall = KeyEvent.VK_SPACE;
        rotClock = KeyEvent.VK_X;
        rotCounter = KeyEvent.VK_Z;
        hold = KeyEvent.VK_SHIFT;
        pause = KeyEvent.VK_ESCAPE; 
        
        // debug commands
        debugToggle = KeyEvent.VK_PERIOD;
        endGame = KeyEvent.VK_K;
        lock = KeyEvent.VK_CONTROL;
        up = KeyEvent.VK_UP;
        clearBoard = KeyEvent.VK_BACK_SPACE;
    }
    
}
