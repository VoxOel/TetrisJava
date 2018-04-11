package tetris;

public class Board {
    private Chunk[][] chunkGrid;
    
    public Board()
    {
        chunkGrid = new Chunk[10][22];
        
        for( int i = 0; i < 10; i++)
        {
            for( int j = 0; j < 22; j++)
            {
                chunkGrid[i][j] = new Chunk();
            }
        }
    }
    
    public boolean setChunk(int x, int y, boolean show, String color)
    {
        boolean success = true;
        
        if(!chunkGrid[x][y].setX(x))
            success = false;
        
        if(!chunkGrid[x][y].setY(y))
            success = false;
        
        if(!chunkGrid[x][y].setVisibility(show))
            success = false;
        
        if(!chunkGrid[x][y].setColor(color))
            success = false;
        
        return success;
        
    }
    
}
