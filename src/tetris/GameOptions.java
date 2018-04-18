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
        //these can be changed during play
        ghostTetra = true;
        placement = PlacementType.INFINITE;
        superRotation = false;
        showNext = 6;
        holdBox = true;
        
        //gamerule options
        //these have to be changed before a game is started
        boardWidth = 10;
        skyline = 20;
        
        
    }
    
}
