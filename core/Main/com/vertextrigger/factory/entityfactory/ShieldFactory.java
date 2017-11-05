package com.vertextrigger.factory.entityfactory;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.entities.bullet.NonAnimator;
import com.vertextrigger.entities.player.Shield;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.factory.bodyfactory.ShieldBodyFactory;
import com.vertextrigger.level.Level;
import com.vertextrigger.screen.AbstractGameScreen;
import com.vertextrigger.util.GameObjectSize;

public class ShieldFactory {

	public static Shield createShield(final Level level, final Vector2 initialPosition) {
		final Body body = new ShieldBodyFactory().createShieldBody(level.getWorld(), initialPosition);
		final Sprite sprite = new SpriteFactory().createCoreSprite("shield", GameObjectSize.SHIELD_SIZE);
		return new Shield(body, new NonAnimator(sprite));
	}
}