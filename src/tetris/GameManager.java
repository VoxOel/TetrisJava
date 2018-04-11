package tetris;


public class GameManager {
    public enum KeyInput 
        { LEFT, RIGHT, SOFTFALL, HARDFALL, ROTCLOCK, ROTCOUNTER, HOLD, PAUSE }
    
    protected Board board;
    protected Scorecard scorecard;
    protected NextQueue nextQueue;
    protected HoldBox holdBox;
    protected KeyInput keyQueue;
    
    
    
    
    
    
}
