package tetris;

import java.util.LinkedList;
import java.util.Random;
import java.util.Queue;

public class NextQueue {

    private boolean[] bag;
    private Queue<Character> queue;
    private int showNum;

    public NextQueue()
    {
        this(6);
    }

    public NextQueue(int show) {
        showNum = show;

        bag = new boolean[7];
        for(int i = 0; i < 7; i++)
        {
            bag[i] = true;
        }

        queue = new LinkedList<>();
        for(int i = 0; i < 7; i++) 
        {
            queue.offer(grab());
        }
    }

    public char pullTetra() //returns next char in queue
    {
        char ret;

        ret = queue.remove();
        queue.offer(grab());

        return ret;
    }

    private char grab()     //randomly generates char for Tetramino
    {                       //based on Tetris generation rules
        boolean empty = true;

        for(boolean b : bag)    //checking to see if grab-bag is empty
        {          
            if(b)
            {
                empty = false;
            }
        }

        if(empty)
        {
            for(int i = 0; i < 7; i++) //filling empty grab-bag
            {       
                bag[i] = true;
            }
        }

        Random rand = new Random();

        while(true) {                           //pulls from grab-bag, and sets value as pulled
            int num = rand.nextInt(7);    //uses frequesncy array to represent grab-bag
            if(bag[num]) {
                bag[num] = false;
                switch(num) {             //convert pull to Tetra char
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

}
