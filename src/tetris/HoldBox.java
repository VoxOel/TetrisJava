package tetris;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class HoldBox extends JPanel{
	
    private char hold;
    private boolean show;

    public HoldBox(boolean vis) {
        hold = '0';
        show = vis;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        if (show) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            ImageIcon holdBox = new ImageIcon(
                    getClass().getResource("/tetris/textures/nextBox.png"));
            int nativeWidth = holdBox.getImage().getWidth(null);
            int nativeHeight = holdBox.getImage().getHeight(null);
            
            int scale = 1;
            while (nativeWidth * (scale + 1) <= getWidth()
                    && nativeHeight * (scale + 1) <= getHeight()) {
                scale++;
            }
            
            int drawHeight = nativeHeight * scale;
            int drawWidth = nativeWidth * scale;
            
            int drawX = getWidth() - drawWidth;
            int drawY = getHeight() / 20;
            
            g2d.drawImage(holdBox.getImage(),
                    drawX, drawY, drawWidth, drawHeight, null);
            
            String color;
            switch (hold) {
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
                    color = "0";
                    break;
            }
            
            if (!color.equals("0")) {
                ImageIcon tetra = new ImageIcon(
                        getClass().getResource("/tetris/textures/"
                                + color + "Tetra.png"));
                
                g2d.drawImage(tetra.getImage(),
                        drawX, drawY, drawWidth, drawHeight, null);
            }
            
            GameFont gf = new GameFont("hold");
            
            int fontDrawWidth = gf.getImage().getWidth() * scale;
            int fontDrawHeight = gf.getImage().getHeight() * scale;
            
            drawX = drawX + drawWidth / 2 - fontDrawWidth / 2;
            drawY = drawY + drawHeight - drawHeight / 10 - fontDrawHeight;
            
            g2d.drawImage(gf.getImage(), drawX, drawY,
                    fontDrawWidth, fontDrawHeight, null);
        }
        
    }
    
    public void toggleVis()
    {
        show = !show;
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
