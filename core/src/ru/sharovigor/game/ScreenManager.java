package ru.sharovigor.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by IgorSE on 25.07.2017.
 */

public class ScreenManager {
    enum ScreenType {
        GAME
    }

    private static final ScreenManager ourInstance = new ScreenManager();

    public static ScreenManager getInstance() {
        return ourInstance;
    }

    private Game game;
    private GameScreen gameScreen;
    OrthographicCamera camera;
    Viewport viewport;

    public void init(Game game) {
        this.game = game;
        this.gameScreen = new GameScreen();
        camera = new OrthographicCamera(640, 360);
        viewport = new FitViewport(1280, 720, camera);
        viewport.update(1280, 720, true);
        viewport.apply();
    }

    public void switchScreen(ScreenType type) {
        switch (type) {
            case GAME:
                game.setScreen(gameScreen);
                break;
        }
    }

    public Camera getCamera() {
        return camera;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void onResize(int width, int height) {
        viewport.update(width, height, true);
        viewport.apply();
    }

    private ScreenManager() {
    }

    public void dispose() {
    }
}
