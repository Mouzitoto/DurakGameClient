package kz.mouzitoto.games.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import kz.mouzitoto.games.network.DGClient;

/**
 * Created by ruslan.babich on 012 12.04.2017.
 */
public class RoomScreen implements Screen {
    private DGClient dgClient;
    private Stage stage;
    private SpriteBatch spriteBatch;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    public RoomScreen(DGClient dgClient) {
        this.dgClient = dgClient;
        this.spriteBatch = dgClient.getSpriteBatch();
        this.cam = dgClient.getCam();
        this.shapeRenderer = new ShapeRenderer();

        this.stage = new Stage(new FitViewport(480, 600, cam));
        Gdx.input.setInputProcessor(this.stage);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        cam.update();
        shapeRenderer.setProjectionMatrix(cam.combined);


        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        //top left block for room id
        shapeRenderer.rect(1, 499, 150, 100);
        //left block for players
        shapeRenderer.rect(1, 1, 150, 499);
        shapeRenderer.end();


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
