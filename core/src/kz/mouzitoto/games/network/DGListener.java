package kz.mouzitoto.games.network;

import com.esotericsoftware.kryonet.Listener;
import kz.mouzitoto.games.screens.LobbyScreen;

/**
 * Created by ruslan.babich on 010 10.04.2017.
 */
public class DGListener extends Listener {
    private LobbyScreen lobbyScreen;




    public void setLobbyScreen(LobbyScreen lobbyScreen) {
        this.lobbyScreen = lobbyScreen;
    }
}
