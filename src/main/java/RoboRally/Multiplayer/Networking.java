package RoboRally.Multiplayer;

import com.esotericsoftware.kryonet.EndPoint;

public interface Networking {

    /**
     * Registers the classes necessary to translate the bitstream into objects.
     *
     * @param endPoint to be registered.
     */
    void register(EndPoint endPoint);

}
