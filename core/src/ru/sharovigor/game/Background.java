package ru.sharovigor.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

class Background {

    private static Texture texture;
    private static Texture textureStar;
    private int textureWidth, textureHeight;
    private Star[] stars;

    private class Star {
        Vector2 position, velocity;
        float scl;
        int gdxWidth, gdxHeight;

        Star() {
            position = new Vector2((float) Math.random() * Gdx.graphics.getWidth(), (float) Math.random() * Gdx.graphics.getHeight());
            velocity = new Vector2((float) (Math.random() - 0.5) * 5f, (float) (Math.random() - 0.5) * 5f);
            scl = (float) Math.random() * 0.2f;
            gdxWidth = Gdx.graphics.getWidth();
            gdxHeight = Gdx.graphics.getHeight();

            textureWidth = textureStar.getWidth() / 2;
            textureHeight = textureStar.getHeight() / 2;
        }

        public void update(Human human, float dt) {
            position.mulAdd(velocity, dt);
            position.mulAdd(human.velocity, -0.002f);
            // условия пролета за экран
//            Utils.screenBorder(position, textureStar);
        }
    }

    Background() {
        if (texture == null && textureStar == null) {
            texture = new Texture("background1.jpg");
            textureStar = new Texture("star.png");
        }
        stars = new Star[250];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star();
        }
    }

    void render(SpriteBatch batch) {
        batch.draw(texture, 0, 0);
        for (Star st : stars) {
            batch.draw(textureStar, st.position.x - textureWidth, st.position.y - textureHeight, textureWidth, textureHeight, textureWidth * 2, textureHeight * 2, st.scl, st.scl, 0, 0, 0, textureWidth * 2, textureHeight * 2, false, false);
        }
    }

    public void update(Human human, float dt) {
        for (Star star : stars) {
            star.update(human, dt);
        }
    }

    void dispose() {
        texture.dispose();
    }
}
