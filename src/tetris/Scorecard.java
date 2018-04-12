package tetris;

public class Scorecard {

	private int score;
	private int level;
	private int linesCleared;
        private int singles, doubles, triples;
        private int tetrises, tSpins;

	/* constructors */
	Scorecard() {
            score = 0;
            level = 1;
            linesCleared = 0;
            singles = 0;        //  TODO:
            doubles = 0;        //      these 5 need
            triples = 0;        //      getters and
            tetrises = 0;       //      setters!
            tSpins = 0;         //
	}
	
	Scorecard(int newScore, int newLevel) {
            score = newScore;
            level = newLevel;
            linesCleared = 0;
	}
	
	/* setters */
	public void setScore(int newScore) {
            score = newScore;
	}
	
	public void addScore(int linesCleared, boolean TSpin) {
            /* assumes that this function will probably recieve
             * parameters from Board or GameManager class.
             * pseudo:
             * if(wasTSpin) {Scorecard.addScore(linesCleared, true)}
             */
            switch(linesCleared) {
                case 0: // TSpin
                    score += 400 * level;
                    break;
                case 1: // Single
                    if(TSpin)
                        score += 800 * level;
                    else
                        score += 100 * level;
                    break;
                case 2: // Double
                    if(TSpin)
                        score += 1200 * level;
                    else
                        score += 300 * level;
                    break;
                case 3: // Triple
                    if(TSpin)
                        score += 1600 * level;
                    else
                        score += 500 * level;
                    break;
                case 4: // Tetris!
                    score += 800 * level;
                    break;
            }
                
	}
	
	public void setLevel(int newLevel) {
            level = newLevel;
	}
	
	public void incrementLevel() {
            level++;
	}
	
	public void setLinesCleared(int newLinesCleared) {
            linesCleared = newLinesCleared;
	}
	
	public void addLinesCleared(int add) {
            linesCleared += add;
	}
	
	/* getters */
	public int getScore() {
            return score;
	}
	
	public int getLevel() {
            return level;
	}
	
	public int getLinesCleared() {
            return linesCleared;
	}

}
