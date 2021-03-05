package RoboRally.Game;

import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.MapTools.MapChecker;
import RoboRally.Game.MapTools.Map;
import RoboRally.Game.MapTools.MapSelector;
import RoboRally.Game.Cards.ProgrammingDeck;
import RoboRally.Game.Objects.Player;
import RoboRally.Game.Objects.Robot;
import RoboRally.Multiplayer.Multiplayer;
import RoboRally.RoboRallyApp;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.List;

/**
 * The Game - RoboRally
 */
public class RoboRallyGame {
    private final RoboRallyApp app;
    private final Multiplayer myConnection;
    private final List<Player> players;
    private final Map map;
    public final EventHandler eventHandler;
    private boolean cheatMode = false;

    private final MapChecker mapChecker;
    private final ProgrammingDeck deck;

    public RoboRallyGame(RoboRallyApp app, Multiplayer connection) {
        this.app = app;
        this.myConnection = connection;
        this.players = new LinkedList<>();
        map = new Map(MapSelector.MAP2); // TODO: Get selected map.
        mapChecker = new MapChecker(map);
        eventHandler = new EventHandler(this); //TODO: Use handler
        deck = new ProgrammingDeck();
    }


    /**
     * Adds a new player to the game.
     *
     * @return the Player added.
     */
    public Player addPlayer() {
        Player newPlayer = new Player(players.size()+1);
        newPlayer.getRobot().setLoc(5,5); // TODO: Get next start loc
        map.playerLayer.setCell((int) newPlayer.getRobot().getLoc().x,(int) newPlayer.getRobot().getLoc().y, newPlayer.getPiece().getCell());
        players.add(newPlayer);

        return players.get(players.size()-1);
    }

    /**
     * @return List of players
     */
    public List<Player> getPlayers() { return players; }

    public Map getMap() { return map; }



    //================================================================
    //                            Actions
    //================================================================

    private void playProgramCard(Robot robot, ProgramCard card) {
        if (card.getSteps() != 0) { updateLocation(robot, card); }
        else { updateHeading(robot, card); }
    }

    private void updateLocation(Robot robot, ProgramCard card) {
        robot.getLoc().x += robot.heading().getX() * card.getSteps();
        robot.getLoc().y += robot.heading().getY() * card.getSteps();
    }

    private void updateHeading(Robot robot, ProgramCard card) {
        robot.setHeading(robot.heading().rotate(card.getRotation()));
    }


    /**
     * Moves a player's robot according to the program card.
     *
     * @param player who controls robot.
     * @param card that contains robot instructions to be executed.
     */
    public void ExecuteProgramCard(Player player, ProgramCard card) {
        mapChecker.removeRobot(player.getRobot());
        playProgramCard(player.getRobot(), card);
        mapChecker.putRobot(player);
    }

    // ======================================================================================
    //                         TESTING RELATED METHODS BELLOW
    // ======================================================================================

    public static void move(Robot robot, Direction dir) {
        robot.setLoc(robot.getLoc().add(new Vector2(dir.getX(), dir.getY())));
    }

    /**
     * Moves robot according to card.
     * return true if robot was moved.
    */
    public boolean ExecuteProgramCard(int index) {
        mapChecker.removeRobot(app.getSelf().getRobot());
        playProgramCard(app.getSelf().getRobot(), app.getSelf().getHand().get(index));
        mapChecker.putRobot(app.getSelf());
        app.getSelf().getHand().set(index, deck.drawCard());
        return true;
    }

    //================================================================
    //                            Cheat Mode
    //================================================================

    /**
     * Enters Cheat-mode. Lets the robot move with commands from keyboard.
     */
    public boolean cheatMove (Direction dir) {
        mapChecker.removeRobot(app.getSelf().getRobot());
        TestingLibrary.move(app.getSelf().getRobot(), dir);
        mapChecker.putRobot(app.getSelf());
        return true;
    }

    public void toggleCheatMode() {
        cheatMode = !cheatMode;
    }

    public boolean isCheatModeOn() {
        return cheatMode;
    }
}
