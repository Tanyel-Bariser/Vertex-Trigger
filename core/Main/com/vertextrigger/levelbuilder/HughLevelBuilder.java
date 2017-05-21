package com.vertextrigger.levelbuilder;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.MagnetFlowField;
import com.vertextrigger.entities.enemy.Poker;
import com.vertextrigger.factory.EnemyFactory;
import com.vertextrigger.factory.PlatformFactory;
import com.vertextrigger.factory.bodyfactory.PlatformBodyFactory.Friction;
import com.vertextrigger.factory.entityfactory.PlayerFactory;
import com.vertextrigger.inanimate.StaticPlatform;
import com.vertextrigger.inanimate.portal.Portal;
import com.vertextrigger.inanimate.portal.PortalFactory;
import com.vertextrigger.inanimate.portal.PortalTeleportation;
import com.vertextrigger.screen.AbstractGameScreen;
import com.vertextrigger.util.GameObjectSize;

import static com.badlogic.gdx.math.MathUtils.degreesToRadians;
import static com.vertextrigger.inanimate.portal.PortalTeleportation.MOVING_OPPOSITE_HORIZONTAL_DIRECTION;
import static com.vertextrigger.inanimate.portal.PortalTeleportation.MOVING_SAME_DIRECTION;
import static com.vertextrigger.util.GameObjectSize.MEDIUM_PLATFORM_SIZE;
import static com.vertextrigger.util.GameObjectSize.SIGN_SIZE;
import static com.vertextrigger.util.GameObjectSize.SMALL_PLATFORM_SIZE;
import static com.vertextrigger.util.GameObjectSize.TINY_PLATFORM_SIZE;

public class HughLevelBuilder extends AbstractLevelBuilder {

    private static final int CONTAINER_WIDTH = 8;
    private static final int CONTAINER_HEIGHT = 8;

    private final AbstractGameScreen screen;
    private final PlatformFactory platformFactory;
    static private boolean foo = true;

    public HughLevelBuilder(final World world, final AbstractGameScreen screen) {
        super(world, screen, CONTAINER_WIDTH, CONTAINER_HEIGHT);
        this.screen = screen;
        this.platformFactory = new PlatformFactory(world);
        AudioManager.playLevelOneMusic();
        if (foo) AudioManager.toggleMute();
        foo = false;
        //player = PlayerFactory.createPlayer(world, new Vector2(fromLeft(1), fromBottom(0), screen, magnetFlowField);
        player = PlayerFactory.createPlayer(world, new Vector2(fromLeft(2), fromBottom(5.3f)), screen, magnetFlowField);
    }

    @Override
    public MagnetFlowField createMagnetFlowField() {
        return null;
    }

    @Override
    protected void createEnemies(Steerable<Vector2> target) {
        final Poker poker1 = EnemyFactory.createPokerEnemy(world, new Vector2(fromLeft(15.1f), fromBottom(2)));
        entities.add(poker1);
        screen.addMortal(poker1);

        final Poker poker2 = EnemyFactory.createPokerEnemy(world, new Vector2(fromLeft(6.5f), fromBottom(6)));
        poker2.getBody().setFixedRotation(false);
        poker2.getBody().setTransform(new Vector2(fromLeft(6.5f), fromBottom(5.6f)), 180 * degreesToRadians);
        poker2.getBody().setFixedRotation(true);
        poker2.getBody().setGravityScale(0);
        entities.add(poker2);
        screen.addMortal(poker2);

        //final Bee bee = EnemyFactory.createBeeEnemy(world, new Vector2(0, 1), player.getSteerable(), screen, magnetFlowField);
        //entities.add(bee);
        //screen.addMortal(bee);
    }

    @Override
    protected void createDangerousBalls() {

    }

    @Override
    protected void createMovingPlatforms() {

    }

    @Override
    protected void createTimedPlatforms() {

    }

    private static float fromBottom(final float distanceFromFloor) {
        return -CONTAINER_HEIGHT + distanceFromFloor;
    }

    private static float fromLeft(final float distanceFromLeftWall) {
        return -CONTAINER_WIDTH + distanceFromLeftWall;
    }

