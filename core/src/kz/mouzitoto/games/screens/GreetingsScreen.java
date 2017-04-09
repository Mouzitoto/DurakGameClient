package kz.mouzitoto.games.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import kz.mouzitoto.games.network.DGClient;


/**
 * Created by Mouzitoto on 09.04.2017.
 */
public class GreetingsScreen implements Screen {
    DGClient dgClient;
    SpriteBatch spriteBatch;
    OrthographicCamera cam;
    Stage stage;

    public GreetingsScreen(DGClient dgClient, SpriteBatch spriteBatch, OrthographicCamera cam) {
        this.dgClient = dgClient;
        this.spriteBatch = spriteBatch;
        this.cam = cam;

        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);

        stage = new Stage(new FitViewport(480, 600, cam));


        final TextField userNameInput = new TextField("", new Skin(Gdx.files.internal("skin/uiskin.json")));
        userNameInput.setSize(150, 40);
        userNameInput.setPosition(cam.viewportWidth / 2 - 75, cam.viewportHeight / 2);
        stage.addActor(userNameInput);

        Image enterButton = new Image(new Texture("enter-button.png"));
        enterButton.setPosition(cam.viewportWidth / 2 - 75, cam.viewportHeight / 2 - 110);
        enterButton.setSize(150, 100);
        enterButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("enterButton", userNameInput.getText());
                return false;
            }
        });
        stage.addActor(enterButton);

        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.draw();
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
}
