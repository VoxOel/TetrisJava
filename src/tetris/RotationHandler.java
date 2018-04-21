package tetris;

public class RotationHandler
{
    private Board board;
    
    public RotationHandler(Board b)
    {
        board = b;
    }
    
    public boolean clockwise(Tetramino t)
    {
        Tetramino temp = getCopy(t);
        if(rotClock1(temp))
        {
            return rotClock1(t);
        }
        temp = getCopy(t);
        if(rotClock2(temp))
        {
            return rotClock2(t);
        }
        temp = getCopy(t);
        if(rotClock3(temp))
        {
            return rotClock3(t);
        }
        temp = getCopy(t);
        if(rotClock4(temp))
        {
            return rotClock4(t);
        }
        temp = getCopy(t);
        if(rotClock5(temp))
        {
            return rotClock5(t);
        }
        
        return false;
    }
    
    public boolean counterClockwise(Tetramino t)
    {
        Tetramino temp = getCopy(t);
        if(rotCounter1(temp))
        {
            return rotCounter1(t);
        }
        temp = getCopy(t);
        if(rotCounter2(temp))
        {
            return rotCounter2(t);
        }
        temp = getCopy(t);
        if(rotCounter3(temp))
        {
            return rotCounter3(t);
        }
        temp = getCopy(t);
        if(rotCounter4(temp))
        {
            return rotCounter4(t);
        }
        temp = getCopy(t);
        if(rotCounter5(temp))
        {
            return rotCounter5(t);
        }
        
        return false;
    }
    
    private boolean rotClock1(Tetramino t)
    {
        t.rotateClockwise();
        return checkCollision(t);
    }
    
    private boolean rotCounter1(Tetramino t)
    {
        t.rotateCounterClockwise();
        return checkCollision(t);
    }
    
