package tetris;


public class GameManager {
    public enum KeyInput 
        { NULL, LEFT, RIGHT, SOFTFALL, HARDFALL, 
        ROTCLOCK, ROTCOUNTER, HOLD, PAUSE }
    
    protected Board board;
    protected Scorecard scorecard;
    protected NextQueue nextQueue;
    protected HoldBox holdBox;
    protected Tetramino playTetra;
    protected Tetramino ghostTetra;
    
    protected boolean leftHold, rightHold, softHold;
    protected boolean hasHeld;  // keeps track if the player is able to hold
                                // again or not; once a piece is locked in, set
                                // this back to false
    
    public GameManager()
    {
        this(new GameOptions());
    }
    
    public GameManager(GameOptions op)
    {
        boolean gameOver = false;
        
        board = new Board(10,22);
        scorecard = new Scorecard();
        nextQueue = new NextQueue(op.showNext);
        holdBox = new HoldBox();
        
        hasHeld = false;
        
        while(!gameOver)
        {
            //game!
        }
        
        //highscore and exit stuff
        
    }
    
    public void initTetra(char c)
    {
        if(c == '0')
        {
            // initialize from NextQueue (default operation)
        }
        else
        {
            // initialize after performing a hold
            
            // playTetra = new Tetramino(c);
            // how do we do this?
        }
    }   //TODO
    
    protected Tetramino charToTetra(char c) throws Exception
    {
        switch(c)
        {
            case 'i': return new TetraI();
            case 'o': return new TetraO();
            case 't': return new TetraT();
            case 's': return new TetraS();
            case 'z': return new TetraZ();
            case 'j': return new TetraJ();
            case 'l': return new TetraL();
            default:
                throw new Exception("Attempt to pass char into charToTetra: " + c);
        }
    }
    
    protected boolean hold()
    {
        // sends playTetra type to HoldBox and recieves the type stored
        if(!hasHeld)
        {
            initTetra(holdBox.swap(playTetra.getType()));
            hasHeld = true;
            return true;
        }
        
        return false;
    }
    
    protected boolean left()
    {
        for(Chunk c : playTetra.getChunkArray())
        {
            if(c.getX() <= 0)
               return false;
        }
        
        playTetra.left();
        return true;
    }
    
    protected boolean right()
    {
        for(Chunk c : playTetra.getChunkArray())
        {
            if(c.getX() >= board.getWidth()-1)
               return false;
        }
        
        playTetra.right();
        return true;
    }
    
    
}
