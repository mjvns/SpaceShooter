package com.mjvns.spaceshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Spaceshooter extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture background;
	private AnimatedSprite animated_missile;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,480);
		batch = new SpriteBatch();
		background = new Texture("Space.png");
		Texture missiletexture = new Texture("Animated_missile.png");
		Sprite missile = new Sprite(missiletexture);
		animated_missile = new AnimatedSprite(missile);
		animated_missile.setPosition(800/2,0);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		animated_missile.draw(batch);
		batch.end();

		handleTouch();
		animated_missile.move();
	}

	private void handleTouch() {
		if(Gdx.input.isTouched()){
			int xTouch = Gdx.input.getX();

			if(xTouch > animated_missile.getX()){
				animated_missile.moveRight();
			}else{
				animated_missile.moveLeft();
			}
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}
