package tetris;

public class HoldBox {
	
	private char hold;
	
	public HoldBox() {
		hold = '0';
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
