package com.vertextrigger.inanimate;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class Ground {
	
	public Ground(World world, Vector2[] corners) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(0, 0);

		ChainShape worldContainerShape = new ChainShape();
		worldContainerShape.createChain(corners);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = worldContainerShape;
		fixDef.friction = 0.9f;
		fixDef.restitution = 0;
		
		Body worldContainer = world.createBody(bodyDef);
		worldContainer.createFixture(fixDef).setUserData(this);

		worldContainerShape.dispose();	
	}
}
