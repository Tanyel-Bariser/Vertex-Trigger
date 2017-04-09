package com.vertextrigger.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Timer;
import com.vertextrigger.assets.Assets;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.factory.BackgroundFactory;
import com.vertextrigger.factory.ButtonFactory;
import com.vertextrigger.factory.FontFactory;
import com.vertextrigger.factory.GameScreenFactory;
import com.vertextrigger.main.VertexTrigger;

/**
 * The first screen the user sees, with the option of selecting the level they want to play. The first level will always be able to be selected & so
 * will previously completed levels.
 */
public class MainScreen implements Screen {

	private static final String COMING_SOON_TEXT = "Coming Soon!";
	private static final String LEVEL_PROTO_BUTTON_TEXT = "Prototype";
	private static final String LEVEL_ONE_BUTTON_TEXT = "Level One";
	private static final String LEVEL_TWO_BUTTON_TEXT = "Level Two";
	private static final String TITLE_TEXT = "Vertex Trigger";
	private static float WIDTH = Gdx.graphics.getWidth();
	private static float HEIGHT = Gdx.graphics.getHeight();

	private final Assets assets;
	private final VertexTrigger vertexTrigger;

	private BitmapFont buttonFont, titleFont;
	private GlyphLayout titleLayout;
	private ImageTextButton prototype, levelOne, levelTwo;
	private ImageTextButtonStyle style = new ImageTextButtonStyle();
	private Sprite background;
	private SpriteBatch spriteBatch;
	private Stage stage;

	public MainScreen(final VertexTrigger vertexTrigger) {
		this.assets = VertexTrigger.ASSETS;
		this.vertexTrigger = vertexTrigger;
	}

	@Override
	public void render(final float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
		background.draw(spriteBatch);
		titleFont.draw(spriteBatch, TITLE_TEXT, WIDTH / 2 - titleLayout.width / 2, HEIGHT * 0.9f);
		spriteBatch.end();

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(final int width, final int height) {
		stage.clear();
		WIDTH = width;
		HEIGHT = height;
		show();
	}

	/**
	 * Show method is invoked once when the main screen is first created & is used to set up the main screen
	 */
	@Override
	public void show() {
		initStage();
		initBackground();
		initTitle();
		initButtons();
		AudioManager.playMainScreenMusic();
	}

	private void initStage() {
		spriteBatch = new SpriteBatch();
		titleLayout = new GlyphLayout();
		stage = new Stage();
		assets.loadMainScreen();
		Gdx.input.setInputProcessor(stage);
	}

	private void initBackground() {
		background = BackgroundFactory.getMainScreenBackground();
		background.setSize(800, 600);
		background.setOriginCenter();
	}

	private void initTitle() {
		titleFont = FontFactory.getMainScreenTitleFont();
		titleFont.setColor(Color.RED);
		titleFont.getData().setScale(2);
		titleLayout.setText(titleFont, TITLE_TEXT);
	}

	private void initButtons() {
		initButtonStyle();
		initPrototype();
		initLevelOne();
		initLevelTwo();
	}

	private void initButtonStyle() {
		// 01, 02, 11, 12, 13
		final Drawable up = ButtonFactory.getMainScreenButtonUp();
		final Drawable down = ButtonFactory.getMainScreenButtonDown();
		buttonFont = FontFactory.getMainScreenButtonFont();

		style = new ImageTextButtonStyle(up, down, null, buttonFont);
		style.fontColor = Color.ROYAL;
	}

	private void initPrototype() {
		prototype = new ImageTextButton(LEVEL_PROTO_BUTTON_TEXT, style);
		prototype.setPosition(WIDTH / 2 - prototype.getWidth() / 2, buttonHeight(0));
		prototype.addListener(new ClickListener() {
			public void clicked(final InputEvent event, final float x, final float y) {
				vertexTrigger.setNextScreen(GameScreenFactory.createPrototypeLevel(vertexTrigger));
			}
		});
		prototype.getLabel().setFontScale(1.2f);
		stage.addActor(prototype);
	}

	private void initLevelOne() {
		levelOne = new ImageTextButton(LEVEL_ONE_BUTTON_TEXT, style);
		levelOne.setPosition(WIDTH / 2 - levelOne.getWidth() / 2, buttonHeight(1) );
		levelOne.addListener(new ClickListener() {
			public void clicked(final InputEvent event, final float x, final float y) {
				temporaryMessage(levelOne, COMING_SOON_TEXT);
			}
		});
		levelOne.getLabel().setFontScale(1.2f);
		stage.addActor(levelOne);
	}

	private void initLevelTwo() {
		levelTwo = new ImageTextButton(LEVEL_TWO_BUTTON_TEXT, style);
		levelTwo.setPosition(WIDTH / 2 - levelTwo.getWidth() / 2, buttonHeight(2));
		levelTwo.addListener(new ClickListener() {
			public void clicked(final InputEvent event, final float x, final float y) {
				temporaryMessage(levelTwo, COMING_SOON_TEXT);
			}
		});
		levelTwo.getLabel().setFontScale(1.2f);
		stage.addActor(levelTwo);
	}

	private void temporaryMessage(final ImageTextButton button, final String temporaryMessage) {
		final CharSequence originalText = button.getText().toString();
		button.setText(temporaryMessage);
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				button.setText(originalText);
			}
		}, 2.5f);
	}

	private float buttonHeight(final int offset) {
		return (HEIGHT / 2) - 75 * offset;
	}

	// UNUSED METHOD FROM INTERFACE
	@Override
	public void pause() {
	}

	// UNUSED METHOD FROM INTERFACE
	@Override
	public void resume() {
	}

	/**
	 * Hide method is invoked automatically when the screen is switch to another screen & is an appropriate place to dispose of the assets being used
	 */
	@Override
	public void hide() {
		this.dispose();
	}

	/**
	 * Dispose method needs to be invoked manually when this class is no longer being used to dispose of the assets it's using
	 */
	@Override
	public void dispose() {
		assets.unloadMainScreen();
	}
}