package tetris;

public abstract class Tetramino
{
	
    protected Chunk[] chunkArray;
    protected char type;
    protected int lowest;

    public Tetramino(char c)
    {
        chunkArray = new Chunk[4];
        lowest = 99999;
        setType(c);
    }

    protected void checkLowest()
    {
        for(Chunk c : chunkArray)
        {
            if(c.getY() < lowest)
                lowest = c.getY();
        }
    }
    
    public int getLowest()
    {
        return lowest;
    }

    public void up()
    {
        for(int i = 0; i < 4; i++)
        {
            chunkArray[i].setY(chunkArray[i].getY() + 1);
        }
    }
    
    public void down()
    {
        for(int i = 0; i < 4; i++)
        {
            chunkArray[i].setY(chunkArray[i].getY() - 1);
        }
        checkLowest();
    }

    public void left()
    {
        for(int i = 0; i < 4; i++)
        {
            chunkArray[i].setX(chunkArray[i].getX() - 1);
        }
    }

    public void right()
    {
        for(int i = 0; i < 4; i++)
        {
            chunkArray[i].setX(chunkArray[i].getX() + 1);
        }
    }

    public Chunk[] getChunkArray()
    {
        return chunkArray;
    }

    public char getType()
    {
        return type;
    }

    private boolean setType(char c)
    {
        switch(c)
        {
            case 'i':
            case 'o':
            case 't':
            case 's':
            case 'z':
            case 'j':
            case 'l': 
                type = c;
                return true;
            default :
                type = '0';
                return false;
        }
    }
    
    public abstract void rotateClockwise();
    public abstract void rotateCounterClockwise();
}

class TetraI extends Tetramino
{
    public TetraI()
    {
        super('i');
        for(int i = 0; i < 4; i++)
        {
            chunkArray[i] = new Chunk("cyan");
        }
        initPos();
    }

    private void initPos()
    {
        chunkArray[0].setX(3);  chunkArray[0].setY(20); 
        chunkArray[1].setX(4);  chunkArray[1].setY(20);
        chunkArray[2].setX(5);  chunkArray[2].setY(20);
        chunkArray[3].setX(6);  chunkArray[3].setY(20);
    }

    public void rotateClockwise() { }
    public void rotateCounterClockwise() { }
}

class TetraO extends Tetramino
{
    public TetraO()
    {
        super('o');
        for(int i = 0; i < 4; i++)
        {
            chunkArray[i] = new Chunk("yellow");
        }
        initPos();
    }

    private void initPos()
    {
        chunkArray[0].setX(4);  chunkArray[0].setY(21);
        chunkArray[1].setX(5);  chunkArray[1].setY(21);
        chunkArray[2].setX(4);  chunkArray[2].setY(20);
        chunkArray[3].setX(5);  chunkArray[3].setY(20);
    }

    public void rotateClockwise() { }
    public void rotateCounterClockwise() { }
}

class TetraT extends Tetramino
{
    public TetraT()
    {
        super('t');
        for(int i = 0; i < 4; i++)
        {
            chunkArray[i] = new Chunk("purple");
        }
        initPos();
    }

    private void initPos()
    {
        chunkArray[0].setX(4);  chunkArray[0].setY(21);
        chunkArray[1].setX(3);  chunkArray[1].setY(20);
        chunkArray[2].setX(4);  chunkArray[2].setY(20);
        chunkArray[3].setX(5);  chunkArray[3].setY(20);
    }

    public void rotateClockwise() { }
    public void rotateCounterClockwise() { }
}

class TetraS extends Tetramino
{
    public TetraS()
    {
        super('s');
        for(int i = 0; i < 4; i++)
        {
            chunkArray[i] = new Chunk("green");
        }
        initPos();
    }

    private void initPos()
    {
        chunkArray[0].setX(4);  chunkArray[0].setY(21);
        chunkArray[1].setX(5);  chunkArray[1].setY(21);
        chunkArray[2].setX(3);  chunkArray[2].setY(20);
        chunkArray[3].setX(4);  chunkArray[3].setY(20);
    }

    public void rotateClockwise() { }
    public void rotateCounterClockwise() { }
}

class TetraZ extends Tetramino
{
    public TetraZ()
    {
        super('z');
        for(int i = 0; i < 4; i++)
        {
            chunkArray[i] = new Chunk("red");
        }
        initPos();
    }

    private void initPos()
    {
        chunkArray[0].setX(3);  chunkArray[0].setY(21);
        chunkArray[1].setX(4);  chunkArray[1].setY(21);
        chunkArray[2].setX(4);  chunkArray[2].setY(20);
        chunkArray[3].setX(5);  chunkArray[3].setY(20);
    }

    public void rotateClockwise() { }
    public void rotateCounterClockwise() { }
}

class TetraJ extends Tetramino
{
    public TetraJ()
    {
        super('j');
        for(int i = 0; i < 4; i++)
        {
                chunkArray[i] = new Chunk("blue");
        }
        initPos();
    }

    private void initPos()
    {
        chunkArray[0].setX(3);  chunkArray[0].setY(21);
        chunkArray[1].setX(3);  chunkArray[1].setY(20);
        chunkArray[2].setX(4);  chunkArray[2].setY(20);
        chunkArray[3].setX(5);  chunkArray[3].setY(20);
    }
	
    public void rotateClockwise() { }
    public void rotateCounterClockwise() { }
}

class TetraL extends Tetramino
{
    public TetraL()
    {
        super('l');
        for(int i = 0; i < 4; i++)
        {
            chunkArray[i] = new Chunk("orange");
        }
        initPos();
    }

    private void initPos()
    {
        chunkArray[0].setX(5);  chunkArray[0].setY(21);
        chunkArray[1].setX(3);  chunkArray[1].setY(20);
        chunkArray[2].setX(4);  chunkArray[2].setY(20);
        chunkArray[3].setX(5);  chunkArray[3].setY(20);
    }

    public void rotateClockwise() { }
    public void rotateCounterClockwise() { }
}
