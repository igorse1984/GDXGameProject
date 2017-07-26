package ru.sharovigor.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Игорь on 18.07.2017.
 */

public class GamePad {
    private static final GamePad ourInstance = new GamePad();

    public static GamePad getInstance() {
        return ourInstance;
    }

    // текстура геймпада
    Texture back, stick;
    // векторы позиций центра геймпада (не меняется во время игры)
    // и позиции на джойстике
    Vector2 position, m, m1;
    boolean rend;

    Rectangle rect;

    float joyCenterX, joyCenterY;

    private GamePad() {
        back = new Texture("backcursor.png");
        stick = new Texture("stick.png");
        rect = new Rectangle(50, 50, 200, 200);
        joyCenterX = rect.x + rect.width / 2;
        joyCenterY = rect.y + rect.height / 2;
        position = new Vector2((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        rend = false;
        m = new Vector2(0, 0);
    }

    public void render(SpriteBatch batch) {

        if (rend) {
//            batch.draw(backCursor, rect.x, rect.y);
//            batch.draw(gameCursor, rect.x + 100 - 25 + m.x, rect.y + 100 - 25 + m.y);

            batch.draw(back, 0, 0);
            batch.draw(stick, position.x - stick.getWidth() / 2, position.y - stick.getHeight() / 2);
        }
    }

    public void update(float dt) {
        MyGameInputPrcs mgip = (MyGameInputPrcs) Gdx.input.getInputProcessor();
        if (mgip.isTouched()) {
            // текущие координаты курсора
            m = new Vector2(mgip.getX(), mgip.getY());
            // нулевая позиция геймпада (центр)
            m1 = new Vector2(back.getWidth() / 2, back.getHeight() / 2);

            if (m.cpy().sub(m1).len() < (back.getWidth() / 2) - 32) {
                rend = true;
                position.x = mgip.getX();
                position.y = mgip.getY();
            }
        }
        if (!mgip.isTouched()) {
            rend = false;
            position.x = back.getWidth() / 2;
            position.y = back.getHeight() / 2;
        }
    }

    public void dispose() {
        back.dispose();
    }
}
