package com.vertextrigger.assets;

public enum BackgroundPath {
	MAIN_SCREEN("background/main_screen.png"),
	LEVEL_ONE("background/level_one.png"),
	LEVEL_TWO("background/level_two.png"),
	LEVEL_THREE("background/level_three.png"),
	LEVEL_FOUR("background/level_four.png");

	private final String path;

	private BackgroundPath(final String path) {
		this.path = path;
	}

	/**
	 * @return directory of background image within assets folder of android project
	 */
	public String getPath() {
		return path;
	}
}