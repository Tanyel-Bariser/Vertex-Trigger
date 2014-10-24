package com.vertextrigger.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AnimationFactoryTest.class, AssetsTest.class,
		AudioManagerTest.class, BulletPoolTest.class, ButtonFactoryTest.class,
		CollisionDetectionTest.class, ContactBehaviourTest.class,
		ControllerTest.class, DangerousBallTest.class, EnemyFactoryTest.class,
		EnemyTest.class, GroundWallFactoryTest.class, LevelOneScreenTest.class,
		MainScreenTest.class, PlatformFactoryTest.class, PlayerTest.class,
		SpriteFactoryTest.class, VertexTriggerTest.class })
public class AllTests {

}
