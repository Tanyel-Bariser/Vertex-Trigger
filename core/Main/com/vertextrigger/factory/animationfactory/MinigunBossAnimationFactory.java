package com.vertextrigger.factory.animationfactory;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.vertextrigger.factory.SpriteFactory;

import static com.vertextrigger.util.GameObjectSize.MINIGUN_BOSS_BODY_SIZE;

public class MinigunBossAnimationFactory extends AbstractAnimationFactory {

    private final SpriteFactory spriteFactory;

    public MinigunBossAnimationFactory() {
        this.spriteFactory = new SpriteFactory();
    }

    @Override
    protected Animation getStanding() {
        return new Animation(0, spriteFactory.createLevelSprite("SkeletonBoss", MINIGUN_BOSS_BODY_SIZE));
    }

    public Animation getOverheat() {
        return new Animation(0, spriteFactory.createLevelSprite("SkeletonBossOverheat", MINIGUN_BOSS_BODY_SIZE));
    }

    public Animation getStandingPhaseTwo() {
        return new Animation(0, spriteFactory.createLevelSprite("SkeletonBossPhaseTwo", MINIGUN_BOSS_BODY_SIZE));
    }
}
