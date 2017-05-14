package com.vertextrigger.levelbuilder;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.MagnetFlowField;
import com.vertextrigger.entities.enemy.Poker;
import com.vertextrigger.factory.EnemyFactory;
import com.vertextrigger.factory.PlatformFactory;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.factory.bodyfactory.PlatformBodyFactory.Friction;
import com.vertextrigger.factory.entityfactory.PlayerFactory;
import com.vertextrigger.inanimate.StaticPlatform;
import com.vertextrigger.inanimate.portal.Portal;
import com.vertextrigger.inanimate.portal.PortalFactory;
import com.vertextrigger.screen.AbstractGameScreen;
import com.vertextrigger.util.GameObjectSize;

import static com.badlogic.gdx.math.MathUtils.degreesToRadians;
import static com.badlogic.gdx.math.MathUtils.radiansToDegrees;
import static com.vertextrigger.inanimate.portal.PortalTeleportation.MOVING_DIFFERENT_XY_AXIS_DIRECTION;
import static com.vertextrigger.inanimate.portal.PortalTeleportation.MOVING_OPPOSITE_HORIZONTAL_DIRECTION;
import static com.vertextrigger.inanimate.portal.PortalTeleportation.MOVING_SAME_DIRECTION;
import static com.vertextrigger.util.GameObjectSize.MEDIUM_PLATFORM_SIZE;
import static com.vertextrigger.util.GameObjectSize.SIGN_SIZE;
import static com.vertextrigger.util.GameObjectSize.SMALL_PLATFORM_SIZE;
import static com.vertextrigger.util.GameObjectSize.WINDOW_SIZE;

public class HughLevelBuilder extends AbstractLevelBuilder {

    private static final int CONTAINER_WIDTH = 8;
    private static final int CONTAINER_HEIGHT = 4;

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
        //player = PlayerFactory.createPlayer(world, new Vector2(-7, -CONTAINER_HEIGHT), screen, magnetFlowField);
        player = PlayerFactory.createPlayer(world, new Vector2(0, 0), screen, magnetFlowField);
    }

    @Override
    public MagnetFlowField createMagnetFlowField() {
        return null;
    }

    @Override
    protected void createEnemies(Steerable<Vector2> target) {
        final Poker poker1 = EnemyFactory.createPokerEnemy(world, new Vector2(7.1f, -2));
        entities.add(poker1);
        screen.addMortal(poker1);

        final Poker poker2 = EnemyFactory.createPokerEnemy(world, new Vector2(-1.5f, 2));
        poker2.getBody().setFixedRotation(false);
        poker2.getBody().setTransform(new Vector2(-1.5f, 1.6f), 180 * degreesToRadians);
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

    @Override
    protected void createStaticPlatforms() {
        final Sprite signRight = spriteFactory.createLevelSprite("signRight", SIGN_SIZE);
        signRight.setPosition(-CONTAINER_WIDTH, -CONTAINER_HEIGHT);
        sprites.add(signRight);

        final Sprite signLeft = spriteFactory.createLevelSprite("signLeft", SIGN_SIZE);
        signLeft.setPosition(7.03f, -1.25f);
        sprites.add(signLeft);

        final Sprite signLeft2 = spriteFactory.createLevelSprite("signLeft", SIGN_SIZE);
        signLeft2.setPosition(-1.5f, 2);
        sprites.add(signLeft2);

        float spikeXPosition = -6;
        for(int i = 0; i < 60; i++) {
            final Sprite spike = spriteFactory.createLevelSprite("stoneCaveSpikeBottom", SIGN_SIZE);
            spike.setPosition(spikeXPosition, -CONTAINER_HEIGHT);
            sprites.add(spike);
            spikeXPosition += 0.2f;
        }

        createStaticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(-6.5f, -3));
        createStaticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(-4.5f, -3));
        createStaticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(-1.5f, -3));
        createStaticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(2.5f, -3));
        createStaticPlatform("snowCenter", MEDIUM_PLATFORM_SIZE, new Vector2(4.5f, -3));
        createStaticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(7, -2));
        createStaticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(5, -1));
        createStaticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(3, -0.5f));
        createStaticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(0, -0));

        createStaticPlatform("snowMid", SMALL_PLATFORM_SIZE, new Vector2(-1.5f, 0.3f), Friction.SNOW, -45);
        createStaticPlatform("snowMid", SMALL_PLATFORM_SIZE, new Vector2(-1.5f, 2), Friction.SNOW, 0);
        createStaticPlatform("snowMid", MEDIUM_PLATFORM_SIZE, new Vector2(-3, 1.3f), Friction.SNOW, 0);
        createStaticPlatform("snowMid", SMALL_PLATFORM_SIZE, new Vector2(-6, 1.3f), Friction.SNOW, 0);



