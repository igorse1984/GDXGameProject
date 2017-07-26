package ru.sharovigor.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

abstract class SpaceShip extends SpaceObj {


    Vector2 m;
    float firstVelocity, angleVelocity;
    float fireRate, fireCounter;

    public SpaceShip(String TEXTURE_NAME, Vector2 position) {
        super(TEXTURE_NAME, position);
        angleVelocity = 3.14f;
        fireCounter = 0;
        fireRate = 0.05f;
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        // инерция корабля
        velocity.scl(0.98f);

        // обсчет столкновений с астеройдами (старая версия)
//        healt = Utils.boomAsteroid(asteroids, position, velocity, healt);
    }


    // Метод, который занимается выстреливанием пули
    public void fire() {
        BulletEmitter.getInstance().setupBullet(position, angle);
    }
}

