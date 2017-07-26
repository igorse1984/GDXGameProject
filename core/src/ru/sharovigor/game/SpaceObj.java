package ru.sharovigor.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.toDegrees;

abstract class SpaceObj {
    Texture texture;
    Vector2 position;
    Vector2 velocity;
    Vector2 m, m1;
    float angle;
    float healt;
    Circle hitArea;

    public SpaceObj(String TEXTURE_NAME, Vector2 position) {
        if (texture == null) {
            texture = new Texture(TEXTURE_NAME);
        }
        angle = 0;
        healt = 100;
        this.position = position;
        hitArea = new Circle(position.x, position.y, texture.getWidth() / 2);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - texture.getWidth() / 2, position.y - texture.getHeight() / 2, texture.getWidth() / 2, texture.getHeight() / 2, texture.getWidth(), texture.getHeight(), 1.0f, 1.0f, (float) toDegrees(angle), 0, 0, 64, 64, false, false);
    }

    public void update(float dt) {
        // двигатель (к вектору позиции прибавляется вектор скорости)
        position.mulAdd(velocity, dt);

        // обсчет столкновений с обьектами
        hitArea.x = position.x;
        hitArea.y = position.y;
//        healt = Utils.boomAsteroid(asteroids, position, velocity, healt);
    }

    public void dispose() {
        texture.dispose();
    }

}

