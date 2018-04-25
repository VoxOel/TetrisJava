package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GameManager extends JPanel implements KeyListener{
    protected JPanel dispGuide;
    protected Board board;
    protected Scorecard scorecard;
    protected NextQueue nextQueue;
    protected HoldBox holdBox;
    protected Tetramino playTetra, ghostTetra;
    protected RotationHandler rotation;
    protected int tetraLowest;
    
    protected GameOptions op;
    protected KeyBinding bind;
    
    protected KeyHold keyHold;
    
    protected boolean hasHeld;  // keeps track if the player is able to hold
                                // again or not; once a piece is locked in, set
                                // this back to false
    
    protected Timer fallTimer, lockTimer;
    protected boolean inLockDown, shouldLock;
    protected int lockDownTracker;
    
    //sound
    SoundHandler bgm;       //background music
    
    
    
    public GameManager()
    {
        this(new GameOptions(), new KeyBinding());
    }
    
    public GameManager(GameOptions optionSettings, KeyBinding keyBindSettings)
    {
        super(null);
        
        // Game Setup Variables
        op = optionSettings;
        bind = keyBindSettings;
        
        //display and UI variables and setup
        dispGuide = new JPanel(new GridBagLayout());
        board = new Board(op.boardWidth, op.skyline*2);
        scorecard = new Scorecard(0, op.startingLevel, 15, true);
        nextQueue = new NextQueue(op.showNext);
        holdBox = new HoldBox(op.holdBox);
        rotation = new RotationHandler(board);
        
        
        GridBagConstraints constr = new GridBagConstraints();

        constr.fill = GridBagConstraints.BOTH;
        
        constr.gridx = 0;
        constr.gridy = 0;
        constr.weightx = 0.5;
        constr.weighty = .5;
        constr.gridheight = 1;
        dispGuide.add(holdBox,constr);

        constr.gridx = 1;
        constr.gridy = 0;
        constr.weightx = 0.88;
        constr.weighty = 1.0;
        constr.gridheight = 3;
        dispGuide.add(board,constr);
        
        constr.gridx = 2;
        constr.gridy = 0;
        constr.weightx = 0.5;
        constr.weighty = 0.5;
        constr.gridheight = 3;
        dispGuide.add(nextQueue,constr);
        
        constr.gridx = 0;
        constr.gridy = 1;
        constr.weightx = .5;
        constr.weighty = 0.4;
        constr.gridheight = 1;
        dispGuide.add(scorecard,constr);
        
        add(dispGuide, BorderLayout.CENTER);
        
        /*
        
        board.setBackground(Color.BLACK);
        holdBox.setBackground(Color.GREEN);
        nextQueue.setBackground(Color.RED);
        scorecard.setBackground(Color.cyan);
        */
        setBackground(Color.DARK_GRAY);
        dispGuide.setOpaque(false);
        board.setOpaque(false);
        holdBox.setOpaque(false);
        nextQueue.setOpaque(false);
        scorecard.setOpaque(false);
        
        
        //sound
        bgm = new SoundHandler("/tetris/audio/themeA.wav");
        bgm.setLoop(true);
        
        // Environment Variables
        hasHeld = false;
        keyHold = new KeyHold();
        
        //timer set-up
        lockTimer = new Timer(500, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(down())
                {
                    up();
                    shouldLock = true;
                }
                else
                {
                    lock();
                }  
            }
        });
        
        lockTimer.setRepeats(false);
        inLockDown = false;
        lockDownTracker = 0;
        
        fallTimer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fall();
            }
        });
        
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Image imageBorder = (new ImageIcon(
                getClass().getResource("/tetris/textures/border.png"))
                ).getImage();
        Image imageNext = (new ImageIcon(
                getClass().getResource("/tetris/textures/nextBox.png"))
                ).getImage();
        Image imageHold = (new ImageIcon(
                getClass().getResource("/tetris/textures/holdBox.png"))
                ).getImage();
        Image imageScore = (new ImageIcon(
                getClass().getResource("/tetris/textures/scoreBox.png"))
                ).getImage();
        
        int drawHeight = imageBorder.getHeight(null);
        int drawWidth = imageBorder.getWidth(null) +
                imageNext.getWidth(null) +
                imageHold.getWidth(null);
        
        drawHeight = drawHeight + drawHeight/20;
        drawWidth = drawWidth + drawWidth/10;
        
        int scale = 1;
        while(drawHeight*(scale+1) <= getHeight() && 
                drawWidth*(scale+1) <= getWidth())
            scale++;
        
        drawHeight *= scale;
        drawWidth *= scale;
        
        int drawX = getWidth()/2 - drawWidth/2;
        int drawY = getHeight()/2 - drawHeight/2;
        
        dispGuide.setBounds(drawX, drawY, drawWidth, drawHeight);
        
        validate();
    }
    
    public void toggleHoldVis()
    {
        holdBox.toggleVis();
    }
    
    public void setQueueShow(int i)
    {
        nextQueue.setShow(i);
    }
    
    public void run()
    { 
        initTetra('0');
        fallTimer.start();
        bgm.start();
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
            ghostTetra = charToTetra(tetra);
            initGhost();
            
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        
        tetraLowest = 99999;
        
        fallTimer.setDelay(calcFallDelay());
        
        if(!op.debugMode)
            fallTimer.restart();
        
        for( Chunk ch : playTetra.getChunkArray())
        {
            if(board.isSolidChunk(ch, 0, 0))
            {
                gameOver();
                lock();
            }
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
    
    protected void fall()
    {
        if(down())
        {
            if(keyHold.softFall)
            {
                scorecard.addScore(1);
            }
            repaintTetra();
            if(playTetra.getCurrentLow() < tetraLowest)
            {
                tetraLowest = playTetra.getCurrentLow();
                lockDownTracker = 0;
                inLockDown = false;
                shouldLock = false;
                lockTimer.stop();
            }
            else
            {
                if(shouldLock)
                {
                    if(down())
                    {
                        up();
                    }
                    else
                    {
                        lock();
                    }
                }
            }
        }
        else
        {
            if(!inLockDown)
                lockTimer.restart();
            inLockDown = true;
            
        }  
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
        while(down(playTetra))
        {
            scorecard.addScore(2);
        }
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
    
    protected void trackPlacement()
    {
        if(inLockDown)
        {
            lockDownTracker++;
            switch(op.placement)
            {
                case CLASSIC:
                    break;
                case EXTENDED:
                    if(lockDownTracker < 15)
                    {
                        lockTimer.restart();
                    }
                    break;
                case INFINITE:
                    lockTimer.restart();
                    break;
            }
        }
    }
    
    //returns lowest Y-value of playTetra
    protected void lock()
    {
        for(Chunk c : playTetra.getChunkArray())
        {
            board.setChunk(c.getX(), c.getY(), true, true, c.getColor());
        }
        
        clearCheck();
        
        hasHeld = false;
        
        if(playTetra.getCurrentLow() >= op.skyline)
        {
            gameOver();
        }
        else
        {
            initTetra();
        }
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
        
        //TODO: t-spin
        scorecard.lineScore(lines);
        
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
    
    public void pause()
    {
        bgm.stop();
        fallTimer.stop();
    }
    
    public void unpause()
    {
        placeGhost();
        repaint();
        Timer delay = new Timer(3000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                fallTimer.start();
                bgm.start();
            }
            
        });
        
        delay.setRepeats(false);
        delay.start();
        
    }
    
    
    
    public void processKey(int key)
    {
        if(key == bind.left)
        {
            left();
            trackPlacement();
            repaintTetra();
            keyHold.left = true;
        }
        else if(key == bind.right)
        {
            right();
            trackPlacement();
            repaintTetra();
            keyHold.right = true;
        }
        else if(key == bind.softFall)
        {
            if(!keyHold.softFall)
            {
                keyHold.softFall = true;
                
                fallTimer.setDelay(fallTimer.getDelay()/20);
                
                fallTimer.restart();
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
                rotation.clockwise(playTetra);
                trackPlacement();
                repaintTetra();

                keyHold.rotClock = true;
            }
        }
        else if(key == bind.rotCounter)
        {
            if(!keyHold.rotCounter)
            {
                rotation.counterClockwise(playTetra);
                trackPlacement();
                repaintTetra();
                    
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
        else if(key == bind.softFall)
        {
            down();
            repaintTetra();
            keyHold.softFall = true;
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