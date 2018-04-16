package tetris;

public abstract class Tetramino
{
	
    protected Chunk[] chunkArray;
    protected char type;
    protected int lowest;
    protected char face;

    public Tetramino(char c)
    {
        chunkArray = new Chunk[4];
        lowest = 99999;
        face = 'n';
        setType(c);
    }
    
    public Tetramino(Tetramino t)
    {
        chunkArray = new Chunk[4];
        for(int i = 0; i < 4; i++)
            chunkArray[i] = new Chunk(t.chunkArray[i]);
        face = t.face;
        setType(t.type);
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
    
    public char getFace()
    {
        return face;
    }

    private boolean setType(char c)
    {
        switch(c)
        {
            case 'i':   case 'o':   case 't':
            case 's':   case 'z':   case 'j':
            case 'l': 
                type = c;
                return true;
            default :
                type = '0';
                return false;
        }
    }
    
    public abstract void rotateClockwise();
    
    public void rotateCounterClockwise()
    {
        for(int i = 0; i < 3; i++)
            rotateClockwise();
    }
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
    
    public TetraI(TetraI t)
    {
        super(t);
    }

    private void initPos()
    {
        chunkArray[0].setX(3);  chunkArray[0].setY(20); 
        chunkArray[1].setX(4);  chunkArray[1].setY(20);
        chunkArray[2].setX(5);  chunkArray[2].setY(20);
        chunkArray[3].setX(6);  chunkArray[3].setY(20);
    }

    @Override
    public void rotateClockwise()
    {
        switch(face)
        {
            case 'n':
                chunkArray[0].move(chunkArray[0].getX() + 2, chunkArray[0].getY() + 1);
                chunkArray[1].move(chunkArray[1].getX() + 1, chunkArray[1].getY() + 0);
                chunkArray[2].move(chunkArray[2].getX() + 0, chunkArray[2].getY() - 1);
                chunkArray[3].move(chunkArray[3].getX() - 1, chunkArray[3].getY() - 2);
                face = 'e';
                break;
            case 'e':
                chunkArray[0].move(chunkArray[0].getX() + 1, chunkArray[0].getY() - 2);
                chunkArray[1].move(chunkArray[1].getX() + 0, chunkArray[1].getY() - 1);
                chunkArray[2].move(chunkArray[2].getX() - 1, chunkArray[2].getY() + 0);
                chunkArray[3].move(chunkArray[3].getX() - 2, chunkArray[3].getY() + 1);
                face = 's';
                break;
            case 's':
                chunkArray[0].move(chunkArray[0].getX() - 2, chunkArray[0].getY() - 1);
                chunkArray[1].move(chunkArray[1].getX() - 1, chunkArray[1].getY() + 0);
                chunkArray[2].move(chunkArray[2].getX() + 0, chunkArray[2].getY() + 1);
                chunkArray[3].move(chunkArray[3].getX() + 1, chunkArray[3].getY() + 2);
                face = 'w';
                break;
            case 'w':
                chunkArray[0].move(chunkArray[0].getX() - 1, chunkArray[0].getY() + 2);
                chunkArray[1].move(chunkArray[1].getX() + 0, chunkArray[1].getY() + 1);
                chunkArray[2].move(chunkArray[2].getX() + 1, chunkArray[2].getY() + 0);
                chunkArray[3].move(chunkArray[3].getX() + 2, chunkArray[3].getY() - 1);
                face = 'n';
                break;
        }
    }
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
    
    public TetraO(TetraO t)
    {
        super(t);
    }

    private void initPos()
    {
        chunkArray[0].setX(4);  chunkArray[0].setY(21);
        chunkArray[1].setX(5);  chunkArray[1].setY(21);
        chunkArray[2].setX(4);  chunkArray[2].setY(20);
        chunkArray[3].setX(5);  chunkArray[3].setY(20);
    }

    @Override
    public void rotateClockwise() { }
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
    
    public TetraT(TetraT t)
    {
        super(t);
    }

    private void initPos()
    {
        chunkArray[0].setX(4);  chunkArray[0].setY(21);
        chunkArray[1].setX(3);  chunkArray[1].setY(20);
        chunkArray[2].setX(4);  chunkArray[2].setY(20);
        chunkArray[3].setX(5);  chunkArray[3].setY(20);
    }

    @Override
    public void rotateClockwise()
    {
        switch(face)
        {
            case 'n':
                chunkArray[0].move(chunkArray[0].getX() + 1, chunkArray[0].getY() - 1);
                chunkArray[1].move(chunkArray[1].getX() + 1, chunkArray[1].getY() + 1);
                chunkArray[2].move(chunkArray[2].getX() + 0, chunkArray[2].getY() + 0);
                chunkArray[3].move(chunkArray[3].getX() - 1, chunkArray[3].getY() - 1);
                face = 'e';
                break;
            case 'e':
                chunkArray[0].move(chunkArray[0].getX() - 1, chunkArray[0].getY() - 1);
                chunkArray[1].move(chunkArray[1].getX() + 1, chunkArray[1].getY() - 1);
                chunkArray[2].move(chunkArray[2].getX() + 0, chunkArray[2].getY() + 0);
                chunkArray[3].move(chunkArray[3].getX() - 1, chunkArray[3].getY() + 1);
                face = 's';
                break;
            case 's':
                chunkArray[0].move(chunkArray[0].getX() - 1, chunkArray[0].getY() + 1);
                chunkArray[1].move(chunkArray[1].getX() - 1, chunkArray[1].getY() - 1);
                chunkArray[2].move(chunkArray[2].getX() + 0, chunkArray[2].getY() + 0);
                chunkArray[3].move(chunkArray[3].getX() + 1, chunkArray[3].getY() + 1);
                face = 'w';
                break;
            case 'w':
                chunkArray[0].move(chunkArray[0].getX() + 1, chunkArray[0].getY() + 1);
                chunkArray[1].move(chunkArray[1].getX() - 1, chunkArray[1].getY() + 1);
                chunkArray[2].move(chunkArray[2].getX() + 0, chunkArray[2].getY() + 0);
                chunkArray[3].move(chunkArray[3].getX() + 1, chunkArray[3].getY() - 1);
                face = 'n';
                break;
        }
    }
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
    
    public TetraS(TetraS t)
    {
        super(t);
    }

    private void initPos()
    {
        chunkArray[0].setX(4);  chunkArray[0].setY(21);
        chunkArray[1].setX(5);  chunkArray[1].setY(21);
        chunkArray[2].setX(3);  chunkArray[2].setY(20);
        chunkArray[3].setX(4);  chunkArray[3].setY(20);
    }

    @Override
    public void rotateClockwise()
    {
        switch(face)
        {
            case 'n':
                chunkArray[0].move(chunkArray[0].getX() + 1, chunkArray[0].getY() - 1);
                chunkArray[1].move(chunkArray[1].getX() + 0, chunkArray[1].getY() - 2);
                chunkArray[2].move(chunkArray[2].getX() + 1, chunkArray[2].getY() + 1);
                chunkArray[3].move(chunkArray[3].getX() + 0, chunkArray[3].getY() + 0);
                face = 'e';
                break;
            case 'e':
                chunkArray[0].move(chunkArray[0].getX() - 1, chunkArray[0].getY() - 1);
                chunkArray[1].move(chunkArray[1].getX() - 2, chunkArray[1].getY() + 0);
                chunkArray[2].move(chunkArray[2].getX() + 1, chunkArray[2].getY() - 1);
                chunkArray[3].move(chunkArray[3].getX() + 0, chunkArray[3].getY() + 0);
                face = 's';
                break;
            case 's':
                chunkArray[0].move(chunkArray[0].getX() - 1, chunkArray[0].getY() + 1);
                chunkArray[1].move(chunkArray[1].getX() + 0, chunkArray[1].getY() + 2);
                chunkArray[2].move(chunkArray[2].getX() - 1, chunkArray[2].getY() - 1);
                chunkArray[3].move(chunkArray[3].getX() + 0, chunkArray[3].getY() + 0);
                face = 'w';
                break;
            case 'w':
                chunkArray[0].move(chunkArray[0].getX() + 1, chunkArray[0].getY() + 1);
                chunkArray[1].move(chunkArray[1].getX() + 2, chunkArray[1].getY() + 0);
                chunkArray[2].move(chunkArray[2].getX() - 1, chunkArray[2].getY() + 1);
                chunkArray[3].move(chunkArray[3].getX() + 0, chunkArray[3].getY() + 0);
                face = 'n';
                break;
        }
    }
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
    
    public TetraZ(TetraZ t)
    {
        super(t);
    }

    private void initPos()
    {
        chunkArray[0].setX(3);  chunkArray[0].setY(21);
        chunkArray[1].setX(4);  chunkArray[1].setY(21);
        chunkArray[2].setX(4);  chunkArray[2].setY(20);
        chunkArray[3].setX(5);  chunkArray[3].setY(20);
    }

    @Override
    public void rotateClockwise()
    {
        switch(face)
        {
            case 'n':
                chunkArray[0].move(chunkArray[0].getX() + 2, chunkArray[0].getY() + 0);
                chunkArray[1].move(chunkArray[1].getX() + 1, chunkArray[1].getY() - 1);
                chunkArray[2].move(chunkArray[2].getX() + 0, chunkArray[2].getY() + 0);
                chunkArray[3].move(chunkArray[3].getX() - 1, chunkArray[3].getY() - 1);
                face = 'e';
                break;
            case 'e':
                chunkArray[0].move(chunkArray[0].getX() + 0, chunkArray[0].getY() - 2);
                chunkArray[1].move(chunkArray[1].getX() - 1, chunkArray[1].getY() - 1);
                chunkArray[2].move(chunkArray[2].getX() + 0, chunkArray[2].getY() + 0);
                chunkArray[3].move(chunkArray[3].getX() - 1, chunkArray[3].getY() + 1);
                face = 's';
                break;
            case 's':
                chunkArray[0].move(chunkArray[0].getX() - 2, chunkArray[0].getY() + 0);
                chunkArray[1].move(chunkArray[1].getX() - 1, chunkArray[1].getY() + 1);
                chunkArray[2].move(chunkArray[2].getX() + 0, chunkArray[2].getY() + 0);
                chunkArray[3].move(chunkArray[3].getX() + 1, chunkArray[3].getY() + 1);
                face = 'w';
                break;
            case 'w':
                chunkArray[0].move(chunkArray[0].getX() + 0, chunkArray[0].getY() + 2);
                chunkArray[1].move(chunkArray[1].getX() + 1, chunkArray[1].getY() + 1);
                chunkArray[2].move(chunkArray[2].getX() + 0, chunkArray[2].getY() + 0);
                chunkArray[3].move(chunkArray[3].getX() + 1, chunkArray[3].getY() - 1);
                face = 'n';
                break;
        }
    }
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
    
    public TetraJ(TetraJ t)
    {
        super(t);
    }

    private void initPos()
    {
        chunkArray[0].setX(3);  chunkArray[0].setY(21);
        chunkArray[1].setX(3);  chunkArray[1].setY(20);
        chunkArray[2].setX(4);  chunkArray[2].setY(20);
        chunkArray[3].setX(5);  chunkArray[3].setY(20);
    }
	
    @Override
    public void rotateClockwise()
    {
        switch(face)
        {
            case 'n':
                chunkArray[0].move(chunkArray[0].getX() + 2, chunkArray[0].getY() + 0);
                chunkArray[1].move(chunkArray[1].getX() + 1, chunkArray[1].getY() + 1);
                chunkArray[2].move(chunkArray[2].getX() + 0, chunkArray[2].getY() + 0);
                chunkArray[3].move(chunkArray[3].getX() - 1, chunkArray[3].getY() - 1);
                face = 'e';
                break;
            case 'e':
                chunkArray[0].move(chunkArray[0].getX() + 0, chunkArray[0].getY() - 2);
                chunkArray[1].move(chunkArray[1].getX() + 1, chunkArray[1].getY() - 1);
                chunkArray[2].move(chunkArray[2].getX() + 0, chunkArray[2].getY() + 0);
                chunkArray[3].move(chunkArray[3].getX() - 1, chunkArray[3].getY() + 1);
                face = 's';
                break;
            case 's':
                chunkArray[0].move(chunkArray[0].getX() - 2, chunkArray[0].getY() + 0);
                chunkArray[1].move(chunkArray[1].getX() - 1, chunkArray[1].getY() - 1);
                chunkArray[2].move(chunkArray[2].getX() + 0, chunkArray[2].getY() + 0);
                chunkArray[3].move(chunkArray[3].getX() + 1, chunkArray[3].getY() + 1);
                face = 'w';
                break;
            case 'w':
                chunkArray[0].move(chunkArray[0].getX() + 0, chunkArray[0].getY() + 2);
                chunkArray[1].move(chunkArray[1].getX() - 1, chunkArray[1].getY() + 1);
                chunkArray[2].move(chunkArray[2].getX() + 0, chunkArray[2].getY() + 0);
                chunkArray[3].move(chunkArray[3].getX() + 1, chunkArray[3].getY() - 1);
                face = 'n';
                break;
        }
    }
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
    
    public TetraL(TetraL t)
    {
        super(t);
    }

    private void initPos()
    {
        chunkArray[0].setX(5);  chunkArray[0].setY(21);
        chunkArray[1].setX(3);  chunkArray[1].setY(20);
        chunkArray[2].setX(4);  chunkArray[2].setY(20);
        chunkArray[3].setX(5);  chunkArray[3].setY(20);
    }

    @Override
    public void rotateClockwise()
    {
        switch(face)
        {
            case 'n':
                chunkArray[0].move(chunkArray[0].getX() + 0, chunkArray[0].getY() - 2);
                chunkArray[1].move(chunkArray[1].getX() + 1, chunkArray[1].getY() + 1);
                chunkArray[2].move(chunkArray[2].getX() + 0, chunkArray[2].getY() + 0);
                chunkArray[3].move(chunkArray[3].getX() - 1, chunkArray[3].getY() - 1);
                face = 'e';
                break;
            case 'e':
                chunkArray[0].move(chunkArray[0].getX() - 2, chunkArray[0].getY() + 0);
                chunkArray[1].move(chunkArray[1].getX() + 1, chunkArray[1].getY() - 1);
                chunkArray[2].move(chunkArray[2].getX() + 0, chunkArray[2].getY() + 0);
                chunkArray[3].move(chunkArray[3].getX() - 1, chunkArray[3].getY() + 1);
                face = 's';
                break;
            case 's':
                chunkArray[0].move(chunkArray[0].getX() + 0, chunkArray[0].getY() + 2);
                chunkArray[1].move(chunkArray[1].getX() - 1, chunkArray[1].getY() - 1);
                chunkArray[2].move(chunkArray[2].getX() + 0, chunkArray[2].getY() + 0);
                chunkArray[3].move(chunkArray[3].getX() + 1, chunkArray[3].getY() + 1);
                face = 'w';
                break;
            case 'w':
                chunkArray[0].move(chunkArray[0].getX() + 2, chunkArray[0].getY() + 0);
                chunkArray[1].move(chunkArray[1].getX() - 1, chunkArray[1].getY() + 1);
                chunkArray[2].move(chunkArray[2].getX() + 0, chunkArray[2].getY() + 0);
                chunkArray[3].move(chunkArray[3].getX() + 1, chunkArray[3].getY() - 1);
                face = 'n';
                break;
        }
    }
}
