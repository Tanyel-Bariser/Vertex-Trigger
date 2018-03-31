package com.vertextrigger.controller;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.Align;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.factory.ButtonFactory;

public abstract class AndroidController extends Controller {
	protected final Table buttonLayer = new Table().debug();
	protected final float width = Gdx.graphics.getWidth() / 9;

	public AndroidController(final Player player, final Screen screen, final Stage stage) {
		super(player, screen);

		createDirectionalButtons();
		createPauseButton(new ImageButton(ButtonFactory.getPauseButton(), null, ButtonFactory.getResumeButton()));
		createShootButton(new ImageButton(ButtonFactory.getShootButton()));
		createJumpButton(new ImageButton(ButtonFactory.getJumpButton()));

		buttonLayer.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		buttonLayer.setPosition(0, 0);
		buttonLayer.align(Align.center | Align.bottom);
		stage.addActor(buttonLayer);
	}

	abstract void createDirectionalButtons();

	private void createPauseButton(final ImageButton pause) {
		pause.addListener(getPauseClickListener());
		buttonLayer.add(pause).width(width).height(200).padRight(width/2);
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
		buttonLayer.add(shoot).width(width*2).height(200);
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
		buttonLayer.add(jump).width(width*2).height(200);
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