package ru.sharovigor.game;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import static java.lang.Math.*;


class BulletEmitter {
    private static final BulletEmitter ourInstance = new BulletEmitter();

    public static BulletEmitter getInstance() {
        return ourInstance;
    }

    final Array<Bullet> actObject = new Array<Bullet>();
    final Pool<Bullet> pool = new Pool<Bullet>(256, 8192) {
        @Override
        protected Bullet newObject() {
            return new Bullet();
        }
    };

    public void reset() {
        pool.clear();
        actObject.clear();
    }


    private BulletEmitter() {

    }


    public void setupBullet(Vector2 position, float angle) {
        Bullet bl = pool.obtain();
        bl.setup(position.x, position.y, 400 * (float) cos(angle), 400 * (float) sin(angle));
        actObject.add(bl);
    }

    public void update(float dt) {
        Bullet bl;
        int len = actObject.size;
        for (int i = len; --i >= 0; ) {
            bl = actObject.get(i);
            bl.update(dt);
            if (!bl.active) {
                actObject.removeIndex(i);
                pool.free(bl);
            }
        }
    }

    public void render(SpriteBatch batch) {
        Bullet bl;
        int len = actObject.size;
        for (int i = len; --i >= 0; ) {
            bl = actObject.get(i);
            bl.render(batch);
        }
    }
}