package com.vertextrigger.inanimate.portal;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.inanimate.portal.Portal;
import com.vertextrigger.inanimate.portal.PortalFactory;

public class PortalFactoryTest {
	PortalFactory factory;
	Portal portal;
	Vector2 PORTAL_1_POSITION, PORTAL_2_POSITION;
	World world;

	@Before
	public void setUp() {
		buildWorld();
		factory = new PortalFactory();
		PORTAL_1_POSITION = new Vector2(0, 1);
		PORTAL_2_POSITION = new Vector2(10, -7);
		portal = factory.createPortalPair(world, PORTAL_1_POSITION, PORTAL_2_POSITION);
	}

	private void buildWorld() {
		boolean doNotSimulateInactiveBodies = true;
		Vector2 gravity = new Vector2(0, -9.81f);
		world = new World(gravity, doNotSimulateInactiveBodies);
	}

	@Test
	public void whenPortalsCreatedTheyShouldBePlacedAtStartingPositions() {
		assertThat(portal.getPosition(), is(equalTo(PORTAL_1_POSITION)));
		assertThat(portal.getPairedPosition(), is(equalTo(PORTAL_2_POSITION)));
	}
	
	@Test public void whenPortalPairIsCreatedTheyShouldBePaired() {
		assertNotNull(portal.getPairedPortal());
		assertThat(portal.getPairedPosition(), is(equalTo(PORTAL_2_POSITION)));
	}
	
	
}