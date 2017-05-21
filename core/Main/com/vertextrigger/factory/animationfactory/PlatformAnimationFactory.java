package com.vertextrigger.factory.animationfactory;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.util.GameObjectSize;

public class PlatformAnimationFactory extends AbstractAnimationFactory {
    private final SpriteFactory spriteFactory;
    private final GameObjectSize size;
    private final String platformSprite;

    public PlatformAnimationFactory(final GameObjectSize platformSize, final String platformSprite) {
        this.platformSprite = platformSprite;
        spriteFactory = new SpriteFactory();
        size = platformSize;
    }

    @Override
    protected Animation getMoving() {
        return new Animation(0, spriteFactory.createLevelSprite(platformSprite, size));
    }

    @Override
    protected Animation getStanding() {
        return new Animation(0, spriteFactory.createLevelSprite(platformSprite, size));
    }
}
