package com.vertextrigger.controller;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.factory.ButtonFactory;

public class AndroidButtonController extends AndroidController {

	public AndroidButtonController(final Player player, final Screen screen, final Stage stage) {
		super(player, screen, stage);
	}

	@Override
	void createDirectionalButtons() {
		createLeftButton(new ImageButton(ButtonFactory.getLeftButton()));
		createRightButton(new ImageButton(ButtonFactory.getRightButton()));
	}

	private void createLeftButton(final ImageButton left) {
		left.addListener(getLeftClickListener());
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
}