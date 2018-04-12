package tetris;

public abstract class Tetramino
{
	
    protected Chunk[] chunkArray;
    protected char type;
    protected int lowest;

    public Tetramino()
    {
        chunkArray = new Chunk[4];
        lowest = 99999;
    }

    /*
     * class is abstract... is there another way?
    public Tetramino(char c)
    {
        chunkArray = new Chunk[4];
        lowest = 99999;
        setType(c); // overridable method call?
    }
     */

    public abstract void rotateClockwise();
    public abstract void rotateCounterClockwise();

    protected void checkLowest()
    {
        for(Chunk c : chunkArray)
        {
            if(c.getY() < lowest)
                lowest = c.getY();
        }
    }

    public void fall()
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

    public boolean setType(char c)
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
}

class TetraI extends Tetramino
{
    public TetraI()
    {
        super();
        for(int i = 0; i < 4; i++)
        {
            chunkArray[i] = new Chunk("cyan");
        }
        initPos();
    }

    private void initPos()
    {
        chunkArray[0].setX(3);
        chunkArray[0].setY(1);

        chunkArray[1].setX(4);
        chunkArray[1].setY(1);

        chunkArray[2].setX(5);
        chunkArray[2].setY(1);

        chunkArray[3].setX(6);
        chunkArray[3].setY(1);
    }

    public void rotateClockwise() { }
    public void rotateCounterClockwise() { }
}

class TetraO extends Tetramino
{
    public TetraO()
    {
        super();
        for(int i = 0; i < 4; i++)
        {
            chunkArray[i] = new Chunk("yellow");
        }
        initPos();
    }

    private void initPos()
    {
        chunkArray[0].setX(4);
        chunkArray[0].setY(0);

        chunkArray[1].setX(5);
        chunkArray[1].setY(0);

        chunkArray[2].setX(4);
        chunkArray[2].setY(1);

        chunkArray[3].setX(5);
        chunkArray[3].setY(1);
    }

    public void rotateClockwise() { }
    public void rotateCounterClockwise() { }
}

class TetraT extends Tetramino
{
    public TetraT()
    {
        super();
        for(int i = 0; i < 4; i++)
        {
            chunkArray[i] = new Chunk("purple");
        }
        initPos();
    }

    private void initPos()
    {
        chunkArray[0].setX(4);
        chunkArray[0].setY(0);

        chunkArray[1].setX(3);
        chunkArray[1].setY(1);

        chunkArray[2].setX(4);
        chunkArray[2].setY(1);

        chunkArray[3].setX(5);
        chunkArray[3].setY(1);
    }

    public void rotateClockwise() { }
    public void rotateCounterClockwise() { }
}

class TetraS extends Tetramino
{
    public TetraS()
    {
        super();
        for(int i = 0; i < 4; i++)
        {
            chunkArray[i] = new Chunk("green");
        }
        initPos();
    }

    private void initPos()
    {
        chunkArray[0].setX(4);
        chunkArray[0].setY(0);

        chunkArray[1].setX(5);
        chunkArray[1].setY(0);

        chunkArray[2].setX(3);
        chunkArray[2].setY(1);

        chunkArray[3].setX(4);
        chunkArray[3].setY(1);
    }

    public void rotateClockwise() { }
    public void rotateCounterClockwise() { }
}

class TetraZ extends Tetramino
{
    public TetraZ()
    {
        super();
        for(int i = 0; i < 4; i++)
        {
            chunkArray[i] = new Chunk("red");
        }
        initPos();
    }

    private void initPos()
    {
        chunkArray[0].setX(3);
        chunkArray[0].setY(0);

        chunkArray[1].setX(4);
        chunkArray[1].setY(0);

        chunkArray[2].setX(4);
        chunkArray[2].setY(1);

        chunkArray[3].setX(5);
        chunkArray[3].setY(1);
    }

    public void rotateClockwise() { }
    public void rotateCounterClockwise() { }
}

class TetraJ extends Tetramino
{
    public TetraJ()
    {
        super();
        for(int i = 0; i < 4; i++)
        {
                chunkArray[i] = new Chunk("blue");
        }
        initPos();
    }

    private void initPos()
    {
        chunkArray[0].setX(3);
        chunkArray[0].setY(0);

        chunkArray[1].setX(3);
        chunkArray[1].setY(1);

        chunkArray[2].setX(4);
        chunkArray[2].setY(1);

        chunkArray[3].setX(5);
        chunkArray[3].setY(1);
    }
	
    public void rotateClockwise() { }
    public void rotateCounterClockwise() { }
}

class TetraL extends Tetramino
{
    public TetraL()
    {
        super();
        for(int i = 0; i < 4; i++)
        {
            chunkArray[i] = new Chunk("orange");
        }
        initPos();
    }

    private void initPos()
    {
        chunkArray[0].setX(5);
        chunkArray[0].setY(0);

        chunkArray[1].setX(3);
        chunkArray[1].setY(1);

        chunkArray[2].setX(4);
        chunkArray[2].setY(1);

        chunkArray[3].setX(5);
        chunkArray[3].setY(1);
    }

    public void rotateClockwise() { }
    public void rotateCounterClockwise() { }
}
