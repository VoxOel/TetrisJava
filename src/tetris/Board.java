package tetris;

public class Board {
    private Chunk[][] chunkGrid;
    int width, height;
    
    public Board(int w, int h)
    {
        if(w < 4)
            w = 4;
        if(h < 6)
            h = 6;
        
        width = w;
        height = h;
        chunkGrid = new Chunk[width][height];
        
        for( int i = 0; i < width; i++)
        {
            for( int j = 0; j < height; j++)
            {
                chunkGrid[i][j] = new Chunk();
            }
        }
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
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
