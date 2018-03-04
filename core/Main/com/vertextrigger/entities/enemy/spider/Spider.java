package com.vertextrigger.entities.enemy.spider;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Timer;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.AbstractEntity;
import com.vertextrigger.entities.AnimationSet;
import com.vertextrigger.entities.AnimatorImpl;
import com.vertextrigger.entities.Mortal;
import com.vertextrigger.entities.enemy.Enemy;
import com.vertextrigger.util.PositionUtil;

import static com.vertextrigger.util.GameObjectSize.SPIDER_BODY_SIZE;

public class Spider extends AbstractEntity implements Enemy, Mortal {

    static final int TRAVEL_DISTANCE = 2;

    private final float startY;
    private final boolean faceRight;

    private Sprite webSprite;

    public Spider(final Body body, final AnimationSet animationSet, final boolean faceRight) {
        super(Spider.setAtTopOfWeb(body), new AnimatorImpl(animationSet));
        this.startY = body.getPosition().y;
        this.faceRight = faceRight;
    }

    private static Body setAtTopOfWeb(final Body body) {
        body.setTransform(body.getPosition().x, body.getPosition().y + TRAVEL_DISTANCE, body.getAngle());
        body.setLinearVelocity(0, -1);   // make body travel down web until it reaches ultimate position
        return body;
    }

    @Override
    public void die() {
        AudioManager.playEnemyKilledSound();
        animator.playDeathAnimation(this);

        // when spider dies if there's a web remove it from screen by setting its position really far off-screen
        if (webSprite != null) {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    webSprite.setPosition(Float.MIN_VALUE, Float.MIN_VALUE);
                }
            }, 2.5f);
        }
    }

    @Override
    public boolean isFeetOnGround() {
        return false;   // needed to override the default of true and allow fallinganimation to play
    }

    @Override
    public Sprite update(float delta, float alpha) {
        if (PositionUtil.closeEnough(body.getPosition().y, (startY - TRAVEL_DISTANCE))) {
            body.setLinearVelocity(0, 0);   // when spider has reached its ultimate position at end of web, stop it moving
        }

        final Sprite updatedSprite = super.update(delta, alpha);
        if (faceRight) {
            updatedSprite.flip(true, false);
        }
        return updatedSprite;
    }

    @Override
    public float getOffsetX() {
        return SPIDER_BODY_SIZE.getOffsetX();
    }

    @Override
    public float getOffsetY() {
        return SPIDER_BODY_SIZE.getOffsetY();
    }

    public void setWebSprite(Sprite webSprite) {
        this.webSprite = webSprite;
    }
}
