package com.vertextrigger.factory.bodyfactory;

import static org.junit.Assert.*;
import static com.vertextrigger.util.GameObjectSize.PORTAL_SIZE;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.util.GameObjectSize;

public class PortalBodyFactoryTest {
	PortalBodyFactory factory;
	Body body;

	@Before
	public void setUp() throws Exception {
		body = factory.createPortalBody(null, null);
	}

	@Test
	public void test() {
		
	}

}
