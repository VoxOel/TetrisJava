package tetris;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel {
    private Chunk[][] chunkGrid;
    int width, height;
    
    public Board(int w, int h)
    {
        if(w < 4)
            w = 4;
        if(h < 6)
            h = 6;
        
        width = w;
        height = h;
        chunkGrid = new Chunk[width][height];
        
        for( int i = 0; i < width; i++)
        {
            for( int j = 0; j < height; j++)
            {
                chunkGrid[i][j] = new Chunk();
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        ImageIcon border = new ImageIcon(
                getClass().getResource("../textures/border.png"));
        int borderNativeWidth = border.getImage().getWidth(null);
        int borderNativeHeight = border.getImage().getHeight(null);
        
        int scale = 1;
        while(borderNativeHeight*(scale+1) <= getHeight())
            scale++;

        int borderDrawHeight = borderNativeHeight * scale;
        int borderDrawWidth = borderNativeWidth * scale;
        
        int borderDrawX = getWidth()/2 - borderDrawWidth/2;
        int borderDrawY = getHeight()/2 - borderDrawHeight/2;
        
        Chunk mino = new Chunk();
        int minoNativeWidth = mino.getImage().getWidth(null);
        int minoNativeHeight = mino.getImage().getHeight(null);
        int minoDrawWidth = minoNativeWidth * scale;
        int minoDrawHeight = minoNativeHeight * scale;
        int minoBaseX = borderDrawX + minoDrawWidth/2;
        int minoBaseY = borderDrawY + borderDrawHeight - 3*minoDrawHeight/2;
        
        //draw chunks
        for(int x = 0; x < width; x++ )
        {
            for(int y = 0; y < height; y++)
            {
                if(getChunkVisibility(x,y))
                {
                    g2d.drawImage(chunkGrid[x][y].getImage(),
                           minoBaseX + minoDrawWidth*x,
                           minoBaseY - minoDrawHeight*y,
                           minoDrawWidth, minoDrawHeight, null);
                }
            }
        }
        
        //draw border
        g2d.drawImage(border.getImage(),
                borderDrawX, borderDrawY,                     //start coords
                borderDrawWidth, borderDrawHeight, null);
        
    }
    
    public int getGridWidth()
    {
        return width;
    }
    
    public int getGridHeight()
    {
        return height;
    }
    
    public boolean setChunk(
            int x, int y, boolean show, boolean placed, String color)
    {
        boolean success = true;
        
        if(!chunkGrid[x][y].setX(x))
            success = false;
        
        if(!chunkGrid[x][y].setY(y))
            success = false;
        
        if(!chunkGrid[x][y].setVisibility(show))
            success = false;
        
        if(!chunkGrid[x][y].setPlaced(placed))
            success = false;
        
        if(!chunkGrid[x][y].setColor(color))
            success = false;
        
        return success;
        
    }
    
    public void clearUnplacedChunks()
    {
         for(int x = 0; x < width; x++ )
        {
            for(int y = 0; y < height; y++)
            {
                if(!getChunkPlaced(x,y))
                {
                    chunkGrid[x][y].setVisibility(false);
                }
            }
        }
    }
    
    public boolean getChunkVisibility(int x, int y)
    {
        return chunkGrid[x][y].getVisibility();
    }
    
    public boolean getChunkPlaced(int x, int y)
    {
        return chunkGrid[x][y].getPlaced();
    }
    
    public String getChunkColor(int x, int y)
    {
        return chunkGrid[x][y].getColor();
    }
    
}
