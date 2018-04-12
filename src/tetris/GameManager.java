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
        
        while(!gameOver)
        {
            //game!
        }
        
        //highscore and exit stuff
        
    }
    
    public void initTetra(){}   //TODO
    
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
