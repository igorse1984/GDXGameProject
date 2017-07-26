package ru.sharovigor.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;


class Bullet implements Pool.Poolable {
    Vector2 position;
    Vector2 velocity;
    private Texture texture;
    boolean active;
    Circle hitArea;



    public Bullet() {
        if (texture == null) {
            texture = new Texture("bullet.png");
        }
        position = new Vector2(0, 0);
        velocity = new Vector2(0, 0);
        active = false;
        hitArea = new Circle(position.x, position.y, texture.getWidth() / 1.5f);
    }

    public void setup(float x, float y, float vx, float vy) {
        active = true;
        position.set(x, y);
        velocity.set(vx, vy);
    }

    @Override
    public void reset() {
        active = false;
        position.set(0, 0);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 16, position.y - 16);
    }

    public void update(float dt) {
        hitArea.x = position.x;
        hitArea.y = position.y;
        position.mulAdd(velocity, dt);
        if (position.x < -10 || position.x > Gdx.graphics.getWidth() + 10 || position.y < -10 || position.y > Gdx.graphics.getHeight() + 10) {
            reset();
        }
    }


}
