package tetris;

public class RotationHandler
{
    
    public static boolean rotationSafe(Board b, Tetramino t, boolean clockwise)
    {
        Tetramino temp;
        switch(t.getType())
        {
            case 'i':
                temp = new TetraI((TetraI)t);
                break;
/*          case 'o':
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
*/
            default :
                System.out.println("clockwiseRotationSafe(): invalid tetra input");
                return false;
        }
        
        if(clockwise)
            temp.rotateClockwise();
        else
            temp.rotateCounterClockwise();
        
        for(int i = 0; i < 4; i++)
        {
            int x = temp.chunkArray[i].getX();
            if(x > b.getGridWidth() - 1 || x < 0)
                return false;
            int y = temp.chunkArray[i].getY();
            if(y < 0 || y > b.getGridHeight() - 1)
                return false;
            if(b.isSolidChunk(temp.chunkArray[i], 0, 0))
                return false;
        }
        
        return true;
    }
}
