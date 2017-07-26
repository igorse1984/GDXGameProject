package ru.sharovigor.game;

import com.badlogic.gdx.Game;

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

    public void init(Game game) {
        this.game = game;
        this.gameScreen = new GameScreen();
    }

    public void switchScreen(ScreenType type) {
        switch (type) {
            case GAME:
                game.setScreen(gameScreen);
                break;
        }
    }

    private ScreenManager() {
    }
}
