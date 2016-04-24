package com.vertextrigger.factory.animationfactory;
import static com.vertextrigger.util.GameObjectSize.*;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.util.GameObjectSize;

public class PlayerAnimationFactory extends AbstractAnimationFactory {

    private final SpriteFactory spriteFactory;
    private final GameObjectSize size; 

    public PlayerAnimationFactory() {
        spriteFactory = new SpriteFactory();
        size = PLAYER_SIZE;
    }

    @Override
    protected Animation getMoving() {
        Animation runAnimation = new Animation(0.1F, createSprites("run", 6));
        runAnimation.setPlayMode(Animation.PlayMode.LOOP);
        return runAnimation;
    }
    
    private Sprite[] createSprites(String name, int numOfSprites) {
    	Sprite[] sprites = new Sprite[numOfSprites];
    	for (int i = 0; i < numOfSprites; i++) {
    		sprites[i] = spriteFactory.createCoreSprite(name + i, size);
    	}
    	return sprites;
    }
    
    private Sprite[] createSpritesReverseOrder(String name, int numOfSprites) {
    	Sprite[] sprites = new Sprite[numOfSprites];
    	for (int i = numOfSprites - 1; i >= 0; i--) {
    		sprites[i] = spriteFactory.createCoreSprite(name + i, size);
    	}
    	return sprites;
    }

    @Override
    protected Animation getRising() {
        Animation jumpAnimation = new Animation(.5f, createSprites("jump", 2));
        jumpAnimation.setPlayMode(Animation.PlayMode.LOOP);
        return jumpAnimation;
    }
    
    @Override
    protected Animation getFalling() {
        Animation jumpAnimation = new Animation(.5f, createSpritesReverseOrder("jump", 2));
        jumpAnimation.setPlayMode(Animation.PlayMode.LOOP);
        return jumpAnimation;
    }

    @Override
	protected Animation getStanding() {
        Sprite[] standingSprite = new Sprite[]{
        		//shoot1 sprite is the best image for the player standing
        		spriteFactory.createCoreSprite("shoot1", size),
        };

        Animation jumpAnimation = new Animation(0.1f, standingSprite);
        jumpAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        return jumpAnimation;
    }

    @Override
    protected Animation getShooting() {
        Animation shootAnimation = new Animation(0.02F, createSprites("shoot", 8));
        shootAnimation.setPlayMode(Animation.PlayMode.LOOP);
        return shootAnimation;
    }

    @Override
    protected Animation getDeath() {
        Animation deathAnimation = new Animation(0.1f, createSprites("death", 3));	// showing 3 of 4 frames looks better
        deathAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        return deathAnimation;
    }
}