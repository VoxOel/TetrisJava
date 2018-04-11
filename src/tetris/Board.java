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
                chunkGrid[i][j] = new Chunk()
            }
        }
    }
    
}
