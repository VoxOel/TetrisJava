package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;


public class GameManager extends JPanel implements KeyListener{
    protected Board board;
    protected Scorecard scorecard;
    protected NextQueue nextQueue;
    protected HoldBox holdBox;
    protected Tetramino playTetra;
    protected Tetramino ghostTetra;
    
    protected GameOptions op;
    protected KeyBinding bind;
    
    protected JPanel leftGUI, rightGUI;
    
    protected boolean gameOver;
    protected boolean leftHold, rightHold, softHold; //keyinput tracking
    protected boolean hasHeld;  // keeps track if the player is able to hold
                                // again or not; once a piece is locked in, set
                                // this back to false
    
    public GameManager()
    {
        this(new GameOptions(), new KeyBinding());
    }
    
    public GameManager(GameOptions optionSettings, KeyBinding keyBindSettings)
    {
        super(new BorderLayout(10,10));
        
        op = optionSettings;
        bind = keyBindSettings;
        
        gameOver = false;
        
        board = new Board(10,22);
        scorecard = new Scorecard();
        nextQueue = new NextQueue(op.showNext);
        holdBox = new HoldBox();
        
        hasHeld = false;
        
        leftGUI = new JPanel( new BorderLayout(5,5));
        rightGUI = new JPanel( new BorderLayout(5,5));
        
        leftGUI.add(holdBox, BorderLayout.NORTH);
        
        rightGUI.add(nextQueue, BorderLayout.NORTH);
        rightGUI.add(scorecard, BorderLayout.SOUTH);
        
        add(leftGUI, BorderLayout.WEST);
        add(rightGUI, BorderLayout.EAST);
        add(board, BorderLayout.CENTER);
        
        board.setBackground(Color.BLUE);
        leftGUI.setBackground(Color.yellow);
        rightGUI.setBackground(Color.green);
        holdBox.setBackground(Color.PINK);
        nextQueue.setBackground(Color.RED);
        scorecard.setBackground(Color.cyan);
        
       
    }
    
    public void run()
    { 
        
        
        System.out.println("running");
        
        initTetra('t'); //testing
        
        //exit stuff and highscores
    }
    
    public void debugInfo()
    {
        for( Chunk c : playTetra.getChunkArray() )
        {
            System.out.println("Chunk at x/y: " + c.getX() + "/" + c.getY() );
        }
    }
    
    public void initTetra()
    {
        initTetra('0');
    }
    
    public void initTetra(char c)
    {
        char tetra = c;
        
        if(tetra == '0')
        {
            // initialize from NextQueue (default operation)
            // tetra = nextQueue.next() or whatever
        }
        
        try
        {
            playTetra = charToTetra(c);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        
        
    }
    
    public void repaintTetra()
    {
        board.clearUnplacedChunks();
        for(Chunk c : playTetra.getChunkArray())
        {
            board.setChunk(c.getX(), c.getY(), true, false, c.getColor());
        }
        board.repaint();
    }
    
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
    
    protected boolean up()
    {
        for(Chunk c : playTetra.getChunkArray())
        {
            if(c.getY() >= board.getGridHeight() - 1)
                return false;
        }
        
        playTetra.up();
        return true;
    }
    
    protected boolean down()
    {
        for(Chunk c : playTetra.getChunkArray())
        {
            if(c.getY() <= 0)
                return false;
        }
        
        playTetra.down();
        return true;
    }
    
    protected boolean left()
    {
        for(Chunk c : playTetra.getChunkArray())
        {
            if(c.getX() <= 0)   //bounding box checks
               return false;
        }
        
        playTetra.left();
        return true;
    }
    
    protected boolean right()
    {
        for(Chunk c : playTetra.getChunkArray())
        {
            if(c.getX() >= board.getGridWidth()-1)  //bounding box checks
               return false;
        }
        
        playTetra.right();
        return true;
    }
    
    public void processKey(int key)
    {
        /*
        *
        *   everything in this function is temporary for testing purposes
        *
        */
        
        if(key == bind.left)
        {
            left();
        }
        else if(key == bind.right)
        {
            right();
        }
        else if(key == bind.softFall)
        {
            down();
        }
        else if(key == bind.hardFall)
        {
            up();
        }
        else if(key == bind.rotClock)
        {
            
        }
        else if(key == bind.rotCounter)
        {
            
        }
        else if(key == bind.hold)
        {
            
        }
        else if(key == bind.pause)
        {
            gameOver = true;
        }
        
        
        repaintTetra();
        //debugInfo();
    }

    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke)
    {
        processKey(ke.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent ke) 
    {
        //TODO: repeating keys
        //somehow repeating keys already... gotta fix?
    }
}
