package com.mjvns.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class AnimatedSprite {
    private static final int FRAMES_COL = 2;
    private static final int FRAMES_ROW = 2;
    private static final int ship_speed = 5;

    private Sprite sprite;
    private Animation animation;
    private TextureRegion frames[];
    private TextureRegion currentFrame;

    private float stateTime;
    private Vector2 velocity = new Vector2();


    public AnimatedSprite(Sprite sprite){
        this.sprite = sprite;
        Texture texture = sprite.getTexture();
        TextureRegion temp[][] = TextureRegion.split(texture,(int)getSpritewidth(),texture.getHeight()/FRAMES_ROW);
        frames = new TextureRegion[FRAMES_COL*FRAMES_ROW];
        int index = 0;
        for(int i=0;i<FRAMES_ROW;i++){
            for(int j=0;j<FRAMES_COL;j++){
                frames[index++] = temp[i][j];
            }
        }
        animation = new Animation(0.1f, frames);
        stateTime = 0f;
    }

    public void draw(SpriteBatch spritebatch){
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = (TextureRegion) animation.getKeyFrame(stateTime,true);

        spritebatch.draw(currentFrame,sprite.getX(),sprite.getY());
    }

    public void setPosition(float x, float y){
        sprite.setPosition(x-getSpriteCenterOffset(),y);
    }

    public float getSpriteCenterOffset(){
        return getSpritewidth()/2;
    }

    private float getSpritewidth() {
        return sprite.getWidth()/FRAMES_COL;
    }

    public void moveRight(){
        velocity = new Vector2(ship_speed,0);
    }

    public void moveLeft(){
        velocity = new Vector2(-ship_speed,0);
    }

    public int getX() {
        return (int)(sprite.getX() + getSpriteCenterOffset());
    }

    public void move() {
        int xMovement = (int) (velocity.x + Gdx.graphics.getDeltaTime());
        sprite.setPosition(sprite.getX()+xMovement, 0);
    }
}
