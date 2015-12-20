package com.vertextrigger.alltests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.vertextrigger.collisiondetection.*;
import com.vertextrigger.entities.*;
import com.vertextrigger.entities.player.*;
import com.vertextrigger.factory.*;
import com.vertextrigger.factory.bodyfactory.BulletBodyFactoryTest;
import com.vertextrigger.factory.bodyfactory.PlayerBodyFactoryTest;
import com.vertextrigger.factory.bodyfactory.PortalBodyFactoryTest;
import com.vertextrigger.inanimate.portal.PortalFactoryTest;
import com.vertextrigger.levelbuilders.LevelOneBuilderTest;
import com.vertextrigger.levelbuilders.LevelThreeBuilderTest;
import com.vertextrigger.levelbuilders.LevelTwoBuilderTest;
import com.vertextrigger.main.*;
import com.vertextrigger.screens.GameScreenTest;
import com.vertextrigger.screens.MainScreenTest;
import com.vertextrigger.util.*;

@RunWith(Suite.class)
@SuiteClasses({ CollisionDetectionTest.class, PlatformBehaviourTest.class,
		BulletBodyFactoryTest.class, BulletTest.class, GunTest.class,
		AnimatorTest.class, PlayerBodyFactoryTest.class,
		PlayerTest.class, DangerousBallTest.class, EnemyTest.class,
		ExplodingPlatformTest.class, FireBallTest.class,
		MovingPlatformTest.class, PathTest.class, PortalTest.class,
		ReappearingPlatformTest.class, RotatingPlatformTest.class,
		SpikedBallTest.class, TimedPlatformTest.class,
		AnimationFactoryTest.class, ButtonFactoryTest.class,
		DangerousBallFactoryTest.class, EnemyFactoryTest.class,
		GroundWallFactoryTest.class, LevelOneBuilderTest.class,
		LevelTwoBuilderTest.class, LevelThreeBuilderTest.class,
		VertexTriggerTest.class, GameScreenTest.class, MainScreenTest.class,
		AndroidControllerTest.class, AssetsTest.class, AudioManagerTest.class,
		DesktopControllerTest.class ,PortalFactoryTest.class, PortalBodyFactoryTest.class,
		PortalTest.class})
public class AllTests {
}
