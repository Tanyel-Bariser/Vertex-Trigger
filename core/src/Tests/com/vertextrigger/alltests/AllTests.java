package com.vertextrigger.alltests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.vertextrigger.collisiondetection.*;
import com.vertextrigger.entities.*;
import com.vertextrigger.entities.player.*;
import com.vertextrigger.factories.*;
import com.vertextrigger.levelbuilders.*;
import com.vertextrigger.main.*;
import com.vertextrigger.screens.*;
import com.vertextrigger.util.*;

@RunWith(Suite.class)
@SuiteClasses({
	CollisionDetectionTest.class, PlatformBehaviourTest.class, BulletBodyFactoryTest.class,
	BulletTest.class, GunTest.class, PlayerAnimatorTest.class, PlayerBodyFactoryTest.class,
	PlayerTest.class, DangerousBallTest.class, EnemyTest.class, ExplodingPlatformTest.class,
	FireBallTest.class, MovingPlatformTest.class, PathTest.class, PortalTest.class,
	ReappearingPlatformTest.class, RotatingPlatformTest.class, SpikedBallTest.class,
	TimedPlatformTest.class, AnimationFactoryTest.class, ButtonFactoryTest.class,
	DangerousBallFactoryTest.class, EnemyFactoryTest.class, GroundWallFactoryTest.class,
	StaticPlatformFactoryTest.class, LevelOneBuilderTest.class, LevelTwoBuilderTest.class,
	LevelThreeBuilderTest.class, VertexTriggerTest.class, GameScreenTest.class, MainScreenTest.class,
	AndroidControllerTest.class, AssetsTest.class, AudioManagerTest.class, DesktopControllerTest.class
	})

public class AllTests {
}
