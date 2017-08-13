package com.vertextrigger.entities.mortalplatform;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.entities.AnimatorImpl;
import com.vertextrigger.factory.animationfactory.PlatformAnimationFactory;
import com.vertextrigger.util.GameObjectSize;

/**
 * A {@link MortalPlatform} that fades out to nothing once the player has stepped on it
 *
 * @see com.vertextrigger.collisiondetection.CollisionDetection#postSolve(Contact, ContactImpulse)
 */
public class FadingPlatform extends MortalPlatform {

    final private GameObjectSize size;
    final private World world;

    private float currentAlpha = 1;

    public FadingPlatform(final Body body, final GameObjectSize size, final String sprite, final World world) {
        super(body, new AnimatorImpl(new PlatformAnimationFactory(size, sprite).createAnimationSet()), size);
        this.size = size;
        this.world = world;
        setUserData();
    }

    @Override
    public Sprite update(final float delta, final float alpha) {
        final Sprite sprite = super.update(delta, alpha);

        // if we are 'dead', start fading out by reducing the sprite's alpha
        if (isDead()) {
            sprite.setAlpha(currentAlpha);
            currentAlpha -= 0.0075;
        }
        return sprite;
    }

    @Override
    public boolean isDeathAnimationFinished() {
        // when the sprite is almost transparent, we say our 'death animation' is done
        return currentAlpha < 0.02;
    }
}
