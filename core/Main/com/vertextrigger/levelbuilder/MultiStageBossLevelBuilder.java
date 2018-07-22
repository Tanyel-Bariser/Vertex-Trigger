package com.vertextrigger.levelbuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.MagnetFlowField;
import com.vertextrigger.entities.callback.PositionCallback;
import com.vertextrigger.entities.callback.Runnable;
import com.vertextrigger.entities.enemy.minigunboss.MinigunBoss;
import com.vertextrigger.factory.BackgroundFactory;
import com.vertextrigger.factory.EnemyFactory;
import com.vertextrigger.factory.entityfactory.PlayerFactory;
import com.vertextrigger.factory.entityfactory.ShieldFactory;
import com.vertextrigger.inanimate.portal.Portal;
import com.vertextrigger.screen.AbstractGameScreen;

public class MultiStageBossLevelBuilder extends AbstractLevelBuilder {

    private static final int CONTAINER_WIDTH = 4;
    private static final int CONTAINER_HEIGHT = 3;

    public MultiStageBossLevelBuilder(final World world, final AbstractGameScreen screen) {
        super(world, screen, CONTAINER_WIDTH, CONTAINER_HEIGHT);
        player = PlayerFactory.createPlayer(world, new Vector2(fromLeft(0.75f), fromBottom(3)), screen, magnetFlowField);
        setupBoss();
        //AudioManager.toggleMute();
    }

    private void setupBoss() {
        player.addCallbacks(new PositionCallback(new Runnable() {
            @Override
            public void run() {
                final MinigunBoss boss = EnemyFactory.createMinigunBossEnemy(world, new Vector2(fromLeft(6), fromBottom(3)), player.getPosition(), gameScreen);
                entities.add(boss);
                gameScreen.addMortal(boss);
            }
        }, new Vector2(fromLeft(3.5f), fromBottom(0)), player.getPosition()));
    }

    @Override
    protected void createDangerousBalls() {

    }

    @Override
    protected void createEnemies(Steerable<Vector2> target) {

    }

    @Override
    protected void createGroundWalls() {
        super.createGroundWalls(CONTAINER_WIDTH, CONTAINER_HEIGHT);

    }

    @Override
    public MagnetFlowField createMagnetFlowField() {
        return null;
    }

    @Override
    protected void createMovingPlatforms() {

    }

    @Override
    public Array<Portal> createPortals() {
        return null;
    }

    @Override
    public void createPowerUps() {
        ShieldFactory.createShield(world, new Vector2(fromLeft(0.75f), fromBottom(1.5f)), gameScreen);
    }

    @Override
    protected void createStaticPlatforms() {
        createSign("signRight", fromLeft(0), fromBottom(0));
    }

    @Override
    protected void createTimedPlatforms() {

    }

    @Override
    public Sprite getBackground() {
        final Sprite background = BackgroundFactory.getLevelOneBackground();
        background.setPosition(fromLeft(0), fromBottom(0));
        // todo find a better way of scaling this. i dunno why /80 works and it'll be specific to this level's container size anyway
        background.setSize(Gdx.graphics.getWidth() / 80, Gdx.graphics.getHeight() / 80);
        return background;
    }

    @Override
    public void resetLevelLayout() {

    }
}
