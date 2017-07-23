package com.vertextrigger.entities.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.inanimate.StaticPlatform;

import static com.vertextrigger.util.GameObjectSize.SPIKE_SIZE;

public class Spike extends StaticPlatform implements Enemy {

    final private Body body;
    final private Sprite sprite;

    public Spike(final Sprite sprite, final Body body) {
        super(sprite, body);
        this.sprite = sprite;
        this.body = body;
    }

    public void setSpritePosition() {
        sprite.setPosition(body.getPosition().x - (sprite.getWidth() / SPIKE_SIZE.getOffsetX()), body.getPosition().y
                - (sprite.getHeight() / SPIKE_SIZE.getOffsetY()));
    }

    @Override
    public void setDead() {
        // no-op
        // todo maybe reconsider this being an enemy
    }
}
