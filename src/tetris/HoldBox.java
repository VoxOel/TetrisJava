package tetris;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class HoldBox extends JPanel{
	
    private char hold;

    public HoldBox() {
        hold = '0';
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        ImageIcon holdBox = new ImageIcon(
                getClass().getResource("/tetris/textures/nextBox.png"));
        int nativeWidth = holdBox.getImage().getWidth(null);
        int nativeHeight = holdBox.getImage().getHeight(null);
        
        int scale = 1;
        while(nativeWidth*(scale+1) <= getWidth() && 
                nativeHeight*(scale+1) <= getHeight())
            scale++;
        
        int drawHeight = nativeHeight * scale;
        int drawWidth = nativeWidth * scale;
        
        int drawX = getWidth() - drawWidth;
        int drawY = getHeight()/10;
        
        g2d.drawImage(holdBox.getImage(),
                drawX, drawY, drawWidth, drawHeight, null);
        
        String color;
        switch(hold)
        {
            case 'i': color = "cyan"; break;
            case 'o': color =  "yellow"; break;
            case 't': color =  "purple"; break;
            case 's': color =  "green"; break;
            case 'z': color =  "red"; break;
            case 'j': color =  "blue"; break;
            case 'l': color =  "orange"; break;
            default : color =  "0"; break;
        }
        
        if(!color.equals("0"))
        {
            ImageIcon tetra = new ImageIcon(
                getClass().getResource("/tetris/textures/" + 
                        color + "Tetra.png"));
            
            g2d.drawImage(tetra.getImage(),
                drawX, drawY, drawWidth, drawHeight, null);
        }
        
        GameFont gf = new GameFont("hold");
        drawX = 
        
        //g2d.drawImage(gf.getImage(), , 0,  null);
        
        
        
    }
    
    public char getHold()
    {
        return hold;
    }

    public char swap(char c) {
        char ret = hold;
        if(hold != '0') {
            hold = c;
            
        }
        else 
        {
            hold = c;
            ret = '0';
        }
        repaint();
        return ret;
    }
	
}
