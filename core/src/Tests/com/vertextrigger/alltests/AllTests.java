package com.vertextrigger.alltests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.vertextrigger.collisiondetection.CollisionDetectionTest;
import com.vertextrigger.collisiondetection.PlatformBehaviourTest;
import com.vertextrigger.entities.BulletPoolTest;
import com.vertextrigger.entities.BulletTest;
import com.vertextrigger.entities.DangerousBallTest;
import com.vertextrigger.entities.EnemyTest;
import com.vertextrigger.entities.ExplodingPlatformTest;
import com.vertextrigger.entities.FireBallTest;
import com.vertextrigger.entities.MovingPlatformTest;
import com.vertextrigger.entities.PathTest;
import com.vertextrigger.entities.PlayerTest;
import com.vertextrigger.entities.ReappearingPlatformTest;
import com.vertextrigger.entities.RotatingPlatformTest;
import com.vertextrigger.entities.SpikedBallTest;
import com.vertextrigger.entities.TimedPlatformTest;
import com.vertextrigger.factories.AnimationFactoryTest;
import com.vertextrigger.factories.ButtonFactoryTest;
import com.vertextrigger.factories.DangerousBallFactoryTest;
import com.vertextrigger.factories.EnemyFactoryTest;
import com.vertextrigger.factories.GroundWallFactoryTest;
import com.vertextrigger.factories.SpriteFactoryTest;
import com.vertextrigger.factories.StaticPlatformFactoryTest;
import com.vertextrigger.levelbuilders.LevelOneBuilderTest;
import com.vertextrigger.levelbuilders.LevelThreeBuilderTest;
import com.vertextrigger.levelbuilders.LevelTwoBuilderTest;
import com.vertextrigger.main.VertexTriggerTest;
import com.vertextrigger.screens.GameScreenTest;
import com.vertextrigger.screens.MainScreenTest;
import com.vertextrigger.util.AssetsTest;
import com.vertextrigger.util.AudioManagerTest;
import com.vertextrigger.util.ControllerTest;

@RunWith(Suite.class)
@SuiteClasses({ AnimationFactoryTest.class, AssetsTest.class,
		AudioManagerTest.class, BulletPoolTest.class, BulletTest.class,
		ButtonFactoryTest.class, CollisionDetectionTest.class,
		ControllerTest.class, DangerousBallFactoryTest.class,
		DangerousBallTest.class, EnemyFactoryTest.class, EnemyTest.class,
		ExplodingPlatformTest.class, FireBallTest.class, GameScreenTest.class,
		GroundWallFactoryTest.class, LevelOneBuilderTest.class,
		LevelThreeBuilderTest.class, LevelTwoBuilderTest.class,
		MainScreenTest.class, MovingPlatformTest.class, PathTest.class,
		PlatformBehaviourTest.class, PlayerTest.class,
		ReappearingPlatformTest.class, RotatingPlatformTest.class,
		SpikedBallTest.class, SpriteFactoryTest.class,
		StaticPlatformFactoryTest.class, TimedPlatformTest.class,
		VertexTriggerTest.class })
public class AllTests {
}