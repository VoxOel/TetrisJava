Tetris
Anthony Oelsner & Yianni Whisler

HOW TO RUN:
The JAR file provided is a fully runnable jar. Double click it, or do
java -jar hwx.jar

the main class is Tetris so, if it is not in the jar file, java Tetris
should run the program

CONTROLS:
- LEFT and RIGHT arrow keys to move the current tetramino sideways
- DOWN arrow key to soft fall
- SPACE BAR to hard fall
- 'Z' rotates  counterclockwise
- 'X' rotates clockwise
- SHIFT key to hold the current piece
- ESCAPE to pause the game
- PERIOD key toggles debug mode

Debug mode:
Prevents the tetramino from falling and enables extra controls that are only
accessible in debug mode.

- 'K' triggers the current game to end
- CTRL key will lock the key in place
- UP arrow key to move the tetramino up
- BACKSPACE to clear the gameboard

INTERFACE:
Press any key at the title screen, after that, use the mouse to adjust start
level or access play settings. Click start to begin the game. At any point
the game can be paused by pressing ESCAPE.

Start Level is implemented, clicking on this will increase the level the game
will begin at.

Play Settings:
- Show Ghost: toggles the ghost tetramino at the bottom of the screen
- Show Hold: toggles the hold box at the top left of the screen
- Show Next: toggles the number of upcoming tetraminoes that show up on the
	right side of the screen
- Placement type:
	Classic: the player has .5 seconds to move or rotate the tetramino before
		it locks.
	Extended: the player has .5 seconds to move or rotate the tetramino before
		it locks. The player can move or rotate the tetramino 15 times, which resets
		the lock timer.
	Infinite: the player has .5 seconds to move or rotate the tetramino before
		it locks. The player can move or rotate the tetramino indefinitely, which resets
		the lock timer.
	
EXTRA FEATURES:
- Added music that plays only while game is in progress
- The entire game minus the title screen features custom sprites and textures
	made by Anthony
- All of the options settings 
- The Ghost Tetramino
- HighScores
- Implemented Tetris Super Rotation™ (this is whenever the tetramino gets rotated
	during certain scenarios "to provide a smoother playing experience."

WORK:
https://github.com/VoxOel/TetrisJava
This is only a general description most of the time was spent in pair-programming at Dirac

Anthony:
https://github.com/VoxOel/TetrisJava/commits?author=VoxOel
- Menus
- GUI
- Super Rotation™
- Scoring
- Created the font
- Textures
- SoundHandler

Yianni:
https://github.com/VoxOel/TetrisJava/commits?author=YianniWhisler
- Responsible for Tetramino manipulation
- Classic Rotation (framework for Super Rotation™)
- HoldBox
- NextQueue
- Various parts of GameManager

SOURCES:
- 2009 Tetris Design Guideline
	https://www.dropbox.com/s/g55gwls0h2muqzn/tetris_guideline_docs_2009.zip?dl=0&file_subpath=%2F2009+Tetris+Design+Guideline.pdf
- korobeiniki by suchabadperson, use granted under license from
	https://soundcloud.com/suchabadperson/tetris-theme-a-korobeiniki
