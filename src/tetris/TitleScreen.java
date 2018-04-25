package tetris;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TitleScreen extends JPanel {

    private BufferedImage title;
    private Timer blinkTimer;
    private boolean blink;
    
    public TitleScreen()
    {
        try {
            title = ImageIO.read(getClass().getResource(
                    "/tetris/textures/title.png"));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
        blink = true;
        
        blinkTimer = new Timer(1200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                blink = !blink;
                repaint();
                if(!blink)
                    blinkTimer.restart();
            }
        });
        
        blinkTimer.setInitialDelay(500);
        blinkTimer.start();
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        int nativeHeight = title.getHeight();
        int nativeWidth = title.getWidth();
        
        int scale = 1;
        while(nativeWidth*(scale+1) <= getWidth() && 
                nativeHeight*(scale+1) <= getHeight())
            scale++;
        
        int drawHeight = nativeHeight * scale;
        int drawWidth = nativeWidth * scale;
        
        int drawX = getWidth()/2 - drawWidth/2;
        int drawY = getHeight()/2 - drawHeight/2;
        
        g2d.drawImage(title,
                    drawX, drawY, drawWidth, drawHeight, null);
        
        if(blink)
        {
            GameFont f = new GameFont("press any key!");
            
            drawY += (drawHeight*161)/185;
            
            drawHeight = f.getImage().getHeight() * scale;
            drawWidth = f.getImage().getWidth() * scale;

            drawX = getWidth()/2 - drawWidth/2;
            

            g2d.drawImage(f.getImage(),
                    drawX, drawY, drawWidth, drawHeight, null);
        }
        
    }
}
