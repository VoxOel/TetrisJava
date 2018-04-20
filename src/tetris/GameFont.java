package tetris;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GameFont{
    String string;
    BufferedImage image;
    
    public GameFont()
    {
        this("");
    }
    
    public GameFont(String s)
    {
        setString(s);
    }
    
    @Override
    public String toString()
    {
        return string;
    }
    
    public boolean setString(String s)
    {
        string = s;
        
        try {
            createImage();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
        
        return true;
    }
    
    public BufferedImage getImage()
    {
        return image;
    }
    
    private void createImage() throws IOException
    {
        int imWidth = 0;
        int imHeight = 0;
        
        String lookUp;
        BufferedImage letter;
        for(int i = 0; i < string.length(); i++)
        {
            char c = string.charAt(i);
            if(!Character.isLetter(c) && !Character.isDigit(c) &&
                    c != '?' && c != '!' && c != '.' && 
                    c != ' ' && c != ':')
            {
                c = ' ';
            }
            
            c = Character.toLowerCase(c);
            
            switch(c)
            {
                case '?': lookUp = "question";      break;
                case '!': lookUp = "exclamation";   break;
                case ' ': lookUp = "space";         break;
                case ':': lookUp = "colon";         break;
                case '.': lookUp = "period";        break;
                default : lookUp = "" + c;
            }
            letter = ImageIO.read(getClass().getResource(
                    "/tetris/textures/font/" + lookUp + ".png"));
            
            imWidth += letter.getWidth();
            if(letter.getHeight() >  imHeight)
                imHeight = letter.getHeight(); 
        }
        
        image = new BufferedImage(imWidth, imHeight, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2d = image.createGraphics();
        int drawX = 0;
        for(int i = 0; i < string.length(); i++)
        {
            char c = string.charAt(i);
            if(!Character.isLetter(c) && !Character.isDigit(c) &&
                    c != '?' && c != '!' && c != '.' && 
                    c != ' ' && c != ':')
            {
                c = ' ';
            }
            
            switch(c)
            {
                case '?': lookUp = "question";      break;
                case '!': lookUp = "exclamation";   break;
                case ' ': lookUp = "space";         break;
                case ':': lookUp = "colon";         break;
                case '.': lookUp = "period";        break;
                default : lookUp = "" + c;
            }
            letter = ImageIO.read(getClass().getResource(
                    "/tetris/textures/font/" + lookUp + ".png"));
            
            g2d.drawImage(letter, drawX, 
                    image.getHeight() - letter.getHeight(), null);
            
            drawX += letter.getWidth();
        }
        
        
    }
}