package com.mygdx.game;

import com.badlogic.gdx.Game;

public class CCSBombExplosion extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}
	public void render() {
		super.render();
	}
}