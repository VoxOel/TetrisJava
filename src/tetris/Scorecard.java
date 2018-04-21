package tetris;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Scorecard extends JPanel{

    private int score;
    private int level;
    private int levelLimit;
    private int linesCleared;
    private boolean backtoback;
    private boolean fixedLeveling;

    /* constructors */
    Scorecard() {
        this(0,1,15, true);
    }

    Scorecard(int newScore, int newLevel, int limit, boolean fixed) {
        score = newScore;
        level = newLevel;
        levelLimit = limit;
        linesCleared = 0;
        backtoback = false;
        fixedLeveling = true;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        try {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            
            StringBuilder stringScore = new StringBuilder("" + score);
            while (stringScore.length() < 8)
            {
                stringScore.insert(0, ' ');
            }
            if(stringScore.length() > 8)
            {
                stringScore = new StringBuilder("99999999");
            }
            
            StringBuilder levelString = new StringBuilder("" + level);
            while (levelString.length() < 2)
            {
                levelString.insert(0, 0);
            }
            if(levelString.length() > 2)
            {
                levelString = new StringBuilder("99");
            }
            
            GameFont scoreDisp = new GameFont(stringScore.toString());
            GameFont levelDisp = new GameFont(levelString.toString());
            
            BufferedImage card = ImageIO.read(getClass().getResource(
                    "/tetris/textures/scoreBox.png"));
            
            int nativeWidth = card.getWidth();
            int nativeHeight = card.getHeight();

            int scale = 1;
            while(nativeWidth*(scale+1) <= getWidth() && 
                    nativeHeight*(scale+1) <= getHeight())
                scale++;

            int drawHeight = nativeHeight * scale;
            int drawWidth = nativeWidth * scale;

            int drawX = getWidth() - drawWidth;
            int drawY = getHeight()/20;

            g2d.drawImage(card,
                    drawX, drawY, drawWidth, drawHeight, null);
            
            int fontDrawX = drawX + (drawWidth*32)/48;
            int fontDrawY = drawY + (drawHeight*11)/48;
            
            g2d.drawImage(levelDisp.getImage(),
                    fontDrawX, fontDrawY,
                    levelDisp.getImage().getWidth()*scale,
                    levelDisp.getImage().getHeight()*scale,
                    null);
            
            fontDrawX = drawX + (drawWidth*8)/48;
            fontDrawY = drawY + (drawHeight*29)/48;
            
            g2d.drawImage(scoreDisp.getImage(),
                    fontDrawX, fontDrawY,
                    scoreDisp.getImage().getWidth()*scale,
                    scoreDisp.getImage().getHeight()*scale,
                    null);
            
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /* setters */
    public void setScore(int newScore) {
        score = newScore;
    }
    
    public void lineScore(int linesCleared)
    {
        lineScore(linesCleared, false, false);
    }

    public void lineScore(int lines, boolean TSpin, boolean mini) {
        int actionTotal = 0;
        boolean oldB2B = backtoback;
        backtoback = false;
        
        switch(lines) {
            case 0:
                if(TSpin)
                {
                    if(mini)
                        actionTotal = 100 * level;
                    else
                        actionTotal = 400 * level;
                }
                break;
            case 1: // Single
                if(TSpin)
                {
                    if(mini)
                        actionTotal = 200 * level;
                    else
                    {
                        actionTotal = 800 * level;
                    }
                    backtoback = true;
                }
                else
                    actionTotal = 100 * level;
                break;
            case 2: // Double
                if(TSpin)
                {
                    actionTotal = 1200 * level;
                    backtoback = true;
                }
                else
                    actionTotal = 300 * level;
                break;
            case 3: // Triple
                if(TSpin)
                {
                    actionTotal = 1600 * level;
                    backtoback = true;
                }
                else
                {
                    actionTotal = 500 * level;
                }
                break;
            case 4: // Tetris!
                actionTotal = 800 * level;
                backtoback = true;
                break;
        }
        
        if(oldB2B && backtoback)
        {
            actionTotal += actionTotal/2;
        }
        
        score += actionTotal;
        
        linesCleared += lines;
        
        if(fixedLeveling)
        {
            if(linesCleared >= level*10)
            {
                incrementLevel();
            }
        }
    }

    public void setLevel(int newLevel) {
        level = newLevel;
        if(level > levelLimit)
            level = levelLimit;
        if (level < 1)
            level = 1;
    }

    public void incrementLevel() {
        level++;
        if(level > levelLimit)
            level = levelLimit;
    }

    public void setLinesCleared(int newLinesCleared) {
        linesCleared = newLinesCleared;
    }

    /* getters */
    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public int getLinesCleared() {
        return linesCleared;
    }

}
