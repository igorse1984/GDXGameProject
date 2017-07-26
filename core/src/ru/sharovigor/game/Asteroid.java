package ru.sharovigor.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

class Asteroid extends SpaceObj {
    float scaleXY;
    float spin;
    boolean flipX, flipY;
    boolean active;


    public Asteroid() {
        super("asteroid.png", new Vector2((float) Math.random() * Gdx.graphics.getWidth(), (float) Math.random() * Gdx.graphics.getHeight()));
        velocity = new Vector2((float) ((Math.random() - 0.5f) * 400), (float) (Math.random() - 0.5f) * 400);
        angle = (float) Math.random() * 360;
        spin = (float) (Math.random() - 0.5f) * 5;
        scaleXY = 0.1f + (float) Math.random() * 0.5f;
        if (Math.random() - 0.5f < 0) flipX = true;
        if (Math.random() - 0.5f < 0) flipY = true;
        active = true;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - texture.getWidth() / 2, position.y - texture.getHeight() / 2, texture.getWidth() / 2, texture.getHeight() / 2, texture.getWidth(), texture.getHeight(), scaleXY, scaleXY, angle, 0, 0, texture.getWidth(), texture.getHeight(), flipX, flipY);
    }


    @Override
    public void update(float dt) {
        super.update(dt);
        angle += spin;
        // корректировка на движение корабля игрока
//        position.mulAdd(h.velocity, -0.002f);
        // условия пролета за экран
        Utils.screenBorder(position, texture);
    }
}
