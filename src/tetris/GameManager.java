package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    protected boolean [] keyPressHeld;
    
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
        
        // Game Setup Variables
        
        op = optionSettings;
        bind = keyBindSettings;
        
        gameOver = false;
        
        board = new Board(10,22);
        scorecard = new Scorecard();
        nextQueue = new NextQueue(op.showNext);
        holdBox = new HoldBox();
        
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
        
        // Environment Variables
        hasHeld = false;
        keyPressHeld = new boolean[10];
       
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
            tetra = nextQueue.pullTetra();
        }
        
        try
        {
            playTetra = charToTetra(tetra);
            if(op.ghostTetra)
            {
                ghostTetra = charToTetra(tetra);
                initGhost();
            }
            
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        
        repaintTetra();
    }
    
    protected void initGhost()
    {
        for(int i = 0; i < 4; i++)
        {
            ghostTetra.getChunkArray()[i].setColor(
                    playTetra.getChunkArray()[i].getColor() + "Ghost");
        }
    }
    
    protected void placeGhost()
    {
        for(int i=0; i < 4; i++)
        {
            ghostTetra.getChunkArray()[i].setX(playTetra.getChunkArray()[i].getX());
            ghostTetra.getChunkArray()[i].setY(playTetra.getChunkArray()[i].getY());
        }
        
        while(down(ghostTetra)){}
    }
    
    public void repaintTetra()
    {
        board.clearUnplacedChunks();
        
        if(op.ghostTetra)
        {
            placeGhost();

            for(Chunk c : ghostTetra.getChunkArray())
            {
                board.setChunk(c.getX(), c.getY(), true, false, c.getColor());
            }
        }
        
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
        if(!hasHeld)
        {
            initTetra(holdBox.swap(playTetra.getType()));
            hasHeld = true;
            return true;
        }
        
        return false;
    }
    
    //checks to see if the chunk is sold with x and y transform relative to c
    protected boolean isSolidChunk(Chunk c, int xTransform, int yTransform)
    {
        boolean placed, vis;
        placed = board.getChunkPlaced(c.getX() + xTransform, 
                c.getY() + yTransform);
        vis = board.getChunkVisibility(c.getX() + xTransform, 
                c.getY() + yTransform);
        
        return placed && vis;
    }
    
    protected boolean up()
    {
        for(Chunk c : playTetra.getChunkArray())
        {
            if(c.getY() >= board.getGridHeight() - 1)
                return false;
            
            if(isSolidChunk(c,0,1))
                return false;
        }
        
        playTetra.up();
        return true;
    }
    
    protected boolean hardFall()
    {
        while(down(playTetra)){}
        return true;
    }
    
    protected boolean down()
    {
        return down(playTetra);
    }
    
    protected boolean down(Tetramino tetra)
    {
        for(Chunk c : tetra.getChunkArray())
        {
            if(c.getY() <= 0)
                return false;
            
            if(isSolidChunk(c,0,-1))
                return false;
        }
        
        tetra.down();
        return true;
    }
    
    protected boolean left()
    {
        for(Chunk c : playTetra.getChunkArray())
        {
            if(c.getX() <= 0)   //bounding box checks
               return false;
            
            if(isSolidChunk(c,-1,0))    //collision check
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
            
            if(isSolidChunk(c,1,0)) //collision check
                return false;
        }
        
        playTetra.right();
        return true;
    }
    
    protected boolean lock()
    {
        for(Chunk c : playTetra.getChunkArray())
        {
            board.setChunk(c.getX(), c.getY(), true, true, c.getColor());
        }
        
        initTetra();
        hasHeld = false;
        return true;
    }
    
    public void processKey(int key)
    {
         if(key == bind.left)
        {
            left();
            repaintTetra();
            keyPressHeld[0] = true;
        }
        else if(key == bind.right)
        {
            right();
            repaintTetra();
            keyPressHeld[1] = true;
        }
        else if(key == bind.softFall)
        {
            down();
            repaintTetra();
            keyPressHeld[2] = true;
        }
        else if(key == bind.hardFall)
        {
            if(!keyPressHeld[3])
            {
                hardFall();
                lock();
                repaintTetra();
                keyPressHeld[3] = true;
            }
        }
        else if(key == bind.rotClock)
        {
            keyPressHeld[4] = true;
        }
        else if(key == bind.rotCounter)
        {
            keyPressHeld[5] = true;
        }
        else if(key == bind.hold)
        {
            hold();
            keyPressHeld[6] = true;
        }
        else if(key == bind.pause)
        {
            
        }
        else if(key == bind.debugToggle)
        {
            toggleDebug();
        }
    }
    
    public void processKeyDebug(int key)
    {
        
        if(key == bind.endGame)
        {
            gameOver = true;
            keyPressHeld[7] = true;
        }
        
        // debug commands
        else if(key == bind.lock)
        {
            lock();
            keyPressHeld[8] = true;
        }
        else if(key == bind.up)
        {
            up();
            repaintTetra();
            keyPressHeld[9] = true;
        }
        else
        {
            processKey(key);
        }
        
        
        //debugInfo();
    }
    
    protected void toggleDebug()
    {
        op.debugMode = !op.debugMode;
    }

    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke)
    {
        if(op.debugMode)
        {
            processKeyDebug(ke.getKeyCode());
        }
        else
        {
            processKey(ke.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) 
    {
        // I feel like this is definitely not the solution that
        // we want because it sets the entire array to false again, but
        // after playing around a bit it seems to work exactly like it
        // should if it was correct... see function up() - hardFall
        Arrays.fill(keyPressHeld, false);
    }
}