package ru.sharovigor.game;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import static java.lang.Math.cos;
import static java.lang.Math.sin;


class AsteroidEmitter {
    private static final AsteroidEmitter ourInstance = new AsteroidEmitter();

    public static AsteroidEmitter getInstance() {
        return ourInstance;
    }

    final Array<Asteroid> actObject = new Array<Asteroid>();
    final Pool<Asteroid> pool = new Pool<Asteroid>(256, 8192) {
        @Override
        protected Asteroid newObject() {
            return new Asteroid();
        }
    };

    public void reset() {
        pool.clear();
        actObject.clear();
    }

    private AsteroidEmitter() {
    }

    public void setupAsteroid() {
        Asteroid ast = pool.obtain();
        actObject.add(ast);
    }

    public void update(float dt) {
        Asteroid ast;
        int len = actObject.size;
        for (int i = len; --i >= 0; ) {
            ast = actObject.get(i);
            ast.update(dt);
            if (!ast.active) {
                actObject.removeIndex(i);
                pool.free(ast);
            }
        }
    }

    public void render(SpriteBatch batch) {
        Asteroid ast;
        int len = actObject.size;
        for (int i = len; --i >= 0; ) {
            ast = actObject.get(i);
            ast.render(batch);
        }
    }
}