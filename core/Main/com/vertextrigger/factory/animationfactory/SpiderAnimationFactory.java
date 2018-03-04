package com.vertextrigger.factory.animationfactory;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.vertextrigger.factory.SpriteFactory;

import static com.vertextrigger.util.GameObjectSize.SPIDER_BODY_SIZE;

public class SpiderAnimationFactory extends AbstractAnimationFactory {

    private final SpriteFactory spriteFactory;

    public SpiderAnimationFactory() {
        this.spriteFactory = new SpriteFactory();
    }

    @Override
    protected Animation getStanding() {
        return new Animation(0, spriteFactory.createEnemySprite("spider", SPIDER_BODY_SIZE));
    }

    @Override
    protected Animation getFalling() {
        final Sprite[] movingDownWebSprites = new Sprite[] {
                spriteFactory.createEnemySprite("spider_walk1", SPIDER_BODY_SIZE),
                spriteFactory.createEnemySprite("spider_walk2", SPIDER_BODY_SIZE),
        };

        final Animation movingDownWeb = new Animation(.3f, movingDownWebSprites);
        movingDownWeb.setPlayMode(Animation.PlayMode.LOOP);
        return movingDownWeb;
    }

    @Override
    protected Animation getDeath() {
        final Sprite[] deathSprites = new Sprite[] {
                spriteFactory.createEnemySprite("spider_hit", SPIDER_BODY_SIZE),
                spriteFactory.createEnemySprite("spider_hit", SPIDER_BODY_SIZE),
                spriteFactory.createEnemySprite("spider_walk1", SPIDER_BODY_SIZE),
                spriteFactory.createEnemySprite("spider_walk2", SPIDER_BODY_SIZE),
                spriteFactory.createEnemySprite("spider_walk1", SPIDER_BODY_SIZE),
                spriteFactory.createEnemySprite("spider_walk2", SPIDER_BODY_SIZE),
                spriteFactory.createEnemySprite("spider_dead", SPIDER_BODY_SIZE),
                spriteFactory.createEnemySprite("spider_dead", SPIDER_BODY_SIZE),
                spriteFactory.createEnemySprite("spider_dead", SPIDER_BODY_SIZE),
        };

        final Animation deathAnimation = new Animation(.4f, deathSprites);
        deathAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        return deathAnimation;
    }
}
