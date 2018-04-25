package tetris;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SettingsScreen extends JPanel{
    private MenuButton ghost, placement, queueShow, holdShow, controls, back;
    private MenuButton[] buttons;
    private Tetris tetris;
    private Timer scaleTimer;
    
    public SettingsScreen(Tetris t)
    {
        super(new GridBagLayout());
        
        tetris = t;
        
        ghost = new MenuButton("show ghost", "on");
        holdShow = new MenuButton("show hold", "on");
        queueShow = new MenuButton("show next", "6");
        placement = new MenuButton("placement type", "extended");
        controls = new MenuButton("controls");
        back = new MenuButton("back");
        
        buttons = new MenuButton[6];
        buttons[0] = ghost;
        buttons[1] = holdShow;
        buttons[2] = queueShow;
        buttons[3] = placement;
        buttons[4] = controls;
        buttons[5] = back;
        
        GridBagConstraints constr = new GridBagConstraints();

        constr.fill = GridBagConstraints.NONE;
        constr.weightx = 0.5; 
        constr.weighty = 0.5;
        
        for(int i = 0; i < 6; i++)
        {
            constr.gridx = i % 2;
            constr.gridy = i/2;
            add(buttons[i],constr);
        }
        
        ghost.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                
            }
            
        });
        
        holdShow.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                
            }
            
        });
        
        queueShow.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                
            }
            
        });
        
        placement.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                
            }
            
        });
        
        controls.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                
            }
            
        });
        
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                tetris.closeSettings();
            }
            
        });
        
        scaleTimer = new Timer(0, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae) {
                repaintButtons();
            }
            
        });
        
        scaleTimer.setDelay(500);
        scaleTimer.start();
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
        while(68*(scale+1) <= tetris.getWidth()/3)
            scale++;
        
        for(int i = 0; i < 6; i++)
        {
            buttons[i].setScale(scale);
            buttons[i].repaintButton();
        }
    }
}
