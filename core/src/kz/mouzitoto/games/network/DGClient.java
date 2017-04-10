package kz.mouzitoto.games.network;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import kz.mouzitoto.games.game.MsgState;
import kz.mouzitoto.games.screens.GreetingsScreen;

public class DGClient extends Game {
	private SpriteBatch spriteBatch;
	private OrthographicCamera cam;
	private Client client;
	private DGListener dgListener;

	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		cam = new OrthographicCamera(480, 600);

		createNetworkClient();

		setScreen(new GreetingsScreen(this, spriteBatch, cam));

	}

	private void createNetworkClient() {
		this.client = new Client();
		this.client.start();

		this.dgListener = new DGListener();
		this.client.addListener(this.dgListener);

		Kryo kryo = this.client.getKryo();
		kryo.register(PrivateMsg.class);
		kryo.register(BroadCastMsg.class);
		kryo.register(MsgState.class);
	}

	public Client getClient() {
		return client;
	}

	public DGListener getDgListener() {
		return dgListener;
	}
}
