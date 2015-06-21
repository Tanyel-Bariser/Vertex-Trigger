package com.vertextrigger.collisiondetection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.util.ContactBody;

@RunWith(MockitoJUnitRunner.class)
public class CollisionDetectionTest {
	private CollisionDetection collision;
	@Mock Player player;
	@Mock Body body;
	@Mock Contact contact;
	@Mock Fixture fixA, fixB;
	@Mock WorldManifold worldManifold;
	private Vector2 vector = new Vector2(0,0);

	@Before
	public void setUp() throws Exception {
		when(player.getBody()).thenReturn(body);
		collision = new CollisionDetection(player);
		when(contact.getFixtureA()).thenReturn(fixA);
		when(contact.getFixtureB()).thenReturn(fixB);
		when(contact.getWorldManifold()).thenReturn(worldManifold);
		when(worldManifold.getPoints()).thenReturn(new Vector2[] {vector,vector});
	}
	
	@Test
	public void whenContactWithGroundFixAGroundContactIsTrue() {
		when(fixA.getUserData()).thenReturn(ContactBody.GROUND);
		collision.postSolve(contact, null);
		ContactBody[] contactBodies = setUpContactBodies(contact);
		assertTrue(collision.isGroundContact(contactBodies));
	}
	
	private ContactBody[] setUpContactBodies(Contact contact) {
		return new ContactBody[]{(ContactBody) contact.getFixtureA().getUserData(), 
			(ContactBody) contact.getFixtureB().getUserData()};
	}

	@Test
	public void whenContactWithGroundFixtureBGroundContactIsTrue() {
		when(fixB.getUserData()).thenReturn(ContactBody.GROUND);
		collision.postSolve(contact, null);
		ContactBody[] contactBodies = setUpContactBodies(contact);
		assertTrue(collision.isGroundContact(contactBodies));
	}
	
	@Test
	public void whenContactWithPlayerFixtureAPlayerContactIsTrue() {
		when(fixA.getUserData()).thenReturn(ContactBody.PLAYER);
		when(body.getPosition()).thenReturn(vector);
		collision.postSolve(contact, null);
		ContactBody[] contactBodies = setUpContactBodies(contact);
		assertTrue(collision.isPlayerContact(contactBodies));
	}

	@Test
	public void whenContactWithPlayerFixtureBPlayerContactIsTrue() {
		when(fixB.getUserData()).thenReturn(ContactBody.PLAYER);
		when(body.getPosition()).thenReturn(vector);
		collision.postSolve(contact, null);
		ContactBody[] contactBodies = setUpContactBodies(contact);
		assertTrue(collision.isPlayerContact(contactBodies));
	}
	
	@Test
	public void whenContactWithBulletFixtureABulletContactIsTrue() {
		when(fixA.getUserData()).thenReturn(ContactBody.BULLET);
		collision.postSolve(contact, null);
		ContactBody[] contactBodies = setUpContactBodies(contact);
		assertTrue(collision.isBulletContact(contactBodies));
	}

	@Test
	public void whenContactWithBulletFixtureBBulletContactIsTrue() {
		when(fixB.getUserData()).thenReturn(ContactBody.BULLET);
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
	
	@Test
	public void whenPlayerFeetInContactWithGroundPlayerCanJump() {
		when(fixA.getUserData()).thenReturn(ContactBody.PLAYER);
		when(fixB.getUserData()).thenReturn(ContactBody.GROUND);
		when(body.getPosition()).thenReturn(new Vector2(0,1));
		when(fixB.getBody()).thenReturn(body);
		
		collision.postSolve(contact, null);
		verify(player).setCanJump();
	}
}