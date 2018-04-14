package tetris;

public class GameOptions {
    public enum PlacementType { CLASSIC, EXTENDED, INFINITE }
    
    public boolean ghostTetra;
    public PlacementType placement;
    public int showNext;
    public boolean superRotation;
    
    public GameOptions()
    {
        ghostTetra = true;
        placement = PlacementType.EXTENDED;
        showNext = 6;
        superRotation = false;
    }
    
}
