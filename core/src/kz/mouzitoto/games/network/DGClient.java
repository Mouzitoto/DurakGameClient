package kz.mouzitoto.games.network;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import kz.mouzitoto.games.game.MainPlayer;
import kz.mouzitoto.games.game.MsgState;
import kz.mouzitoto.games.game.Player;
import kz.mouzitoto.games.game.Room;
import kz.mouzitoto.games.screens.GreetingsScreen;
import kz.mouzitoto.games.screens.LobbyScreen;
import kz.mouzitoto.games.screens.RoomScreen;

public class DGClient extends Game {
	private SpriteBatch spriteBatch;
	private OrthographicCamera cam;
	private Client client;
	private DGListener dgListener;
	private MainPlayer player;
	private LobbyScreen lobbyScreen;
	private RoomScreen roomScreen;
	private Room room;

	@Override
	public void create () {
		this.spriteBatch = new SpriteBatch();
		this.cam = new OrthographicCamera(480, 600);

		this.player = new MainPlayer();

		createNetworkClient();

		setScreen(new GreetingsScreen(this, spriteBatch, cam));

	}

	private void createNetworkClient() {
		this.client = new Client();
		this.client.start();

		this.dgListener = new DGListener(this);
		this.client.addListener(this.dgListener);

		Kryo kryo = this.client.getKryo();
		kryo.register(PrivateMsg.class);
		kryo.register(BroadCastMsg.class);
		kryo.register(MsgState.class);
	}

	public void enterLobby() {
		this.lobbyScreen = new LobbyScreen(this, spriteBatch, cam);
		setScreen(this.lobbyScreen);

		PrivateMsg privateMsg = new PrivateMsg();
		privateMsg.setMsgState(MsgState.ROOMS_INFO);
		this.client.sendTCP(privateMsg);
	}

	public void enterRoom() {
		this.roomScreen = new RoomScreen(this);
		setScreen(this.roomScreen);
	}







	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public RoomScreen getRoomScreen() {
		return roomScreen;
	}

	public Client getClient() {
		return client;
	}

	public DGListener getDgListener() {
		return dgListener;
	}

	public MainPlayer getPlayer() {
		return player;
	}

	public LobbyScreen getLobbyScreen() {
		return lobbyScreen;
	}

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	public OrthographicCamera getCam() {
		return cam;
	}
}
