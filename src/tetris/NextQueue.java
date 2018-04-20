package tetris;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class NextQueue extends JPanel{

    private boolean[] bag;
    private Queue<Character> queue;
    private int showNum;

    public NextQueue()
    {
        this(6);
    }

    public NextQueue(int show) {
        showNum = show;

        bag = new boolean[7];
        for(int i = 0; i < 7; i++)
        {
            bag[i] = true;
        }

        queue = new LinkedList<>();
        for(int i = 0; i < 7; i++) 
        {
            queue.offer(grab());
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        LinkedList<Character> list = (LinkedList<Character>)queue;
        
        try {
            BufferedImage next = ImageIO.read(getClass().getResource(
                    "/tetris/textures/nextBox.png"));
            BufferedImage nextQueue = ImageIO.read(getClass().getResource(
                    "/tetris/textures/queueBox.png"));
            BufferedImage nextQueueLeft = ImageIO.read(getClass().getResource(
                    "/tetris/textures/queueBoxLeft.png"));
            BufferedImage nextQueueRight = ImageIO.read(getClass().getResource(
                    "/tetris/textures/queueBoxRight.png"));
            
            BufferedImage[] tetraQueue = new BufferedImage[7];
            ListIterator<Character> it = list.listIterator();
            String color;
            for(int i = 0; i < 7; i++)
            {
                switch(it.next())
                {
                    case 'i':
                        color = "cyan";
                        break;
                    case 'o':
                        color = "yellow";
                        break;
                    case 't':
                        color = "purple";
                        break;
                    case 's':
                        color = "green";
                        break;
                    case 'z':
                        color = "red";
                        break;
                    case 'j':
                        color = "blue";
                        break;
                    case 'l':
                        color = "orange";
                        break;
                    default: 
                        color = "cyan";
                        
                }
                
                color = color + "Tetra";
                if(i > 0)
                {
                    color = color + "Mini";
                }
                
                tetraQueue[i] = ImageIO.read(getClass().getResource(
                    "/tetris/textures/" + color + ".png"));
            }
            
            int nativeWidth = next.getWidth();
            int nativeHeight = next.getHeight();
             
            int scale = 1;
            while(nativeWidth*(scale+1) <= getWidth() && 
                    nativeHeight*(scale+1) <= getHeight())
                scale++;
            
            int drawHeight = nativeHeight * scale;
            int drawWidth = nativeWidth * scale;
        
            int drawX = 0;
            int drawY = getHeight()/30;
            
            g2d.drawImage(next,
                drawX, drawY, drawWidth, drawHeight, null);
            g2d.drawImage(tetraQueue[0],
                drawX, drawY, drawWidth, drawHeight, null);
            
            GameFont gf = new GameFont("next");
        
            int fontDrawWidth = gf.getImage().getWidth() * scale;
            int fontDrawHeight = gf.getImage().getHeight() * scale;
        
            drawX = drawX + drawWidth/2 - fontDrawWidth/2;
            drawY = drawY + drawHeight - drawHeight/10 - fontDrawHeight;
        
            g2d.drawImage(gf.getImage(), drawX, drawY, 
                fontDrawWidth, fontDrawHeight,  null);
            
            
            drawY = getHeight()/30 + drawHeight;
            
            drawHeight = nextQueue.getHeight() * scale;
            drawWidth = nextQueue.getWidth() * scale;
            
            for(int i = 0; i < showNum - 1; i++)
            {
                if(i % 2 == 0)
                    drawX = 0;
                else
                    drawX = drawWidth;
                
                if(i == 0)
                {
                    g2d.drawImage(nextQueue,
                        drawX, drawY, drawWidth, drawHeight, null);
                }
                else if(i % 2 == 0)
                {
                    g2d.drawImage(nextQueueLeft,
                        drawX, drawY, drawWidth, drawHeight, null);
                }
                else
                {
                    g2d.drawImage(nextQueueRight,
                        drawX, drawY, drawWidth, drawHeight, null);
                }
                    
                g2d.drawImage(tetraQueue[i+1],
                    drawX, drawY, drawWidth, drawHeight, null);
                
                drawY += drawHeight/2;
            }
             
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
       
    }
    
    public boolean setShow(int i)
    {
        if(i < 1)
            i = 1;
        if(i > 6)
            i = 6;
        
        showNum = i;
        
        return true;
    }
    
    public int getShow()
    {
        return showNum;
    }

    public char pullTetra() //returns next char in queue
    {
        char ret;

        ret = queue.remove();
        queue.offer(grab());
        
        repaint();
        
        return ret;
    }

    private char grab()     //randomly generates char for Tetramino
    {                       //based on Tetris generation rules
        boolean empty = true;

        for(boolean b : bag)    //checking to see if grab-bag is empty
        {          
            if(b)
            {
                empty = false;
            }
        }

        if(empty)
        {
            for(int i = 0; i < 7; i++) //filling empty grab-bag
            {       
                bag[i] = true;
            }
        }

        Random rand = new Random();

        while(true) {                           //pulls from grab-bag, and sets value as pulled
            int num = rand.nextInt(7);    //uses frequesncy array to represent grab-bag
            if(bag[num]) {
                bag[num] = false;
                switch(num) {             //convert pull to Tetra char
                    case 0: return 'i';
                    case 1: return 'o';
                    case 2: return 't';
                    case 3: return 's';
                    case 4: return 'z';
                    case 5: return 'j';
                    case 6: return 'l';
                }
            }
        }


    }

}
