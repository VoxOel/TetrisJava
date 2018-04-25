package tetris;

import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MenuButton extends JButton{
    
    private String titleString;
    private boolean display;
    private String displayString;
    public int scale;
    
    //TODO make singlebutton editable
    public MenuButton(String label)
    {
        titleString = label;
        display = false;
        displayString = " ";
        scale = 1;
        
        setDisplayString();
        
        disableOutline();
    }
    
    public MenuButton(String label, String disp)
    {
        titleString = label;
        display = true;
        displayString = disp;
        scale = 1;
        
        setDisplayString();
        
        disableOutline();
    }
    
    private void disableOutline()
    {
        setBorderPainted(false);
        setBorder(null);
        setContentAreaFilled(false);
        setMargin(new Insets(0,0,0,0));
        setFocusable(false);
    }
   
    public void setScale(int i)
    {
        if(i < 1)
            i = 1;
        scale = i;
    }
    
    public boolean setTitleAndDisplay(String t, String d)
    {
        titleString = t;
        displayString = d;
        return setDisplayString();
        
    }
    
    public boolean setTitle(String s)
    {
        titleString = s;
        return setDisplayString();
    }
    
    public boolean setDisplay(String s)
    {
        display = true;
        displayString = s;
        return setDisplayString();
    }
    
    public void removeDisplay()
    {
        display = false;
        setDisplayString();
    }
    
    public String getDisplay()
    {
        return displayString;
    }
    
    public String getTitle()
    {
        return titleString;
    }
    
    public void repaintButton()
    {
        setDisplayString();
        repaint();
    }
    
    private boolean setDisplayString()
    {
        String disp = "";
        if(display)
            disp = "Display";
        
        try {
            BufferedImage button = ImageIO.read(getClass().getResource(
                    "/tetris/textures/button" + disp + ".png"));
            BufferedImage buttonRollover = ImageIO.read(getClass().getResource(
                    "/tetris/textures/button" + disp + "Rollover.png"));
            BufferedImage buttonSelected = ImageIO.read(getClass().getResource(
                    "/tetris/textures/button" + disp + "Selected.png"));
            
            GameFont title = new GameFont(titleString);
            GameFont subtitle = new GameFont(displayString);
            
            Graphics2D g2d = button.createGraphics();
            g2d.drawImage(title.getImage(),
                    button.getWidth()/2 - title.getImage().getWidth()/2, 7,
                    title.getImage().getWidth(), title.getImage().getHeight(),
                    null);
            if(display)
                g2d.drawImage(subtitle.getImage(),
                    button.getWidth()/2 - subtitle.getImage().getWidth()/2, 19,
                    subtitle.getImage().getWidth(), subtitle.getImage().getHeight(),
                    null);
            
            
            g2d = buttonRollover.createGraphics();
            g2d.drawImage(title.getImage(),
                    button.getWidth()/2 - title.getImage().getWidth()/2, 7,
                    title.getImage().getWidth(), title.getImage().getHeight(),
                    null);
            if(display)
                g2d.drawImage(subtitle.getImage(),
                    button.getWidth()/2 - subtitle.getImage().getWidth()/2, 19,
                    subtitle.getImage().getWidth(), subtitle.getImage().getHeight(),
                    null);
            
            g2d = buttonSelected.createGraphics();
            g2d.drawImage(title.getImage(),
                    button.getWidth()/2 - title.getImage().getWidth()/2, 7,
                    title.getImage().getWidth(), title.getImage().getHeight(),
                    null);
            if(display)
                g2d.drawImage(subtitle.getImage(),
                    button.getWidth()/2 - subtitle.getImage().getWidth()/2, 19,
                    subtitle.getImage().getWidth(), subtitle.getImage().getHeight(),
                    null);
            
            BufferedImage scaled = new BufferedImage(
                    button.getWidth()*scale, 
                    button.getHeight()* scale,
                    BufferedImage.TYPE_INT_ARGB
            );
            BufferedImage scaledRollover = new BufferedImage(
                    button.getWidth()*scale, 
                    button.getHeight()* scale,
                    BufferedImage.TYPE_INT_ARGB
            );
            BufferedImage scaledSelected = new BufferedImage(
                    button.getWidth()*scale, 
                    button.getHeight()* scale,
                    BufferedImage.TYPE_INT_ARGB
            );
            
            g2d = scaled.createGraphics();
            g2d.drawImage(button, 0, 0,
                    scaled.getWidth(), scaled.getHeight(), null);
            
            g2d = scaledRollover.createGraphics();
            g2d.drawImage(buttonRollover, 0, 0,
                    scaledRollover.getWidth(), 
                    scaledRollover.getHeight(), null);
            
            g2d = scaledSelected.createGraphics();
            g2d.drawImage(buttonSelected, 0, 0,
                    scaledSelected.getWidth(), 
                    scaledSelected.getHeight(), null);
            
            
            
            setIcon( new ImageIcon(scaled));
            setRolloverIcon( new ImageIcon(scaledRollover));
            setPressedIcon( new ImageIcon(scaledSelected));
            
            g2d.dispose();
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
        
        return true;
    }
}
