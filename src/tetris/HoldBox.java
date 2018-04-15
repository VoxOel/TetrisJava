package tetris;

import javax.swing.JPanel;

public class HoldBox extends JPanel{
	
    private char hold;

    public HoldBox() {
        hold = '0';
    }

    public char getHold()
    {
        return hold;
    }

    public char swap(char c) {
        if(hold != '0') {
            char ret = hold;
            hold = c;
            return ret;
        }
        else {
            hold = c;
            return '0';
        }
    }
	
}
