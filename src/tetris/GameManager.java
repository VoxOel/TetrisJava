package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GameManager extends JPanel implements KeyListener{
    protected Board board;
    protected Scorecard scorecard;
    protected NextQueue nextQueue;
    protected HoldBox holdBox;
    protected Tetramino playTetra, ghostTetra;
    protected int tetraLowest;
    
    protected GameOptions op;
    protected KeyBinding bind;
    
    protected JPanel leftGUI, rightGUI;
    
    protected KeyHold keyHold;
    
    protected boolean hasHeld;  // keeps track if the player is able to hold
                                // again or not; once a piece is locked in, set
                                // this back to false
    
    protected Timer fallTimer, lockTimer;
    
    
    
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
        
        //display and UI variables and setup
        board = new Board(op.boardWidth,op.skyline*2);
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
        keyHold = new KeyHold();
        
        lockTimer = new Timer(500, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(!lock())
                {
                    gameOver();
                }
            }
        });
        
        lockTimer.setRepeats(false);
        
        fallTimer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fall();
            }
        });
        
    }
    
    public void run()
    { 
        initTetra('0');
        fallTimer.start();
    }
    
    public void gameOver()
    {
        //TODO make this function do stuff
        System.out.println("Game Over!");
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
        
        tetraLowest = playTetra.getLowest();
        
        fallTimer.setDelay(calcFallDelay());
        
        fallTimer.restart();
        
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
    
    protected void fall()
    {
        if(down())
        {
            lockTimer.stop();
        }
        else
        {
            if(playTetra.getLowest() < tetraLowest)
            {
                tetraLowest = playTetra.getLowest();
                lockTimer.restart();
            }
            else
            {
                lockTimer.start();
            }
            
        }
        
        repaintTetra();
    }
    
    protected int calcFallDelay()
    {
        double delay;
        
        delay = scorecard.getLevel() - 1;
        delay *= .007;
        delay = .8 - delay;
        delay = Math.pow(delay, scorecard.getLevel() - 1);
        delay *= 1000;
        
        return (int)delay;
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
    
    protected boolean up()
    {
        for(Chunk c : playTetra.getChunkArray())
        {
            if(c.getY() >= board.getGridHeight() - 1)
                return false;
            
            if(board.isSolidChunk(c,0,1))
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
            
            if(board.isSolidChunk(c,0,-1))
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
            
            if(board.isSolidChunk(c,-1,0))    //collision check
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
            
            if(board.isSolidChunk(c,1,0)) //collision check
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
        
        clearCheck();
        
        initTetra();
        hasHeld = false;
        return true;
    }
    
    protected int clearCheck()
    {
        
        //check line clears
        int linesCleared[] = new int[4];
        Arrays.fill(linesCleared, -1);
        
        int lines = 0;
        for(int y = 0; y < 20; y++)
        {
            if(checkLine(y))
            {
                linesCleared[lines++] = y;
            }
        }
        
        //TODO: scoring stuff
        
        //clear lines
        for(int i = linesCleared.length - 1; i >= 0; i--)
        {
            if(linesCleared[i] != -1)
                clearLine(linesCleared[i]);
        }
        
        return lines;
    }
    
    //returns true if line is filled
    protected boolean checkLine(int line)
    {
        for(int x = 0; x < board.getGridWidth(); x++)
        {
            if(!board.getChunkPlaced(x,line))
            {
                return false;
            }
        }
        
        return true;
    }
    
    protected void clearLine(int line)
    {
        for(int y = line; y < board.getGridHeight(); y++)
        {
            for(int x = 0; x < board.getGridWidth(); x++)
            {
                
                if(y < board.getGridHeight() - 1)
                    board.setChunk(x, y, 
                            board.getChunkVisibility(x, y+1),
                            board.getChunkPlaced(x,y+1),
                            board.getChunkColor(x,y+1));
                else
                {
                    board.setChunk(x, y, false, false, board.getChunkColor(x, y));
                }
            }
        }
    }
    
    public void processKey(int key)
    {
        if(key == bind.left)
        {
            left();
            repaintTetra();
            keyHold.left = true;
        }
        else if(key == bind.right)
        {
            right();
            repaintTetra();
            keyHold.right = true;
        }
        else if(key == bind.softFall)
        {
            if(!keyHold.softFall)
            {
                keyHold.softFall = true;
                
                fallTimer.setDelay(fallTimer.getDelay()/20);
            }
        }
        else if(key == bind.hardFall)
        {
            if(!keyHold.hardFall)
            {
                hardFall();
                lock();
                repaintTetra();
                keyHold.hardFall = true;
            }
        }
        else if(key == bind.rotClock)
        {
            if(!keyHold.rotClock)
            {
                if(RotationHandler.rotationSafe(board, playTetra, true))
                {
                    playTetra.rotateClockwise();
                    repaintTetra();
                }
                keyHold.rotClock = true;
            }
        }
        else if(key == bind.rotCounter)
        {
            if(!keyHold.rotCounter)
            {
                if(RotationHandler.rotationSafe(board, playTetra, false))
                {
                    playTetra.rotateCounterClockwise();
                    repaintTetra();
                }
                keyHold.rotCounter = true;
            }
        }
        else if(key == bind.hold)
        {
            if(!keyHold.hold)
            {
                if(op.holdBox)
                    hold();
                keyHold.hold = true;
            }
        }
        else if(key == bind.pause)
        {
            if(!keyHold.pause)
            {
                keyHold.pause = true;
            }
        }
        else if(key == bind.debugToggle)
        {
            if(!keyHold.debugToggle)
            {
                toggleDebug();
                keyHold.debugToggle = true;
            }
        }
    }
    
    public void processKeyDebug(int key)
    {
        
        if(key == bind.endGame)
        {
            if(!keyHold.endGame)
            {
                gameOver();
                keyHold.endGame = true;
            }
            
        }
        else if(key == bind.lock)
        {
            if(!keyHold.lock)
            {
                lock();
                keyHold.lock = true;
            }
            
        }
        else if(key == bind.up)
        {
            up();
            repaintTetra();
            keyHold.up = true;
        }
        else if(key == bind.clearBoard)
        {
            board.clearAllChunks();
            board.repaint();
            repaintTetra();
            keyHold.clearBoard = true;
        }
        else
        {
            processKey(key);
        }
    }
    
    public void processRelease(int key)
    {
        if(key == bind.left)
        {
            keyHold.left = false;
        }
        else if(key == bind.right)
        {
            keyHold.right = false;
        }
        else if(key == bind.softFall)
        {
            fallTimer.setDelay(calcFallDelay());
            
            keyHold.softFall = false;
        }
        else if(key == bind.hardFall)
        {
            keyHold.hardFall = false;
        }
        else if(key == bind.rotClock)
        {
            keyHold.rotClock = false;
        }
        else if(key == bind.rotCounter)
        {
            keyHold.rotCounter = false;
        }
        else if(key == bind.hold)
        {
            keyHold.hold = false;
        }
        else if(key == bind.pause)
        {
            keyHold.pause = false;
        }
        else if(key == bind.debugToggle)
        {
            keyHold.debugToggle = false;
        }
        else if(key == bind.endGame)
        {
            keyHold.endGame = false;
        }
        else if(key == bind.lock)
        {
            keyHold.lock = false;
        }
        else if(key == bind.up)
        {
            keyHold.up = false;
        }
        else if(key == bind.clearBoard)
        {
            keyHold.clearBoard = false;
        }
    }
    
    protected void toggleDebug()
    {
        op.debugMode = !op.debugMode;
        
        if(op.debugMode)
        {
            fallTimer.stop();
        }
        else
        {
            fallTimer.start();
        }
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
        processRelease(ke.getKeyCode());
    }
    
    protected class KeyHold
    {
        //play keys
        public boolean left, right, softFall, hardFall,
                rotClock, rotCounter, hold, pause;
        
        //debug keys
        public boolean debugToggle, endGame,
                lock, up, clearBoard;
        
        public KeyHold()
        {
            left = right = softFall = hardFall = rotClock = rotCounter = 
                    hold = pause = debugToggle = endGame = lock = up =
                    clearBoard = false;
        }
        
        public void clearAll()
        {
            setAll(false);
        }
        
        public void setAll(boolean b)
        {
            left = right = softFall = hardFall = rotClock = rotCounter = 
                    hold = pause = debugToggle = endGame = lock = up =
                    clearBoard = b;
        }
    }
}