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

    public boolean setHold(char c)
    {
        switch(c) {
            case 'i':
            case 'o':
            case 't':
            case 's':
            case 'z':
            case 'l':
            case 'j':
                hold = c;
                return true;
            default:
                hold = '0';
                return false;
        }
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
