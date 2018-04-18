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
        
        
    }
    
    public char getHold()
    {
        return hold;
    }

    public char swap(char c) {
        if(hold != '0') {
            char ret = hold;
            hold = c;
            return ret;
        }
        else {
            hold = c;
            return '0';
        }
    }
	
}
