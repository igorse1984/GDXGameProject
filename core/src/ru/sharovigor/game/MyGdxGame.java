package ru.sharovigor.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.text.View;

public class MyGdxGame extends Game {


    @Override
    public void create() {
        ScreenManager.getInstance().init(this);
        ScreenManager.getInstance().switchScreen(ScreenManager.ScreenType.GAME);
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        getScreen().render(dt);
    }
    @Override
    public void dispose(){
        ScreenManager.getInstance().dispose();
    }

}