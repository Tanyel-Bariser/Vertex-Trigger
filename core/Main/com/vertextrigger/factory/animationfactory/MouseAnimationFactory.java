package com.vertextrigger.factory.animationfactory;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.vertextrigger.factory.SpriteFactory;

import static com.vertextrigger.util.GameObjectSize.MOUSE_BODY_SIZE;

public class MouseAnimationFactory extends AbstractAnimationFactory {

    private final SpriteFactory spriteFactory;

    public MouseAnimationFactory() {
        this.spriteFactory = new SpriteFactory();
    }

    @Override
    protected Animation getStanding() {
        return new Animation(0, spriteFactory.createEnemySprite("mouse", MOUSE_BODY_SIZE));
    }

    @Override
    protected Animation getMoving() {
        return new Animation(0, spriteFactory.createEnemySprite("mouse_walk", MOUSE_BODY_SIZE));
    }

    @Override
    protected Animation getDeath() {
        final Sprite[] deathSprites = new Sprite[] {
                spriteFactory.createEnemySprite("mouse_hit", MOUSE_BODY_SIZE),
                spriteFactory.createEnemySprite("mouse_dead", MOUSE_BODY_SIZE),
                spriteFactory.createEnemySprite("mouse_dead", MOUSE_BODY_SIZE),
                spriteFactory.createEnemySprite("mouse_dead", MOUSE_BODY_SIZE),
        };

        // mouse turns upside when it dies :)
        for (int i = 0; i < deathSprites.length; i++) {
            if (i != 0) {
                deathSprites[i].flip(false, true);
            }
        }

        final Animation deathAnimation = new Animation(.4f, deathSprites);
        deathAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        return deathAnimation;
    }
}
