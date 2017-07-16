package com.vertextrigger.controller;

import com.badlogic.gdx.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.factory.ButtonFactory;

public class AndroidController extends Controller {
	private final Table buttonLayer = new Table().debug();
	private final Vector2 leftButtonPosition = new Vector2(-50, 0);
	private final Vector2 rightButtonPosition = new Vector2(-25, 0);
	private final Vector2 pauseButtonPosition = new Vector2(0, 0);
	private final Vector2 shootButtonPosition = new Vector2(25, 0);
	private final Vector2 jumpButtonPosition = new Vector2(50, 0);

	public AndroidController(final Player player, final Screen screen, final Stage stage) {
		super(player, screen);

		createLeftButton(new ImageButton(ButtonFactory.getLeftButton()));
		createRightButton(new ImageButton(ButtonFactory.getRightButton()));
		createPauseButton(new ImageButton(ButtonFactory.getPauseButton(), null, ButtonFactory.getResumeButton()));
		createShootButton(new ImageButton(ButtonFactory.getShootButton()));
		createJumpButton(new ImageButton(ButtonFactory.getJumpButton()));

		buttonLayer.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		buttonLayer.setPosition(0, 0);
		buttonLayer.align(Align.center | Align.bottom);
		stage.addActor(buttonLayer);
	}

	private void createLeftButton(final ImageButton left) {
		left.addListener(getLeftClickListener());
		left.setPosition(leftButtonPosition.x, leftButtonPosition.y);
		buttonLayer.add(left).width(200).height(200);
	}

	private ClickListener getLeftClickListener() {
		return new ClickListener() {
			@Override
			public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				if (isControllable()) {
					player.moveLeft();
				}
				return true;
			}

			@Override
			public void touchUp(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				player.stopMoving();
			}
		};
	}

	private void createRightButton(final ImageButton right) {
		right.addListener(getRightClickListener());
		right.setPosition(rightButtonPosition.x, rightButtonPosition.y);
		buttonLayer.add(right).width(200).height(200);
	}

	private ClickListener getRightClickListener() {
		return new ClickListener() {
			@Override
			public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				if (isControllable()) {
					player.moveRight();
				}
				return true;
			}

			@Override
			public void touchUp(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				player.stopMoving();
			}
		};
	}

	private void createPauseButton(final ImageButton pause) {
		pause.addListener(getPauseClickListener());
		pause.setPosition(pauseButtonPosition.x, pauseButtonPosition.y);
		buttonLayer.add(pause).width(200).height(200);
	}

	private ClickListener getPauseClickListener() {
		return new ClickListener() {
			@Override
			public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				togglePause();
				return super.touchDown(event, x, y, pointer, button);
			}
		};
	}

	private void createShootButton(final ImageButton shoot) {
		shoot.addListener(getShootClickListener());
		shoot.setPosition(shootButtonPosition.x, shootButtonPosition.y);
		buttonLayer.add(shoot).width(200).height(200);
	}

	private ClickListener getShootClickListener() {
		return new ClickListener() {
			@Override
			public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				if (isControllable()) {
					player.shoot();
				}
				return super.touchDown(event, x, y, pointer, button);
			}
		};
	}

	private void createJumpButton(final ImageButton jump) {
		jump.addListener(getJumpClickListener());
		jump.setPosition(jumpButtonPosition.x, jumpButtonPosition.y);
		buttonLayer.add(jump).width(200).height(200);
	}

	private ClickListener getJumpClickListener() {
		return new ClickListener() {
			@Override
			public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				if (isControllable()) {
					player.jump();
				}
				return super.touchDown(event, x, y, pointer, button);
			}
		};
	}
}