//        final PlatformFactory factory = new PlatformFactory(world);
//        GameObjectSize size = SMALL_PLATFORM_SIZE;
//        float positionX = -2;
//        float positionY = -9;
//        boolean leftToRight = false;
//        Vector2 finalPlatformPosition = new Vector2();
//
//        for (int i = 0; i < 25; i++) {
//            final Vector2 platformPosition = new Vector2(positionX, positionY);
//            if (i == 24) {
//                finalPlatformPosition.set(platformPosition);
//                size = MEDIUM_PLATFORM_SIZE;
//            }
//
//            final StaticPlatform platform = factory.createPlatform("snowCenter", size, platformPosition);
//            platform.setRotation(0);
//            sprites.add(platform.getSprite());
//
//            // change direction every 5 platforms
//            if (i % 5 == 0) {
//                leftToRight ^= true;
//            }
//            float changeInX = size.getPhysicalWidth() * 2.5f;
//            positionX = leftToRight ? positionX + changeInX : positionX - changeInX;
//            positionY += 5f * OBJECT_SIZE;
//        }
//
//        createOtherStaticObjects(finalPlatformPosition);
    }

    private void createStaticPlatform(final String spriteName, final GameObjectSize size, final Vector2 platformPosition) {
        createStaticPlatform(spriteName, size, platformPosition, Friction.NORMAL ,0);
    }

    private void createStaticPlatform(final String spriteName, final GameObjectSize size, final Vector2 platformPosition, final Friction friction, final float rotation) {
        final StaticPlatform platform = platformFactory.createPlatform(spriteName, size, platformPosition, friction);
        platform.setRotation(rotation);
        sprites.add(platform.getSprite());
    }

    private void createOtherStaticObjects(final Vector2 finalPlatformPosition) {
        final SpriteFactory spriteFactory = new SpriteFactory();

        final Sprite signRight = spriteFactory.createLevelSprite("signRight", SIGN_SIZE);
        signRight.setPosition(-CONTAINER_WIDTH, -CONTAINER_HEIGHT);
        sprites.add(signRight);

        final Sprite signExit = spriteFactory.createLevelSprite("signExit", SIGN_SIZE);
        signExit.setPosition(finalPlatformPosition.x + .3f, finalPlatformPosition.y + .08f);
        sprites.add(signExit);

        final Sprite window = spriteFactory.createLevelSprite("window", WINDOW_SIZE);
        window.setPosition(finalPlatformPosition.x - .45f, finalPlatformPosition.y);
        sprites.add(window);
    }

    @Override
    protected void createGroundWalls() {
        super.createGroundWalls(CONTAINER_WIDTH, CONTAINER_HEIGHT);
    }

    @Override
    public Array<Portal> createPortals() {
        final float portalHeight = GameObjectSize.PORTAL_SIZE.getPhysicalHeight();
        final float portalWidth = GameObjectSize.PORTAL_SIZE.getPhysicalWidth();
        final Vector2 portal1Position = new Vector2(0.5f + portalWidth, -CONTAINER_HEIGHT + portalHeight);
        final Vector2 portal2Position = new Vector2(2.5f - portalWidth, -2f + portalHeight);
        final Array<Portal> portalPair1 = PortalFactory.createPortalPair(world, portal1Position, portal2Position, MOVING_SAME_DIRECTION);

        final Vector2 portal3Position = new Vector2(5 + portalWidth, -2.8f + portalHeight);
        final Vector2 portal4Position = new Vector2(7.4f - portalWidth, -1.8f + portalHeight);
        final Array<Portal> portalPair2 = PortalFactory.createPortalPair(world, portal3Position, portal4Position, MOVING_OPPOSITE_HORIZONTAL_DIRECTION);

        final Vector2 portal5Position = new Vector2(-2 + portalWidth, 1.3f + portalHeight);
        final Vector2 portal6Position = new Vector2(-2f + portalWidth, 2.4f + portalHeight);
        final Array<Portal> portalPair3 = PortalFactory.createPortalPair(world, portal5Position, portal6Position, MOVING_SAME_DIRECTION);

        final Array<Portal> portals = new Array<>();
        portals.addAll(portalPair1);
        portals.addAll(portalPair2);
        portals.addAll(portalPair3);

        for (final Portal portal : portals) {
            sprites.add(portal.getSprite());
        }
        return portals;
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
