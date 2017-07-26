package ru.sharovigor.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;


class Utils {

    static void screenBorder(Vector2 position, Texture texture) {
        // условия пролета за экран
        if (position.x < -texture.getWidth() / 2) {
            position.x = Gdx.graphics.getWidth() + texture.getWidth() / 2;
        }
        if (position.x > Gdx.graphics.getWidth() + texture.getWidth() / 2) {
            position.x = -texture.getWidth() / 2;
        }
        if (position.y > Gdx.graphics.getHeight() + texture.getHeight() / 2) {
            position.y = -texture.getHeight() / 2;
        }
        if (position.y < -texture.getHeight() / 2) {
            position.y = Gdx.graphics.getHeight() + texture.getHeight() / 2;
        }

//        return new Vector2(position.x, position.y);
    }

    static void checkCollision(SpaceObj spcObj, BulletEmitter bltEmi) {
//        float healt = healtBase;
        for (Bullet blt : BulletEmitter.getInstance().actObject) {
            // вычисляем расстояние между обьектами
//            if (position.cpy().sub(o.position).len() < 70) {
            if (spcObj.hitArea.overlaps(blt.hitArea)) {
                // придаем космическим обьектам скорость при ударении
                Vector2 acc = spcObj.position.cpy().sub(blt.position).nor();
                spcObj.velocity.mulAdd(acc, 20);
                blt.velocity.mulAdd(acc, -20);
//                healt -= 0.1;

                // здесь можно добавить индикатор здоровья
                // ...
            }
        }
//        return healt;
    }

    private static float fireCounter = 0;

    static float keyManagement(Human spaceObj, Vector2 velocity, float firstVelocity, float angle, float angleVelocity, float fireRate, float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            velocity.add((float) (firstVelocity * cos(angle) * dt), (float) (firstVelocity * sin(angle) * dt));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            angle += angleVelocity * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            angle -= angleVelocity * dt;
        }
        if (((MyGameInputPrcs) Gdx.input.getInputProcessor()).checkTouchAndUptake(1280 - 256 - 20, 20, 256, 256) || Gdx.input.isKeyPressed(Input.Keys.L)) {
            fireCounter += dt;
            if (fireCounter > fireRate) {
                fireCounter = 0;
                spaceObj.fire();
            }
        }
        return angle;
    }

    // лететь к курсору
//        if (Gdx.input.isTouched()) {
//            float mx = Gdx.input.getX();
//            float my = Gdx.graphics.getHeight() - Gdx.input.getY();
//           m = new Vector2(mx, my);
//            // рассчет вектора между кораблем и курсором
//            velocity = m.cpy().sub(position).nor().scl(500);
//        }

    public static float normalizeAngle(float angle) {
        // уменьшение запредельного угла
        if (angle > 2 * PI)
            angle = 0;
        else if (angle < -(2 * PI))
            angle = 0;
        return angle;
    }

    public static float rotateTo(float from, float to, float rotationSpeed, float dt) {
        if (from > to) {
            if (from - to < PI) {
                from -= rotationSpeed * dt;
            } else {
                from += rotationSpeed * dt;
            }
        }
        if (from < to) {
            if (to - from < PI) {
                from += rotationSpeed * dt;
            } else {
                from -= rotationSpeed * dt;
            }
        }
        return from;
    }
}
