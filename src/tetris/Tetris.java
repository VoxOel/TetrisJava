package tetris;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;


public class Tetris extends JFrame implements KeyListener{
    
    private TitleScreen title;
    private boolean titleShow;
    
    private MenuScreen menu;
    private boolean menuShow;
    
    private GameManager game;
    
    private GameOptions settings;
    private KeyBinding bindings;
    
    
    public Tetris()
    {
        super("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(800, 600);
        
        title = new TitleScreen();
        menu = new MenuScreen(this);
        
        settings = new GameOptions();
        bindings = new KeyBinding();
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
        setVisible(true);
        return true;
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
    
    public void initGame()
    {
        hideMenu();
        setGM( new GameManager(settings, bindings) );
        startGame();
    }
    
    public void showMenu()
    {
        add(menu);
        setVisible(true);
        menuShow = true;
        
    }
    
    public void hideMenu()
    {
        remove(menu);
        menu.stopScaling();
        menuShow = false;
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
    }

    @Override
    public void keyReleased(KeyEvent ke) {}

}
