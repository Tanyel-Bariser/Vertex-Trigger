package com.vertextrigger.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AnimationFactoryTest.class, AssetsTest.class,
		AudioManagerTest.class, BulletPoolTest.class, BulletTest.class,
		ButtonFactoryTest.class, CollisionDetectionTest.class,
		ControllerTest.class, DangerousBallTest.class, EnemyFactoryTest.class,
		EnemyTest.class, EntityTest.class, GroundWallFactoryTest.class,
		LevelOneScreenTest.class, MainScreenTest.class, PathTest.class,
		PlatformBehaviourTest.class, StaticPlatformFactoryTest.class,
		PlayerTest.class, SpriteFactoryTest.class, VertexTriggerTest.class })
public class AllTests {
}