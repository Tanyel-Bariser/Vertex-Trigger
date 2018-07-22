package com.vertextrigger.factory.bodyfactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.entities.enemy.minigunboss.MinigunBossHead;
import com.vertextrigger.entities.player.PlayerFeet;
import com.vertextrigger.util.GameObjectSize;

import static com.vertextrigger.util.GameObjectSize.MINIGUN_BOSS_BODY_SIZE;
import static com.vertextrigger.util.GameObjectSize.OBJECT_SIZE;

public class MinigunBossBodyFactory extends AbstractBodyFactory {

    public Body createMinigunBossBody(final World world, final Vector2 initialPosition) {
        final FixtureDef fixtureDefinition = createFixtureDefinition();
        fixtureDefinition.isSensor = true;  // so bullets can pass through and reach the head
        final Body body = createBody(world, initialPosition, BodyDef.BodyType.DynamicBody, fixtureDefinition);
        body.setFixedRotation(false);
        createHead(body);
        createFeet(body);
        return body;
    }

    @Override
    protected FixtureDef createFixtureDefinition() {
        final FixtureDef fixtureDefinition = new FixtureDef();
        fixtureDefinition.shape = createShape();
        fixtureDefinition.density = 8;
        fixtureDefinition.friction = 8;
        return fixtureDefinition;
    }

    @Override
    protected Shape createShape() {
        final PolygonShape shape = new PolygonShape();
        shape.setAsBox(MINIGUN_BOSS_BODY_SIZE.getPhysicalWidth(), MINIGUN_BOSS_BODY_SIZE.getPhysicalHeight());
        return shape;
    }

    private void createHead(final Body body) {
        final CircleShape head = new CircleShape();
        head.setRadius(1f * GameObjectSize.OBJECT_SIZE);
        head.setPosition(new Vector2(0f, MINIGUN_BOSS_BODY_SIZE.getPhysicalHeight() - head.getRadius()));
        final FixtureDef fixtureDef = createFixtureDefinition();
        fixtureDef.shape = head;
        final Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(new MinigunBossHead());
    }

    private void createFeet(final Body body) {
        final FixtureDef fixtureDef = createFixtureDefinition();
        fixtureDef.shape = createFeet();
        final Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(new PlayerFeet());
    }

    private PolygonShape createFeet() {
        final PolygonShape feet = new PolygonShape();
        final Vector2 center = new Vector2(0f, -((MINIGUN_BOSS_BODY_SIZE.getPhysicalHeight() * (OBJECT_SIZE + 0.02f))) * 6);
        final float width = MINIGUN_BOSS_BODY_SIZE.getPhysicalWidth() * 0.9f;
        final float height = 0.001f;

        feet.setAsBox(width, height, center, 0);
        return feet;
    }
}
