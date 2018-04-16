package tetris;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Chunk {
    private boolean show;       //visibility on screen
    private boolean placed;
    private String color;
    private int gridX;
    private int gridY;
    
    
    public Chunk()
    {
        this(0,0,false,"grey");
    }
    
    public Chunk(String c)
    {
        this(0,0,true,c);
    }
    
    public Chunk(int x, int y, boolean vis, String c)
    {
        gridX = x;
        gridY = y;
        show = vis;
        placed = false;
        color = c;
    }
    
    public Chunk(Chunk c)
    {
        this(c.gridX , c.gridY, c.show, c.color);
    }
    
    public boolean setPlaced(boolean b)
    {
        placed = b;
        return true;
    }
    
    public boolean getPlaced()
    {
        return placed;
    }
    
    public boolean setColor(String newColor)
    {
        color = newColor;
        
        return true;
    }
    
    public String getColor()
    {
        return color;
    }
    
    public boolean setVisibility(boolean vis)
    {
        show = vis;
        
        return true;
    }
    
    public boolean getVisibility()
    {
        return show;
    }
    
    public boolean setX(int i)
    {
        gridX = i;
        return true;
    }
    
    public int getX()
    {
        return gridX;
    }
    
    public boolean setY(int i)
    {
        gridY = i;
        return true;
    }
    
    public int getY()
    {
        return gridY;
    }
    
    public boolean move(int x, int y)
    {
        gridX = x;
        gridY = y;
        return true;
    }
    
    public ImageIcon getImageIcon()
    {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("../textures/" + color + ".png"));
        
        return ii;
    }
    
    public Image getImage()
    {
        return getImageIcon().getImage();
    }
    
    
}
