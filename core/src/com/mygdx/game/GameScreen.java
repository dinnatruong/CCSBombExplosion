package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Dinna on 09/12/2015.
 */
public class GameScreen extends Stage implements Screen {
    private Game g;
    private OrthographicCamera camera;
    TouchPad touchpad;
    BombButton btnBomb;
    Character character;

    public GameScreen(Game game) {
        g = game;
        float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 10f * aspectRatio, 10f);

        character = new Character();
        character.create();
        touchpad = new TouchPad();
        touchpad.setCharacter(character, character.arbDirection, character.bStop);
        btnBomb = new BombButton();

        this.addActor(touchpad.touchpad);
        this.addActor(btnBomb.ibBombDrop);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0.294f, 0.294f, 0.294f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        touchpad.render();
        character.render();
        this.act(Gdx.graphics.getDeltaTime());
        this.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
