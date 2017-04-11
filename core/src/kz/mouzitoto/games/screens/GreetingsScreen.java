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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import kz.mouzitoto.games.game.MsgState;
import kz.mouzitoto.games.network.DGClient;
import kz.mouzitoto.games.network.PrivateMsg;

import java.io.IOException;


/**
 * Created by Mouzitoto on 09.04.2017.
 */
public class GreetingsScreen implements Screen {
    private Stage stage;


    public GreetingsScreen(final DGClient dgClient, final SpriteBatch spriteBatch, final OrthographicCamera cam) {
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);

        this.stage = new Stage(new FitViewport(480, 600, cam));
        Gdx.input.setInputProcessor(this.stage);

        final TextField userNameInput = new TextField("", new Skin(Gdx.files.internal("skin/uiskin.json")));
        userNameInput.setSize(150, 40);
        userNameInput.setPosition(cam.viewportWidth / 2 - 75, cam.viewportHeight / 2);
        this.stage.addActor(userNameInput);

        Image enterButton = new Image(new Texture("enter-button.png"));
        enterButton.setPosition(cam.viewportWidth / 2 - 75, cam.viewportHeight / 2 - 110);
        enterButton.setSize(150, 100);
        enterButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("enterButton", userNameInput.getText());

                try {
                    connectToServer(dgClient, userNameInput.getText());
                    dgClient.getPlayer().setName(userNameInput.getText());

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return false;
            }
        });
        this.stage.addActor(enterButton);

        //todo: add label at the bottom, for exception messages


    }



    private void connectToServer(DGClient dgClient, String userName) throws IOException {
        dgClient.getClient().connect(5000, "127.0.0.1", 27015);

        PrivateMsg privateMsg = new PrivateMsg();
        privateMsg.setMsgState(MsgState.NEW_PLAYER);
        privateMsg.setMsg(userName);

        dgClient.getClient().sendTCP(privateMsg);
    }





    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        this.stage.draw();
    }


    //<editor-fold desc="Unused implemented methods">
    @Override
    public void show() {

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
    //</editor-fold>
}