    private boolean rotClock2(Tetramino t)
    {
        char face = t.getFace();
        t.rotateClockwise();
        switch(t.getType())
        {
            case 'o':
                return true;
            case 'i':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.left();
                        t.left();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.left();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.right();
                        t.right();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.right();
                        return checkCollision(t);
                    }
                }
            }
            case 't':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.left();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.right();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.right();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.left();
                        return checkCollision(t);
                    }
                }
            }
            case 'l':
            case 'j':
            case 's':
            case 'z':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.left();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.right();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.right();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.left();
                        return checkCollision(t);
                    }
                }
            }
        }
        return false;
    }
    
    private boolean rotCounter2(Tetramino t)
    {
        char face = t.getFace();
        t.rotateCounterClockwise();
        switch(t.getType())
        {
            case 'o':
                return true;
            case 'i':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.left();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.right();
                        t.right();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.right();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.left();
                        t.left();
                        return checkCollision(t);
                    }
                }
            }
            case 't':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.right();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.right();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.left();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.left();
                        return checkCollision(t);
                    }
                }
            }
            case 'l':
            case 'j':
            case 's':
            case 'z':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.right();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.right();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.left();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.left();
                        return checkCollision(t);
                    }
                }
            }
        }
        return false;
    }
    
    private boolean rotClock3(Tetramino t)
    {
        char face = t.getFace();
        t.rotateClockwise();
        switch(t.getType())
        {
            case 'o':
                return true;
            case 'i':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.right();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.right();
                        t.right();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.left();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.left();
                        t.left();
                        return checkCollision(t);
                    }
                }
            }
            case 't':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.left();
                        t.up();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.right();
                        t.down();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        return false;
                    }
                    case 'w':
                    {
                        t.left();
                        t.down();
                        return checkCollision(t);
                    }
                }
            }
            case 'l':
            case 'j':
            case 's':
            case 'z':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.left();
                        t.up();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.right();
                        t.down();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.right();
                        t.up();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.left();
                        t.down();
                        return checkCollision(t);
                    }
                }
            }
        }
        return false;
    }
    
    private boolean rotCounter3(Tetramino t)
    {
        char face = t.getFace();
        t.rotateCounterClockwise();
        switch(t.getType())
        {
            case 'o':
                return true;
            case 'i':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.right();
                        t.right();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.left();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.left();
                        t.left();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.right();
                        return checkCollision(t);
                    }
                }
            }
            case 't':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.right();
                        t.up();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.right();
                        t.down();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        return false;
                    }
                    case 'w':
                    {
                        t.left();
                        t.down();
                        return checkCollision(t);
                    }
                }
            }
            case 'l':
            case 'j':
            case 's':
            case 'z':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.right();
                        t.up();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.right();
                        t.down();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.left();
                        t.up();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.left();
                        t.down();
                        return checkCollision(t);
                    }
                }
            }
        }
        return false;
    }
            
    private boolean rotClock4(Tetramino t)
    {
        char face = t.getFace();
        t.rotateClockwise();
        switch(t.getType())
        {
            case 'o':
                return true;
            case 'i':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.left();
                        t.left();
                        t.down();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.left();
                        t.up();
                        t.up();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.right();
                        t.right();
                        t.up();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.right();
                        t.down();
                        t.down();
                        return checkCollision(t);
                    }
                }
            }
            case 't':
            {
                switch(face)
                {
                    case 'n':
                    {
                        return false;
                    }
                    case 'e':
                    {
                        t.up();
                        t.up();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.down();
                        t.down();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.up();
                        t.up();
                        return checkCollision(t);
                    }
                }
            }
            case 'l':
            case 'j':
            case 's':
            case 'z':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.down();
                        t.down();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.up();
                        t.up();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.down();
                        t.down();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.up();
                        t.up();
                        return checkCollision(t);
                    }
                }
            }
        }
        return false;
    }
    
    private boolean rotCounter4(Tetramino t)
    {
        char face = t.getFace();
        t.rotateCounterClockwise();
        switch(t.getType())
        {
            case 'o':
                return true;
            case 'i':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.left();
                        t.up();
                        t.up();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.right();
                        t.right();
                        t.up();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.right();
                        t.down();
                        t.down();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.left();
                        t.left();
                        t.down();
                        return checkCollision(t);
                    }
                }
            }
            case 't':
            {
                switch(face)
                {
                    case 'n':
                    {
                        return false;
                    }
                    case 'e':
                    {
                        t.up();
                        t.up();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.down();
                        t.down();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.up();
                        t.up();
                        return checkCollision(t);
                    }
                }
            }
            case 'l':
            case 'j':
            case 's':
            case 'z':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.down();
                        t.down();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.up();
                        t.up();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.down();
                        t.down();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.up();
                        t.up();
                        return checkCollision(t);
                    }
                }
            }
        }
        return false;
    }
            
    private boolean rotClock5(Tetramino t)
    {
        char face = t.getFace();
        t.rotateClockwise();
        switch(t.getType())
        {
            case 'o':
                return true;
            case 'i':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.right();
                        t.up();
                        t.up();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.right();
                        t.right();
                        t.down();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.left();
                        t.down();
                        t.down();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.left();
                        t.left();
                        t.up();
                        return checkCollision(t);
                    }
                }
            }
            case 't':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.left();
                        t.down();
                        t.down();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.right();
                        t.up();
                        t.up();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.right();
                        t.down();
                        t.down();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.left();
                        t.up();
                        t.up();
                        return checkCollision(t);
                    }
                }
            }
            case 'l':
            case 'j':
            case 's':
            case 'z':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.left();
                        t.down();
                        t.down();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.right();
                        t.up();
                        t.up();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.right();
                        t.down();
                        t.down();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.left();
                        t.up();
                        t.up();
                        return checkCollision(t);
                    }
                }
            }
        }
        return false;
    }
            
    private boolean rotCounter5(Tetramino t)
    {
        char face = t.getFace();
        t.rotateCounterClockwise();
        switch(t.getType())
        {
            case 'o':
                return true;
            case 'i':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.right();
                        t.right();
                        t.down();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.left();
                        t.down();
                        t.down();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.left();
                        t.left();
                        t.up();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.right();
                        t.down();
                        t.down();
                        return checkCollision(t);
                    }
                }
            }
            case 't':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.right();
                        t.down();
                        t.down();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.right();
                        t.up();
                        t.up();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.left();
                        t.down();
                        t.down();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.left();
                        t.up();
                        t.up();
                        return checkCollision(t);
                    }
                }
            }
            case 'l':
            case 'j':
            case 's':
            case 'z':
            {
                switch(face)
                {
                    case 'n':
                    {
                        t.right();
                        t.down();
                        t.down();
                        return checkCollision(t);
                    }
                    case 'e':
                    {
                        t.right();
                        t.up();
                        t.up();
                        return checkCollision(t);
                    }
                    case 's':
                    {
                        t.left();
                        t.down();
                        t.down();
                        return checkCollision(t);
                    }
                    case 'w':
                    {
                        t.left();
                        t.up();
                        t.up();
                        return checkCollision(t);
                    }
                }
            }
        }
        return false;
    }
            
    //returns true if position is valid
    private boolean checkCollision(Tetramino t)
    {
        for(int i = 0; i < 4; i++)
        {
            int x = t.chunkArray[i].getX();
            if(x > board.getGridWidth() - 1 || x < 0)
                return false;
            int y = t.chunkArray[i].getY();
            if(y < 0 || y > board.getGridHeight() - 1)
                return false;
            if(board.isSolidChunk(t.chunkArray[i], 0, 0))
                return false;
        }
        
        return true;
    }
    
    private Tetramino getCopy(Tetramino t) 
    {
        Tetramino temp = null;
        switch(t.getType())
        {
            case 'i':
                temp = new TetraI((TetraI)t);
                break;
            case 'o':
                temp = new TetraO((TetraO)t);
                break;
            case 't':
                temp = new TetraT((TetraT)t);
                break;
            case 's':
                temp = new TetraS((TetraS)t);
                break;
            case 'z':
                temp = new TetraZ((TetraZ)t);
                break;
            case 'j':
                temp = new TetraJ((TetraJ)t);
                break;
            case 'l':
                temp = new TetraL((TetraL)t);
                break;
        }
        
        return temp;
    }
}
