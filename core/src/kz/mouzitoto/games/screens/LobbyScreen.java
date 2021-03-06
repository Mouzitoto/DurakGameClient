package kz.mouzitoto.games.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import kz.mouzitoto.games.game.MsgState;
import kz.mouzitoto.games.game.Room;
import kz.mouzitoto.games.game.RoomInfo;
import kz.mouzitoto.games.network.DGClient;
import kz.mouzitoto.games.network.PrivateMsg;

import java.util.List;


/**
 * Created by ruslan.babich on 010 10.04.2017.
 */
public class LobbyScreen implements Screen {
    private SpriteBatch spriteBatch;
    private OrthographicCamera cam;
    private Stage stage;
    private List<RoomInfo> rooms;

    public LobbyScreen(final DGClient dgClient, SpriteBatch spriteBatch, OrthographicCamera cam) {
        this.spriteBatch = spriteBatch;
        this.cam = cam;

        this.stage = new Stage(new FitViewport(480, 600, cam));
        Gdx.input.setInputProcessor(this.stage);

        final PrivateMsg privateMsg = new PrivateMsg();

        //buttons
        Image createButton = new Image(new Texture("create-room-button.png"));
        createButton.setPosition(cam.viewportWidth / 2 - 235, 30);
        createButton.setSize(150, 100);
        createButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                privateMsg.setMsgState(MsgState.CREATE_ROOM);
                dgClient.getClient().sendTCP(privateMsg);

                return false;
            }
        });
        this.stage.addActor(createButton);

        Image joinButton = new Image(new Texture("join-room-button.png"));
        joinButton.setPosition(cam.viewportWidth / 2 - 75, 30);
        joinButton.setSize(150, 100);
        this.stage.addActor(joinButton);

        Image refreshButton = new Image(new Texture("refresh-button.png"));
        refreshButton.setPosition(cam.viewportWidth / 2 + 85, 30);
        refreshButton.setSize(150, 100);
        refreshButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                privateMsg.setMsgState(MsgState.ROOMS_INFO);
                dgClient.getClient().sendTCP(privateMsg);

                return false;
            }
        });
        this.stage.addActor(refreshButton);




    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        this.stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void setRooms(List<RoomInfo> rooms) {
        this.rooms = rooms;
    }
}
