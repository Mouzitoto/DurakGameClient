package kz.mouzitoto.games.network;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import kz.mouzitoto.games.screens.GreetingsScreen;

public class DGClient extends Game {
	private SpriteBatch spriteBatch;
	private OrthographicCamera cam;

	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		cam = new OrthographicCamera(480, 600);

		setScreen(new GreetingsScreen(this, spriteBatch, cam));

	}

}
