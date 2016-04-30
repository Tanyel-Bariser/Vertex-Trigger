// package com.vertextrigger.inanimate.portal;
//
// import static org.junit.Assert.assertEquals;
// import static org.mockito.Mockito.*;
//
// import static org.hamcrest.MatcherAssert.assertThat;
// import static org.hamcrest.CoreMatchers.*;
// import org.junit.*;
// import org.junit.runner.RunWith;
// import org.mockito.Mock;
// import org.mockito.runners.MockitoJUnitRunner;
//
// import com.badlogic.gdx.graphics.g2d.Sprite;
// import com.badlogic.gdx.math.Vector2;
// import com.badlogic.gdx.physics.box2d.Body;
//
// @RunWith(MockitoJUnitRunner.class)
// public class PortalTest {
// Portal portal;
// Portal pairedPortal;
// Vector2 position;
// Vector2 pairedPosition;
// @Mock PortalTeleportation teleportation;
// @Mock Body body;
// @Mock Body pairedBody;
// @Mock Sprite sprite;
// @Mock Sprite pairedSprite;
//
// @Before
// public void setUp() throws Exception {
// position = new Vector2(2,-6);
// when(body.getPosition()).thenReturn(position);
// pairedPosition = new Vector2(-9, 12);
// when(pairedBody.getPosition()).thenReturn(pairedPosition);
// portal = new Portal(body, sprite, teleportation, pairedPosition);
// pairedPortal = new Portal(pairedBody, pairedSprite, teleportation, position);
// }
//
// @Test
// public void whenPassInitialPositionIntoPortalShouldReturnThatPosition() {
// assertThat(portal.getPosition(), is(equalTo(position)));
// }
//
// @Test
// public void whenPassPairedPositionIntoPortalShouldReturnThatPosition() {
// assertEquals(position, portal.getPosition());
// assertEquals(pairedPosition, pairedPortal.getPosition());
// assertEquals(portal.getPosition(), pairedPortal.getPairedPosition());
// assertEquals(pairedPortal.getPosition(), portal.getPairedPosition());
// }
//
// @Test
// public void testOtherConstructorParams() {
// assertThat(portal.getBody(), is(equalTo(body)));
// assertThat(pairedPortal.getBody(), is(equalTo(pairedBody)));
// assertThat(portal.getSprite(), is(equalTo(sprite)));
// assertThat(pairedPortal.getSprite(), is(equalTo(pairedSprite)));
// assertThat(portal.getTeleportation(), is(equalTo(teleportation)));
// assertThat(pairedPortal.getTeleportation(), is(equalTo(teleportation)));
// }
//
// @Test
// public void shouldTeleportBody() {
// portal.teleport(body);
// verify(teleportation).teleport(body, portal.getPairedPosition());
// }
// }