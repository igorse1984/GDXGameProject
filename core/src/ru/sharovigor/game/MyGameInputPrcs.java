package ru.sharovigor.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

/**
 * Created by Игорь on 17.07.2017.
 */

public class MyGameInputPrcs implements InputProcessor {

    // мини-класс, упаковываем до 5 нажатий в хешмап
    class TouchInfo {
        int x;
        int y;
        boolean touched;
    }

    HashMap<Integer, TouchInfo> map = new HashMap<Integer, TouchInfo>();

    public MyGameInputPrcs() {
        for (int i = 0; i < 5; i++) {
            map.put(i, new TouchInfo());
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        map.get(pointer).x = (int) ScreenManager.getInstance().getViewport().unproject(new Vector2(screenX, 0)).x;
        map.get(pointer).y = (int) ScreenManager.getInstance().getViewport().unproject(new Vector2(0, screenY)).y;
        map.get(pointer).touched = true;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        map.get(pointer).x = 0;
        map.get(pointer).y = 0;
        map.get(pointer).touched = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        touchDown(screenX, screenY, pointer, 0);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    // если есть нажатие на заданную область
    public boolean checkTouchAndUptake(int x, int y, int width, int height) {
        for (TouchInfo o : map.values()) {
            if (o.touched) {
                if ((o.x > x && o.x < x + width) && o.y > y && o.y < y + height) {
                    o.touched = false;
                    return true;
                }
            }
        }
        return false;
    }

    // из Хешмапа есть ли нажатие
    public boolean isTouched() {
        for (TouchInfo o : map.values()) {
            if (o.touched) {
                return true;
            }
        }
        return false;
    }

    // из Хешмапа координата Y
    public int getY() {
        for (TouchInfo o : map.values()) {
            if (o.touched) {
                return o.y;
            }
        }
        return -1;
    }

    // из Хешмапа координата X
    public int getX() {
        for (TouchInfo o : map.values()) {
            if (o.touched) {
                return o.x;
            }
        }
        return -1;
    }
}
