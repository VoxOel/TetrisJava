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
    private int linesCleared;
    private int singles, doubles, triples;
    private int tetrises, tSpins;

    /* constructors */
    Scorecard() {
        score = 0;
        level = 1;
        linesCleared = 0;
        singles = 0;        //  TODO:
        doubles = 0;        //      these 5 need
        triples = 0;        //      getters and
        tetrises = 0;       //      setters!
        tSpins = 0;         //
    }

    Scorecard(int newScore, int newLevel) {
        score = newScore;
        level = newLevel;
        linesCleared = 0;
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
                stringScore.insert(0, 0);
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

    public void addScore(int linesCleared, boolean TSpin) {
        /* assumes that this function will probably recieve
         * parameters from Board or GameManager class.
         * pseudo:
         * if(wasTSpin) {Scorecard.addScore(linesCleared, true)}
         */
        switch(linesCleared) {
            case 0: // TSpin
                score += 400 * level;
                break;
            case 1: // Single
                if(TSpin)
                    score += 800 * level;
                else
                    score += 100 * level;
                break;
            case 2: // Double
                if(TSpin)
                    score += 1200 * level;
                else
                    score += 300 * level;
                break;
            case 3: // Triple
                if(TSpin)
                    score += 1600 * level;
                else
                    score += 500 * level;
                break;
            case 4: // Tetris!
                score += 800 * level;
                break;
        }

    }

    public void setLevel(int newLevel) {
        level = newLevel;
    }

    public void incrementLevel() {
        level++;
    }

    public void setLinesCleared(int newLinesCleared) {
        linesCleared = newLinesCleared;
    }

    public void addLinesCleared(int add) {
        linesCleared += add;
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
