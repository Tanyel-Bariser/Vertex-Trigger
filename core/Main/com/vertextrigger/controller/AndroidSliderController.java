package com.vertextrigger.controller;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.main.VertexTrigger;

public class AndroidSliderController extends AndroidController {

	public AndroidSliderController(final Player player, final Screen screen, final Stage stage) {
		super(player, screen, stage);
	}

	// Slider for left/right direction buttons
	@Override
	void createDirectionalButtons() {
		final int min = 0;
		final int max = 10;
		final float stepSize = 1.0f;
		final boolean vertical = false;
		final Skin skin = VertexTrigger.ASSETS.getCoreSkin();
		final Drawable sliderImage = skin.getDrawable("magnetInactive");
		final Drawable knobImage = skin.getDrawable("bullet");
		final SliderStyle sliderStyle = new SliderStyle(sliderImage, knobImage);
		final Slider slider = new Slider(min, max, stepSize, vertical, sliderStyle);
		final int width = 600;
		final int directionChangePoint = width / 6;
		slider.addListener(new DragListener() {
			@Override
			public void touchDragged(final InputEvent event, final float x, final float y, final int pointer) {
				if (isControllable()) {
					if (x < directionChangePoint) {
						player.moveLeft();
					} else {
						player.moveRight();
					}
				}
			}

			@Override
			public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				return true;
			}

			@Override
			public void touchUp(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				player.stopMoving();
			}
		});
		buttonLayer.add(slider).width(width).height(200);
	}
}