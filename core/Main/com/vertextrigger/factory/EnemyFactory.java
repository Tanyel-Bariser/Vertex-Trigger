package com.vertextrigger.factory;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.ai.SteerableBody;
import com.vertextrigger.entities.AnimationSet;
import com.vertextrigger.entities.MagnetFlowField;
import com.vertextrigger.entities.enemy.Bee;
import com.vertextrigger.entities.enemy.minigunboss.MinigunBoss;
import com.vertextrigger.entities.enemy.Mouse;
import com.vertextrigger.entities.enemy.Poker;
import com.vertextrigger.entities.enemy.spider.Spider;
import com.vertextrigger.entities.movingplatform.SimplePath;
import com.vertextrigger.factory.animationfactory.BeeAnimationFactory;
import com.vertextrigger.factory.animationfactory.MinigunBossAnimationFactory;
import com.vertextrigger.factory.animationfactory.MouseAnimationFactory;
import com.vertextrigger.factory.animationfactory.PokerAnimationFactory;
import com.vertextrigger.factory.animationfactory.SpiderAnimationFactory;
import com.vertextrigger.factory.bodyfactory.BeeBodyFactory;
import com.vertextrigger.factory.bodyfactory.MinigunBossBodyFactory;
import com.vertextrigger.factory.bodyfactory.MouseBodyFactory;
import com.vertextrigger.factory.bodyfactory.PokerBodyFactory;
import com.vertextrigger.factory.bodyfactory.SpiderBodyFactory;
import com.vertextrigger.factory.entityfactory.BulletFactory;
import com.vertextrigger.screen.AbstractGameScreen;

public class EnemyFactory {

	public static Poker createPokerEnemy(final World world, final Vector2 initialPosition) {
		final PokerBodyFactory factory = new PokerBodyFactory();
		final Body body = factory.createPokerBody(world, initialPosition);
		final AnimationSet anims = new PokerAnimationFactory().createAnimationSet();
		return new Poker(body, anims);
	}

	public static Bee createBeeEnemy(final World world, final Vector2 initialPosition, final Steerable<Vector2> target,
			final AbstractGameScreen screen, final MagnetFlowField magnetFlowField) {
		final float zeroLinearSpeedThreshold = 0.1f;
		final float maxLinearSpeed = 0.1f;
		final float maxLinearAcceleration = 1f;
		final float maxAngularSpeed = 30f;
		final float maxAngularAcceleration = 30f;

		final BeeBodyFactory factory = new BeeBodyFactory();
		final Body body = factory.createBeeBody(world, initialPosition);
		final AnimationSet anims = new BeeAnimationFactory().createAnimationSet();

		return new Bee(body, anims, target, new BulletFactory(world, magnetFlowField), screen, new SteerableBody(body, maxLinearAcceleration,
				maxLinearSpeed, maxAngularAcceleration, maxAngularSpeed, zeroLinearSpeedThreshold, 10, false));
	}

	public static Mouse createMouseEnemy(final World world, final Vector2 pathStart, final Vector2 pathEnd) {
		final MouseBodyFactory factory = new MouseBodyFactory();
		final Body mouseBody = factory.createMouseBody(world, pathStart);
		final AnimationSet mouseAnims = new MouseAnimationFactory().createAnimationSet();
		return new Mouse(mouseBody, mouseAnims, new SimplePath(pathStart, pathEnd));
	}

	public static Spider createSpiderEnemy(final World world, final Vector2 initialPosition, final boolean faceRight) {
		final SpiderBodyFactory factory = new SpiderBodyFactory();
		final Body spiderBody = factory.createSpiderBody(world, initialPosition);
		final AnimationSet spiderAnims = new SpiderAnimationFactory().createAnimationSet();
		return new Spider(spiderBody, spiderAnims, faceRight);
	}

	public static MinigunBoss createMinigunBossEnemy(final World world, final Vector2 initialPosition, final Vector2 playerPosition, final AbstractGameScreen screen) {
		final MinigunBossBodyFactory factory = new MinigunBossBodyFactory();
		final Body minigunBossBody = factory.createMinigunBossBody(world, initialPosition);
		return new MinigunBoss(minigunBossBody, new MinigunBossAnimationFactory().createAnimationSet(), screen, new BulletFactory(world, null), playerPosition);
	}
}