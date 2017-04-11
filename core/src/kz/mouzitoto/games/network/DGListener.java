package kz.mouzitoto.games.network;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kz.mouzitoto.games.game.Room;
import kz.mouzitoto.games.game.RoomInfo;
import kz.mouzitoto.games.screens.LobbyScreen;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by ruslan.babich on 010 10.04.2017.
 */
public class DGListener extends Listener {
    private DGClient dgClient;
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

                case SUCCESSFULLY_JOINED_ROOM:
                    successfullyJoinedRoom(privateMsg);
                    break;



            }
        }
    }

    private void successfullyJoinedRoom(PrivateMsg privateMsg) {
        Room room = new Room(privateMsg.getRoomId());

        Gdx.app.log("playersJson", privateMsg.getMsg());
        //todo: parse playersJson to room.setPlayers

        dgClient.getPlayer().setRoom(room);
    }

    private void roomsInfo(PrivateMsg privateMsg) {
        //todo: user RoomInfo class
        Type roomsInfoListType = new TypeToken<List<RoomInfo>>(){}.getType();
        List<RoomInfo> rooms = gson.fromJson(privateMsg.getMsg(), roomsInfoListType);

        Gdx.app.log("roomsInfo", privateMsg.getMsg());

        dgClient.getLobbyScreen().setRooms(rooms);
    }

    private void handshake(PrivateMsg privateMsg) {
        dgClient.getPlayer().setId(privateMsg.getMsg());

//        Gdx.app.postRunnable(new Runnable() {
//            @Override
//            public void run() {
//                dgClient.enterLobby();
//            }
//        });

        dgClient.enterLobby();


        Gdx.app.log("handshake", privateMsg.getMsg());
    }
}
