package com.vertextrigger.entities.player;
import static com.vertextrigger.util.GameObjectSize.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pool;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.factory.bodyfactory.BulletBodyFactory;
import com.vertextrigger.screen.AbstractGameScreen;
import com.vertextrigger.util.GameObjectSize;

/**
 * A pool of bullets that can be reused to avoid allocation
 * 
 * Useful inherited methods include obtain() and free(Bullet)
 */
public class BulletPool extends Pool<Bullet> {
	private final World world;
	
	public BulletPool(World world) {
		this.world = world;
	}

	/**
	 * Creates new bullet object if there are no more currently left 
	 * in the bullet pool. Lazy instantiation.
	 */
	@Override
	protected Bullet newObject() {
		GameObjectSize size = BULLET_SIZE;
		BulletBodyFactory factory = new BulletBodyFactory();
		Body bulletBody = factory.createBulletBody(world);
		Bullet bullet = new Bullet(bulletBody, new SpriteFactory().createCoreSprite("bullet", size));
		AbstractGameScreen.addBullet(bullet);
		return bullet;
	}
}