package com.vertextrigger.collisiondetection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.util.ContactBody;

@RunWith(MockitoJUnitRunner.class)
public class CollisionDetectionTest {
	private CollisionDetection collision;
	@Mock Body body;
	@Mock Contact contact;
	@Mock Fixture fixA, fixB;
	@Mock WorldManifold worldManifold;
	private Vector2 vector = new Vector2(0,0);

	@Before
	public void setUp() throws Exception {
		collision = new CollisionDetection(body);
		when(contact.getFixtureA()).thenReturn(fixA);
		when(contact.getFixtureB()).thenReturn(fixB);
		when(contact.getWorldManifold()).thenReturn(worldManifold);
		when(worldManifold.getPoints()).thenReturn(new Vector2[] {vector,vector});
	}
	
	private ContactBody[] setUpContactBodies(Contact contact) {
		return new ContactBody[]{(ContactBody) contact.getFixtureA().getUserData(), 
			(ContactBody) contact.getFixtureB().getUserData()};
	}

	@Test
	public void whenContactWithPlatformPlatformContactIsTrue() {
		when(fixA.getUserData()).thenReturn(ContactBody.GROUND);
		collision.postSolve(contact, null);
		ContactBody[] contactBodies = setUpContactBodies(contact);
		assertTrue(collision.isGroundContact(contactBodies));
	}
	
	@Test
	public void whenContactWithPlayerPlayerContactIsTrue() {
		when(fixA.getUserData()).thenReturn(ContactBody.PLAYER);
		when(body.getPosition()).thenReturn(vector);
		collision.postSolve(contact, null);
		ContactBody[] contactBodies = setUpContactBodies(contact);
		assertTrue(collision.isPlayerContact(contactBodies));
	}
	
	@Test
	public void whenContactWithBulletBulletContactIsTrue() {
		when(fixA.getUserData()).thenReturn(ContactBody.BULLET);
		collision.postSolve(contact, null);
		ContactBody[] contactBodies = setUpContactBodies(contact);
		assertTrue(collision.isBulletContact(contactBodies));
	}
	
	@Test
	public void givenPlayerNotCollidedThenPlayerFeetShouldNotBeInContact() {
		boolean isPlayerContact = false;
		when(body.getPosition()).thenReturn(new Vector2(0,1));
		assertFalse(collision.isPlayerFeetContact(contact, isPlayerContact));
	}
	
	@Test
	public void whenContactPointIsBelowPlayerMidpointThenIsFeetContactShouldBeTrue() {
		boolean isPlayerContact = true;
		when(body.getPosition()).thenReturn(new Vector2(0,1));
		assertTrue(collision.isPlayerFeetContact(contact, isPlayerContact));
	}

	@Test
	public void whenContactPointIsAbovePlayerMidpointThenIsFeetContactShouldBeFalse() {
		boolean isPlayerContact = true;
		when(body.getPosition()).thenReturn(new Vector2(0,-1));
		assertFalse(collision.isPlayerFeetContact(contact, isPlayerContact));
	}
	
	@Test
	public void whenPlayerFeetInContactWithGroundPlayerPositionAndAngleShouldBeSet() {
		when(fixA.getUserData()).thenReturn(ContactBody.PLAYER);
		when(fixB.getUserData()).thenReturn(ContactBody.GROUND);
		when(body.getPosition()).thenReturn(new Vector2(0,1));
		when(fixB.getBody()).thenReturn(body);
		
		collision.postSolve(contact, null);
		verify(body).setTransform((Vector2) anyObject(), anyFloat());
	}
	
	@Test
	public void whenPlayerFeetInContactWithGroundPlayerPositionAndAngleShouldBeSetToPlatform() {
		when(fixA.getUserData()).thenReturn(ContactBody.PLAYER);
		when(fixB.getUserData()).thenReturn(ContactBody.GROUND);
		when(body.getPosition()).thenReturn(new Vector2(0,1));
		
		Vector2 platformPosition = new Vector2(2,2);
		float platformAngle = 2.2f;
		when(body.getPosition()).thenReturn(platformPosition);
		when(body.getAngle()).thenReturn(platformAngle);
		when(fixB.getBody()).thenReturn(body);
		
		collision.postSolve(contact, null);
		verify(body).setTransform(platformPosition, platformAngle);
	}
}