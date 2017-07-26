package ru.sharovigor.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.*;


class Bot extends SpaceShip {

    float anyCounter, anyRate;
    float angleRnd;
    Vector2 velocityRnd;


    public Bot() {
        super("bot.png", new Vector2((float) (Gdx.graphics.getWidth() * Math.random()), (float) (Gdx.graphics.getHeight() * Math.random())));
        velocity = new Vector2(0f, 0f);
        firstVelocity = 100f;
        anyCounter = 0;
        anyRate = 0.5f;
        angleRnd = 0;
        velocityRnd = new Vector2();

    }

    public void update(Human hm, float dt) {
        super.update(dt);

        anyCounter += dt;
        // срабатывает раз в N сек
        if (anyCounter >= 3) {
            anyCounter = 0;
            angleRnd = (float) ((Math.random() - 0.5f) * 2 * PI);
        }

        // нормалайз угла
        angle = Utils.normalizeAngle(angle);
        // повороты
        angle = Utils.rotateTo(angle, angleRnd, angleVelocity, dt);

        // движение
//        angle = velocityRnd.angleRad();
        velocity.add((float) (firstVelocity * cos(angle) * dt), (float) (firstVelocity * sin(angle) * dt));

        // корректировка на движение корабля игрока
        position.mulAdd(hm.velocity, -0.002f);

        // выстрелы
        fireCounter += dt;
        if (fireCounter > fireRate) {
            fireCounter = 0;
            if (Math.random() <= 0.1f) fire();
        }
    }

    public void dispose() {
        texture.dispose();
    }

}