    @Override
    protected void createStaticPlatforms() {
        createSign(false, fromLeft(0), fromBottom(0));
        createSign(true, fromLeft(15.03f), fromBottom(2.75f));
        createSign(true, fromLeft(6.5f), fromBottom(6));
        createSpikes(fromLeft(2));

        createStaticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(1.5f), fromBottom(1)));
        createStaticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(3.5f), fromBottom(1)));
        createStaticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(5.5f), fromBottom(1)));
        createStaticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(10.5f), fromBottom(1)));
        createStaticPlatform("snowCenter", MEDIUM_PLATFORM_SIZE, new Vector2(fromLeft(12.5f), fromBottom(1)));
        createStaticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(15), fromBottom(2)));
        createStaticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(13), fromBottom(3)));
        createStaticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(11), fromBottom(3.5f)));
        createStaticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(8), fromBottom(4)));

        createStaticPlatform("snowMid", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(6.5f), fromBottom(4.3f)), Friction.SNOW, -45);
        createStaticPlatform("snowMid", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(6.5f), fromBottom(6)), Friction.SNOW, 0);
        createStaticPlatform("snowMid", MEDIUM_PLATFORM_SIZE, new Vector2(fromLeft(5), fromBottom(5.3f)), Friction.SNOW, 0);
        createStaticPlatform("snowMid", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(2), fromBottom(5.3f)), Friction.SNOW, 0);

        createStaticPlatform("snowCenter", TINY_PLATFORM_SIZE, new Vector2(fromLeft(1), fromBottom(6.3f)));
        createStaticPlatform("snowCenter", TINY_PLATFORM_SIZE, new Vector2(fromLeft(1.5f), fromBottom(7.3f)));
        createStaticPlatform("snowCenter", TINY_PLATFORM_SIZE, new Vector2(fromLeft(2), fromBottom(8.3f)));
        createStaticPlatform("snowCenter", TINY_PLATFORM_SIZE, new Vector2(fromLeft(2.5f), fromBottom(9.3f)));
    }

    private void createSpikes(float startX) {
        for(int i = 0; i < 60; i++) {
            final Sprite spike = spriteFactory.createLevelSprite("stoneCaveSpikeBottom", SIGN_SIZE);
            spike.setPosition(startX, fromBottom(0));
            sprites.add(spike);
            startX += 0.2f;
        }
    }

    private void createSign(boolean leftFacing, float x, float y) {
        final Sprite signRight = spriteFactory.createLevelSprite(leftFacing ? "signLeft" : "signRight", SIGN_SIZE);
        signRight.setPosition(x, y);
        sprites.add(signRight);
    }

    private void createStaticPlatform(final String spriteName, final GameObjectSize size, final Vector2 platformPosition) {
        createStaticPlatform(spriteName, size, platformPosition, Friction.NORMAL ,0);
    }

    private void createStaticPlatform(final String spriteName, final GameObjectSize size, final Vector2 platformPosition, final Friction friction, final float rotation) {
        final StaticPlatform platform = platformFactory.createPlatform(spriteName, size, platformPosition, friction);
        platform.setRotation(rotation);
        sprites.add(platform.getSprite());
    }

    @Override
    protected void createGroundWalls() {
        super.createGroundWalls(CONTAINER_WIDTH, CONTAINER_HEIGHT);
    }

    @Override
    public Array<Portal> createPortals() {
        final Array<Portal> portals = new Array<>();
        portals.addAll(createPortalPair(fromLeft(8.4f), fromBottom(0.2f), fromLeft(10.4f), fromBottom(2.2f), MOVING_SAME_DIRECTION));
        portals.addAll(createPortalPair(fromLeft(13.1f), fromBottom(1.4f), fromLeft(15.3f), fromBottom(2.4f), MOVING_OPPOSITE_HORIZONTAL_DIRECTION));
        portals.addAll(createPortalPair(fromLeft(6.1f), fromBottom(5.5f), fromLeft(6.1f), fromBottom(6.6f), MOVING_SAME_DIRECTION));
        return portals;
    }

    private Array<Portal> createPortalPair(final float x1, final float y1, final float x2, final float y2, final PortalTeleportation type) {
        final Array<Portal> portalPair = PortalFactory.createPortalPair(world, new Vector2(x1, y1), new Vector2(x2, y2), type);
        sprites.add(portalPair.get(0).getSprite());
        sprites.add(portalPair.get(1).getSprite());
        return portalPair;
    }

    @Override
    public void createPowerUps() {

    }

    @Override
    public void resetLevelLayout() {

    }

    @Override
    public Sprite getBackground() {
        return null;
    }
}
