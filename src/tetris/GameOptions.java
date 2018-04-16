package tetris;

public class GameOptions {
    public enum PlacementType { CLASSIC, EXTENDED, INFINITE }
    
    public boolean ghostTetra;
    public PlacementType placement;
    public int showNext;
    public int skyline, boardWidth;
    public boolean superRotation;
    public boolean holdBox;
    public boolean debugMode;
    
    public GameOptions()
    {
        debugMode = false;
        
        //in-play options
        ghostTetra = true;
        placement = PlacementType.EXTENDED;
        superRotation = false;
        showNext = 6;
        holdBox = true;
        
        //gamerule options
        boardWidth = 10;
        skyline = 20;
        
    }
    
}
