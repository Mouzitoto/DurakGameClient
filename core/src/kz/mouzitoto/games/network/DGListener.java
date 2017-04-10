package kz.mouzitoto.games.network;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kz.mouzitoto.games.game.Room;
import kz.mouzitoto.games.screens.LobbyScreen;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by ruslan.babich on 010 10.04.2017.
 */
public class DGListener extends Listener {
    private DGClient dgClient;
    private LobbyScreen lobbyScreen;
    private Gson gson = new Gson();

    public DGListener(DGClient dgClient) {
        this.dgClient = dgClient;
    }

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof PrivateMsg) {
            PrivateMsg privateMsg = (PrivateMsg) object;

            switch (privateMsg.getMsgState()) {
                case HANDSHAKE:
                    handshake(privateMsg);
                    break;

                case ROOMS_INFO:
                    roomsInfo(privateMsg);
                    break;



            }
        }
    }

    private void roomsInfo(PrivateMsg privateMsg) {
        Type roomsListType = new TypeToken<List<Room>>(){}.getType();
        List<Room> rooms = gson.fromJson(privateMsg.getMsg(), roomsListType);

        Gdx.app.log("roomsInfo", privateMsg.getMsg());

        lobbyScreen.setRooms(rooms);
    }

    private void handshake(PrivateMsg privateMsg) {
        dgClient.getPlayer().setId(privateMsg.getMsg());

        Gdx.app.log("handshake", privateMsg.getMsg());
    }

    public void setLobbyScreen(LobbyScreen lobbyScreen) {
        this.lobbyScreen = lobbyScreen;
    }
}
