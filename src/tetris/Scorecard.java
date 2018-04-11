package tetris;

public class Scorecard {

	private int score;
	private int level;
	private int linesCleared;

	/* constructors */
	Scorecard() {
		score = 0;
		level = 1;
		linesCleared = 0;
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
	
	public void addScore(int num) {
		score += num;
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
	
	public void incrementLinesCleared() {
		linesCleared++;
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
