package com.vertextrigger.entities.enemy.minigunboss;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.AbstractEntity;
import com.vertextrigger.entities.AnimationSet;
import com.vertextrigger.entities.AnimatorImpl;
import com.vertextrigger.entities.Mortal;
import com.vertextrigger.entities.bullet.Bullet;
import com.vertextrigger.entities.enemy.Enemy;
import com.vertextrigger.factory.animationfactory.MinigunBossAnimationFactory;
import com.vertextrigger.factory.entityfactory.BulletFactory;
import com.vertextrigger.screen.AbstractGameScreen;

import static com.vertextrigger.util.GameObjectSize.MINIGUN_BOSS_BODY_SIZE;
import static com.vertextrigger.util.GameObjectSize.MINIGUN_BULLET_SIZE;

public class MinigunBoss extends AbstractEntity implements Enemy, Mortal {

    final static private float SHOT_FREQUENCY = 0.25f;
    final static private float SHOT_FREQUENCY_PHASE_2 = 2;
    final static private float SHOT_SCALAR = 0.005f;
    final static private float SHOT_SCALAR_PHASE_2 = 0.0005f;
    final static private int SHOTS_BEFORE_COOLDOWN = 15;
    final static private int SHOTS_DURING_COOLDOWN = 10;

    private boolean isFinalPhase;
    private boolean isCooldownPeriod;
    private int shotsElapsed;
    private int hitsTaken;
    private float timeElapsed;
    private final MinigunBossAnimationFactory animationFactory;
    private final AbstractGameScreen screen;
    private final BulletFactory bulletFactory;
    private final Vector2 playerPosition;

    // save on object instantiations by making some vars global
    private final Vector2 shotVelocity;
    private final Sprite muzzleFlash;
    private final Sprite overheatSmoke;
    private final Sprite damageCircle;

    public MinigunBoss(final Body body, final AnimationSet animationSet, final AbstractGameScreen screen, final BulletFactory bulletFactory, final Vector2 playerPosition) {
        super(body, new AnimatorImpl(animationSet));
        this.animationFactory = new MinigunBossAnimationFactory();
        this.screen = screen;
        this.bulletFactory = bulletFactory;
        this.playerPosition = playerPosition;
        this.shotVelocity = new Vector2();
        this.muzzleFlash = bulletFactory.createMinigunMuzzleFlash();
        this.overheatSmoke = bulletFactory.createOverheatSmoke();
        this.damageCircle = bulletFactory.createDamageCircle();
    }

    void onHeadHit(final Vector2 bulletPosition) {
        damageCircle.setPosition(bulletPosition.x + MINIGUN_BULLET_SIZE.getSpriteWidth(), bulletPosition.y);
        damageCircle.setScale(2);
        AbstractGameScreen.addTemporarySprite(damageCircle, 25);
        AudioManager.playEnemyKilledSound();

        if (!isFinalPhase && ++hitsTaken == 5) {
            isFinalPhase = true;
            animator.lockAnimation(animationFactory.getStandingPhaseTwo());
            hitsTaken = 0;
        }
        else if (isFinalPhase && ++hitsTaken == 3) {
            onDie();
            animator.unlockAnimation();
            setDead();
        }
    }

    void onDie() {
        // 1 position in center of body and then 1 at left, right, up, down offsets
        final Vector2[] deathDamagePositions = new Vector2[] {
                this.getPosition(),
                new Vector2(this.getPosition()).add(.15f, 0),
                new Vector2(this.getPosition()).add(-.15f, 0),
                new Vector2(this.getPosition()).add(0, .5f),
                new Vector2(this.getPosition()).add(0, -.5f),
        };

        for (final Vector2 deathDamagePosition : deathDamagePositions) {
            final Sprite sprite = bulletFactory.createDamageCircle();
            sprite.setScale(2);
            sprite.setPosition(deathDamagePosition.x, deathDamagePosition.y);
            AbstractGameScreen.addTemporarySprite(sprite, 25);
        }
        AudioManager.playEnemyKilledSound();
    }

    @Override()
    public Sprite update(final float delta, final float alpha) {
        timeElapsed += delta;

        if (timeElapsed >= (isFinalPhase ? SHOT_FREQUENCY_PHASE_2 :SHOT_FREQUENCY)) {
            shoot();
            timeElapsed = 0;
        }

        return super.update(delta, alpha);
    }

    private void shoot() {
        if (isCooldownPeriod && !isFinalPhase) {
            animator.lockAnimation(animationFactory.getOverheat());
            overheatSmoke.setPosition(muzzleFlash.getX(), muzzleFlash.getY());
            overheatSmoke.setScale(2);
            AbstractGameScreen.addTemporarySprite(overheatSmoke);

            if (++shotsElapsed == SHOTS_DURING_COOLDOWN) {
                isCooldownPeriod = false;
                shotsElapsed = 0;
                animator.unlockAnimation();
            }
        }
        else {
            doShoot();
            if (++shotsElapsed == SHOTS_BEFORE_COOLDOWN) {
                isCooldownPeriod = true;
                shotsElapsed = 0;
            }
        }
    }

    private void doShoot() {
        final Bullet bullet = bulletFactory.createMinigunBullet();
        bullet.getBody().setActive(true);

        final Vector2 bulletOrigin = getPosition().add(-0.5f, 0.2f);
        bullet.setPosition(bulletOrigin);
        bullet.cachePosition();

        bullet.shoot(calculateBulletVelocity(bulletOrigin));
        AudioManager.playBeeShootSound();
        screen.addEntity(bullet);

        muzzleFlash.setPosition(bulletOrigin.x - 0.15f, bulletOrigin.y - 0.05f);
        muzzleFlash.setScale(2);
        AbstractGameScreen.addTemporarySprite(muzzleFlash);
    }

    // shoot straight ahead unless in final phase where we target player
    private Vector2 calculateBulletVelocity(final Vector2 bulletOrigin) {
        if (!isFinalPhase) {
            shotVelocity.set(-1, 0);
        }
        else {
            shotVelocity.set(playerPosition).sub(bulletOrigin).nor();
        }
        return shotVelocity.scl(isFinalPhase ? SHOT_SCALAR_PHASE_2 : SHOT_SCALAR);
    }

    @Override
    public void die() {
        AudioManager.playEnemyKilledSound();
        animator.playDeathAnimation(this);
    }

    @Override
    public void setUserData() {
        body.setUserData(this);
        for (final Fixture fix : body.getFixtureList()) {
            if (fix.getUserData() instanceof MinigunBossHead) {
                ((MinigunBossHead) fix.getUserData()).setMinigunBoss(this);
            } else {
                fix.setUserData(this);
            }
        }
    }

    @Override
    public float getOffsetX() {
        return MINIGUN_BOSS_BODY_SIZE.getOffsetX();
    }

    @Override
    public float getOffsetY() {
        return MINIGUN_BOSS_BODY_SIZE.getOffsetY();
    }
}
