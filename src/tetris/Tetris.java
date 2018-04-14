package tetris;

import javax.swing.JFrame;


public class Tetris{
    
    public static void main(String args[]){
        JFrame frame = new JFrame( "Tetris" );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GameManager gm = new GameManager();
        frame.add( gm );
        frame.setSize(800, 600);
        frame.setVisible(true);
        
        frame.addKeyListener(gm);
        
        gm.run();
    }

}
