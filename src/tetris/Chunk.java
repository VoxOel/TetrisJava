package tetris;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Chunk {
    private boolean show;
    private String color;
    private int gridX;
    private int gridY;
    
    
    public Chunk()
    {
        show = false;
    }
    
    public Chunk(int x, int y, boolean s, String c)
    {
        gridX = x;
        gridY = y;
        show = s;
        color = c;
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
        if( i > 9 || i < 0)
            return false;
        
        gridX = i;
        
        return true;
    }
    
    public int getX()
    {
        return gridX;
    }
    
    public boolean setY(int i)
    {
        if( i > 21 || i < 0)
            return false;
        
        gridY = i;
        
        return true;
    }
    
    public int getY()
    {
        return gridY;
    }
    
    public ImageIcon getImageIcon()
    {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(color));
        return ii;
    }
    
    public Image getImage()
    {
        return getImageIcon().getImage();
    }
    
    
}
