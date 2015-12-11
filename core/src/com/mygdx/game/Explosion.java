package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Dinna on 09/12/2015.
 */
//https://github.com/sfaci/jbombermanx.git
public class Explosion {
    static SpriteBatch batch;
    static TextureAtlas taExplosion;
    static Animation aniExplosion;
    static float elapsedTime;
    static TextureRegion currentFrame;
    static float x, y;

    public enum ExplosionType {
        CENTER, UP, DOWN, LEFT, RIGHT, HORIZONTAL, VERTICAL
    }

    Explosion(float fX, float fY, ExplosionType explosionType) {
        batch = new SpriteBatch();
        x = fX;
        y = fY;
        taExplosion = new TextureAtlas(Gdx.files.internal("Explosion/explosion.pack"));

        String strType = null;

        switch (explosionType) {
            case CENTER:
                strType = "explosion_center";
                break;
            case UP:
                strType = "explosion_up";
                break;
            case DOWN:
                strType = "explosion_down";
                break;
            case LEFT:
                strType = "explosion_left";
                break;
            case RIGHT:
                strType = "explosion_right";
                break;
            case HORIZONTAL:
                strType = "explosion_horizontal";
                break;
            case VERTICAL:
                strType = "explosion_vertical";
                break;
            default:
                break;
        }
        aniExplosion = new Animation(0.2f, taExplosion.findRegions(strType));
        aniExplosion.setPlayMode(Animation.PlayMode.NORMAL);
        currentFrame = aniExplosion.getKeyFrame(0);
    }

    public static void render() {
        batch.begin();
        batch.draw(currentFrame, x, y);
        batch.end();
    }

    public void update() {
        elapsedTime += Gdx.graphics.getDeltaTime();
        currentFrame = aniExplosion.getKeyFrame(elapsedTime, false);
    }
}