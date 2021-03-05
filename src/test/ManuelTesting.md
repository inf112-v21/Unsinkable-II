#Manual testing

##Screen testing
### Test: Start
By running the main class, you should enter the menu screen.

**Expected result:** Menu screen should open in a separate window.

### Test: Change Screen
Pressing one of the buttons on the screen should change the current screen.

**Expected result:** A different screen should be displayed in the same window.

### Test: Start game
Pressing the "Single Player - TESTING" button should load a game.

**Expected result:** A game screen should be displayed for the user.

##Ingame testing

###Test: Input keys
It should be possible to enable/disable cheatmode by pressing 'c'

When cheat mode is active, you can move the robot around the map using the arrow keys.

**Expected result:** The robot should move on screen according to the direction of the input.

###Test: Input Programcard
The keys 1-5 should correspond to a MOVE_1 card form ProgramCard enum.

There is no visual representation at this point.

**Expected result:** The robot should move according to one of the cards.