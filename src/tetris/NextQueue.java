package tetris;

import java.util.LinkedList;
import java.util.Random;
import java.util.Queue;

public class NextQueue {

        private boolean[] bag;
        private Queue<Character> queue;
    
	public NextQueue() {
            bag = new boolean[7];
            for(boolean b : bag) {
                b = true;
            }
            
            queue = new LinkedList<>();
            for(int i = 0; i < 7; i++) {
                queue.offer(grab());
            }
	}
        
        public char pullTetra() {
            char ret;
            
            ret = queue.remove();
            queue.offer(grab());
            
            return ret;
        }
        
        private char grab() {
            boolean empty = true;
            for(boolean b : bag) {
                if(b) {
                    empty = false;
                }
            }
            if(empty){
                for(boolean b: bag) {
                    b = true;
                }
            }
            Random rand = new Random();
            
            while(true) {
                int something = rand.nextInt(7);
                if(bag[something]) {
                    bag[something] = false;
                    switch(something) {
                        case 0: return 'i';
                        case 1: return 'o';
                        case 2: return 't';
                        case 3: return 's';
                        case 4: return 'z';
                        case 5: return 'j';
                        case 6: return 'l';
                    }
                }
            }
            
            
        }
	// shows next 6 tetraminos
	// generate new pieces
	// new piece cannot have more than 4 TOTAL 'S' or 'Z' pieces in a row

}
