package tetris;

public class GameOptions {
    public enum PlacementType { CLASSIC, EXTENDED, INFINITE }
    
    public boolean ghostTetra;
    public PlacementType placement;
    public int showNext;
    public int skyline, boardWidth;
    public boolean superRotation;
    public boolean debugMode;
    
    public GameOptions()
    {
        //play options
        ghostTetra = true;
        placement = PlacementType.EXTENDED;
        showNext = 6;
        superRotation = false;
        debugMode = false;
        
        //set-up options
        boardWidth = 10;
        skyline = 20;
    }
    
}
