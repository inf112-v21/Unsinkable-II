#Manual testing

This document specifies instructions for manual testing for functionality
that is not covered by the automatic unit tests.  
Before going through these, you should check that the automated tests
are passing.

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
When cheat mode is active, you can move the robot around the boardLayers using the arrow keys.

**Expected result:** The robot should move on screen according to the direction of the input.

###Test: Input Programcard
When a game has started, you should see some program cards on the right side of the screen.
Click on one to add it, then click run to execute the program cards when you have chosen five
cards.

**Expected result:** The program cards should be added to the queue, and the
robot should move according to the program cards.

###Test: Networking
Start up two instances of the game, one of which is the host.
The other instance should join the host.

**Expected result:** The host should join the game as usual, then the
second player should be able to join. Both players should be visible on the screen.
Test with more players if needed.

###Test: Multiplayer turns
Each player has their own cards, which their robot can execute.

**Expected result:** Each player should be able to execute their own cards in turn.
All players should see each other's movement in real time.

