package com.vertextrigger.entities.enemy.minigunboss;

import com.badlogic.gdx.math.Vector2;
import com.vertextrigger.collisiondetection.Collidable;
import com.vertextrigger.entities.enemy.Enemy;

public class MinigunBossHead implements Collidable, Enemy {

    private MinigunBoss minigunBoss;

    @Override
    public void setUserData() {
        throw new UnsupportedOperationException("Cannot MinigunBossHead#setUserData(Body body)");
    }

    void setMinigunBoss(final MinigunBoss minigunBoss) {
        this.minigunBoss = minigunBoss;
    }

    public void onHit(final Vector2 bulletPosition) {
        this.minigunBoss.onHeadHit(bulletPosition);
    }

    @Override
    public void setDead() {
        minigunBoss.setDead();
    }
}
