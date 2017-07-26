package ru.sharovigor.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;


class Human extends SpaceShip {

    public Human() {
        super("spaceship.png", new Vector2((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2));
//        texture = new Texture("spaceship.png");
        velocity = new Vector2(0f, 0f);
        firstVelocity = 500f;
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        // обработка тача
        if (GamePad.getInstance().rend) {
            // необходимо исправить! координаты центра джойствика должны быть в джойстике
            m1 = new Vector2(128, 128);
            // рассчет вектора между произвольным центром и курсором
            velocity = GamePad.getInstance().position.cpy().sub(m1).nor().scl(500);
            angle = velocity.angleRad();
        }
        // обработка нажатий
        angle = Utils.keyManagement(this, velocity, firstVelocity, angle, angleVelocity, fireRate, dt);

        // обсчет пролета за экран
        Utils.screenBorder(position, texture);
    }

    // Метод, который занимается выстреливанием пули
    @Override
    public void fire() {
        for (float i = -0.1f; i <= 0.1f; i += 0.05f) {
            BulletEmitter.getInstance().setupBullet(position, angle + i);
        }
    }
}