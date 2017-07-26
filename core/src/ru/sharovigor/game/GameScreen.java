package ru.sharovigor.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by IgorSE on 25.07.2017.
 */

public class GameScreen implements Screen {


    private SpriteBatch batch;
    private Human human;
    private Bot[] bots;
    private Background background;
    MyGameInputPrcs mgip;



    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Background();

        // эмиттер с астеройдами
        for (int i = 0; i < 10; i++) {
            AsteroidEmitter.getInstance().setupAsteroid();
        }

        bots = new Bot[10];
        for (int i = 0; i < bots.length; i++) {
            bots[i] = new Bot();
        }
        human = new Human();
        mgip = new MyGameInputPrcs();
        Gdx.input.setInputProcessor(mgip);


    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix((ScreenManager.getInstance().getCamera().combined));
        batch.begin();
        background.render(batch);

        // рендер астеройдов
        AsteroidEmitter.getInstance().render(batch);

        human.render(batch);
        for (Bot bot : bots) {
            bot.render(batch);
        }
        BulletEmitter.getInstance().render(batch);
        GamePad.getInstance().render(batch);
        batch.end();
    }

    private void update(float dt) {
        GamePad.getInstance().update(dt);
        human.update(dt);
        for (Bot bot : bots) {
            bot.update(human, dt);
        }
        background.update(human, dt);
        BulletEmitter.getInstance().update(dt);
        // апдейт астеройдов
        AsteroidEmitter.getInstance().update(dt);
        // проверка на коллизии игрока и бота
        Utils.checkCollision(human, BulletEmitter.getInstance());
        for (Bot bt : bots) {
            Utils.checkCollision(bt, BulletEmitter.getInstance());
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        human.dispose();
        for (Bot bot : bots) {
            bot.dispose();
        }
        // диспоуз астеройдов нужен?

        GamePad.getInstance().dispose();
        background.dispose();
    }


    @Override
    public void resize(int width, int height) {
        ScreenManager.getInstance().onResize(width, height);

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


}
