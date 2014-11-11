package com.vertextrigger.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AnimationFactoryTest.class, AssetsTest.class,
		AudioManagerTest.class, BulletPoolTest.class, BulletTest.class,
		ButtonFactoryTest.class, CollisionDetectionTest.class,
		ControllerTest.class, DangerousBallFactoryTest.class,
		DangerousBallTest.class, EnemyFactoryTest.class, EnemyTest.class,
		EntityTest.class, ExplodingPlatformTest.class, FireBallTest.class,
		GroundWallFactoryTest.class, LevelOneScreenTest.class,
		MainScreenTest.class, MovingPlatformTest.class, PathTest.class,
		PlatformBehaviourTest.class, PlayerTest.class,
		ReappearingPlatformTest.class, RotatingPlatformTest.class,
		SpikedBallTest.class, SpriteFactoryTest.class,
		StaticPlatformFactoryTest.class, TimedPlatformTest.class,
		VertexTriggerTest.class })
public class AllTests {
}