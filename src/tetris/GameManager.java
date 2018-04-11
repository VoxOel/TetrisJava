package tetris;


public class GameManager {
    public enum KeyInput 
        { NULL, LEFT, RIGHT, SOFTFALL, HARDFALL, ROTCLOCK, ROTCOUNTER, HOLD, PAUSE }
    
    protected Board board;
    protected Scorecard scorecard;
    protected NextQueue nextQueue;
    protected HoldBox holdBox;
    protected KeyInput keyQueue;
    
    
    
    
    private void processKeyLatent()
    {
        switch(keyQueue)
        {
            case LEFT:
        }
    }
    
}
