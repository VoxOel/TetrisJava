package tetris;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MenuScreen extends JPanel implements KeyListener{

    private MenuButton highscores;
    private MenuButton gamemode;
    private MenuButton startingLevel;
    private MenuButton playSettings;
    private MenuButton start;
    private Tetris tetris;
    private Timer scaleTimer;
    
    public MenuScreen(Tetris t)
    {
        super(new GridBagLayout());
        
        tetris = t;
        highscores = new MenuButton("highscores");
        gamemode = new MenuButton("game mode", "marathon");
        startingLevel = new MenuButton("start level", "01");
        playSettings = new MenuButton("play settings");
        start = new MenuButton("start!");
        
        GridBagConstraints constr = new GridBagConstraints();

        constr.fill = GridBagConstraints.NONE;
        constr.weightx = 0.5; 
        constr.gridheight = 1;
        
        constr.gridx = 1;
        constr.gridy = 0;
        constr.weighty = 0.5;
        add(highscores,constr);
        
        constr.gridx = 1;
        constr.gridy = 1;
        constr.weighty = 0.5;
        add(gamemode,constr);
        
        constr.gridx = 1;
        constr.gridy = 2;
        constr.weighty = 0.5;
        add(startingLevel,constr);
        
        constr.gridx = 1;
        constr.gridy = 3;
        constr.weighty = 0.5;
        add(playSettings,constr);
        
        constr.gridx = 1;
        constr.gridy = 4;
        constr.weighty = 0.5;
        add(start,constr);
        
        scaleTimer = new Timer(0, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae) {
                repaintButtons();
            }
            
        });
        
        scaleTimer.setDelay(500);
        scaleTimer.start();
        
        highscores.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                
            }
            
        });
        
        gamemode.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                
            }
            
        });
        
        startingLevel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                
            }
            
        });
        
        playSettings.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                
            }
            
        });
        
        start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                tetris.initGame();
            }
            
        });
    }
    
    public void stopScaling()
    {
        scaleTimer.stop();
    }
    
    public void restartScaling()
    {
        scaleTimer.restart();
    }
    
    public void repaintButtons()
    {
        int scale = 1;
        while(68*(scale+1) <= (tetris.getWidth()*2)/7)
            scale++;
        
        highscores.setScale(scale);
        gamemode.setScale(scale);
        startingLevel.setScale(scale);
        playSettings.setScale(scale);
        start.setScale(scale);
        
        highscores.repaintButton();
        gamemode.repaintButton();
        startingLevel.repaintButton();
        playSettings.repaintButton();
        start.repaintButton();
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {}

    @Override
    public void keyReleased(KeyEvent ke) {}
    
}
