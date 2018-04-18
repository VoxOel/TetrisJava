package tetris;

import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundHandler {
    private Clip sound;
    private boolean loop;
    
    public SoundHandler(String file)
    {
        try
        {
            sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(
                    getClass().getResource(file))
            );
        }
        catch(IOException | LineUnavailableException | UnsupportedAudioFileException e )
        {
            System.err.println("Error loading audio file: " + e.getMessage());
        }
        
        
    }
    
    public void start()
    {
        if(loop)
        {
            sound.loop(Clip.LOOP_CONTINUOUSLY);
        }
        else
        {
            sound.start();
        }
    }
    
    public void restart()
    {
        sound.setFramePosition(0);
        start();
    }
    
    public boolean setLoop(boolean b)
    {
        loop = b;
        
        return true;
    }
    
    public void stop()
    {
        sound.stop();
    }
}
