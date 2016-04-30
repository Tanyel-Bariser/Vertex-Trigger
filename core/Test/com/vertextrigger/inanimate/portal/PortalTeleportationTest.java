package com.vertextrigger.inanimate.portal;

import static com.vertextrigger.inanimate.portal.PortalTeleportation.*;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

@RunWith(MockitoJUnitRunner.class)
public class PortalTeleportationTest {
	@Mock
	Body body;
	Vector2 exitCoordinate;

	@Before
	public void before() {
		when(body.getLinearVelocity()).thenReturn(new Vector2(5, 4));
	}

	@Test
	public void testMovingSameDirection() {
		MOVING_SAME_DIRECTION.teleport(body, exitCoordinate);
		verify(body).setTransform(exitCoordinate, 0);
		verify(body, never()).setLinearVelocity(anyFloat(), anyFloat());
	}

	@Test
	public void testMovingDifferentXYAxisDirection() {
		MOVING_DIFFERENT_XY_AXIS_DIRECTION.teleport(body, exitCoordinate);
		verify(body).setTransform(exitCoordinate, 0);
		verify(body).setLinearVelocity(4, 5);
	}

	@Test
	public void testMovingOppositeHorizontalDirection() {
		MOVING_OPPOSITE_HORIZONTAL_DIRECTION.teleport(body, exitCoordinate);
		verify(body).setTransform(exitCoordinate, 0);
		verify(body).setLinearVelocity(-5, 4);
	}

	@Test
	public void testMovingOppositeVerticalDirection() {
		MOVING_OPPOSITE_VERTICAL_DIRECTION.teleport(body, exitCoordinate);
		verify(body).setTransform(exitCoordinate, 0);
		verify(body).setLinearVelocity(5, -4);
	}
}