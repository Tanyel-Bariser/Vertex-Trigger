package com.vertextrigger.inanimate;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.portal.PortalFactory;

public class PortalFactoryTest {
	
	PortalFactory factory;
	Vector2 PORTAL_1_POSITION, PORTAL_2_POSITION;
	World world;

	@Before
	public void setUp() {
		factory = new PortalFactory();
		PORTAL_1_POSITION = new Vector2(0, 1);
		PORTAL_2_POSITION = new Vector2(10, -7);
	}

	@Test
	public void whenPortalsCreatedTheyShouldBePlacedAtStartingPositions() {
		factory.createPortalPair(world, PORTAL_1_POSITION, PORTAL_2_POSITION);
	}

}
