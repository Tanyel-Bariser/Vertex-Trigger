package com.vertextrigger.factory.bodyfactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import static com.vertextrigger.util.GameObjectSize.SPIDER_BODY_SIZE;

public class SpiderBodyFactory extends AbstractBodyFactory {

    public Body createSpiderBody(final World world, final Vector2 initialPosition) {
        final Body body = createBody(world, initialPosition, BodyDef.BodyType.DynamicBody, createFixtureDefinition());
        body.setFixedRotation(true);
        body.setGravityScale(0);
        return body;
    }

    @Override
    protected FixtureDef createFixtureDefinition() {
        final FixtureDef fixtureDefinition = new FixtureDef();
        fixtureDefinition.shape = createShape();
        fixtureDefinition.density = 100;
        fixtureDefinition.friction = 1.5f;
        return fixtureDefinition;
    }

    @Override
    protected Shape createShape() {
        final PolygonShape shape = new PolygonShape();
        shape.setAsBox(SPIDER_BODY_SIZE.getPhysicalWidth(), SPIDER_BODY_SIZE.getPhysicalHeight());
        return shape;
    }
}
