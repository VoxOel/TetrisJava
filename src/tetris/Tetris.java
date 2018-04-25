package tetris;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.Timer;


public class Tetris extends JFrame implements KeyListener{
    
    private TitleScreen title;
    private boolean titleShow;
    
    private MenuScreen menu;
    private boolean menuShow;
    
    private SettingsScreen settings;
    private boolean settingsShow;
    
    private GameManager game;
    private boolean gameRunning;
    private boolean gameShow;
    
    public GameOptions options;
    public KeyBinding bindings;
    
    
    public Tetris()
    {
        super("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(800, 600);
        
        options = new GameOptions();
        bindings = new KeyBinding();
        
        title = new TitleScreen();
        menu = new MenuScreen(this);
        settings = new SettingsScreen(this,options);
        
        
    }
    
    public boolean setGM(GameManager gm)
    {
        removeKeyListener(game);
        game = gm;
        add(game);
        addKeyListener(game);
        return true;
    }
    
    public boolean startGame()
    {
        game.run();
        gameShow = true;
        setVisible(true);
        gameRunning = true;
        return true;
    }  
    
    public void resumeGame()
    {
        game.unpause();
        add(game);
        setVisible(true);
        repaint();
        
        //TODO countdown timer
        
        Timer delay = new Timer(2950, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                addKeyListener(game);
            }
            
        });
        
        delay.setRepeats(false);
        delay.start();
        
        gameShow = true;
    }
    
    public void pauseGame()
    {
        game.pause();
        remove(game);
        removeKeyListener(game);
        gameShow = false;
        showSettings();
    }
    
    public void showTitle()
    {
        add(title);
        setVisible(true);
        titleShow = true;
    }
    
    public void hideTitle()
    {
        remove(title);
        titleShow = false;
    }
    
    public void toggleHold()
    {
        game.toggleHoldVis();
    }
    
    public void setQueue(int i)
    {
        game.setQueueShow(i);
    }
    
    public void initGame()
    {
        hideMenu();
        setGM(new GameManager(options, bindings) );
        startGame();
    }
    
    public void showMenu()
    {
        add(menu);
        menu.restartScaling();
        setVisible(true);
        repaint();
        menuShow = true;
    }
    
    public void hideMenu()
    {
        remove(menu);
        menu.stopScaling();
        menuShow = false;
    }
    
    public void showSettings()
    {
        add(settings);
        settings.restartScaling();
        setVisible(true);
        repaint();
        settingsShow = true;
    }
    
    public void hideSettings()
    {
        remove(settings);
        settings.stopScaling();
        settingsShow = false;
    }
    
    public void closeSettings()
    {
        hideSettings();
        if(gameRunning)
            resumeGame();
        else
            showMenu();
    }
    
    public static void main(String args[]){
        Tetris tetris = new Tetris();
        
        tetris.showTitle();
        tetris.addKeyListener(tetris);
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) 
    {
        if(titleShow)
        {
            hideTitle();
            showMenu();
        }
        else if(ke.getKeyCode() == bindings.pause)
        {
            if(gameShow)
            {
                pauseGame();
                showSettings();
            }
            else
            {
                closeSettings();
                resumeGame();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {}

}
