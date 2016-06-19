package com.vertextrigger.assets;

enum MusicPath {
	CREDITS("music/credits.ogg"), GAME_OVER("music/game_over.ogg"), LEVEL_ONE("music/level_one.ogg"), LEVEL_TWO("music/level_two.ogg"), MAIN_SCREEN(
			"music/main_screen.ogg");

	private final String path;

	private MusicPath(final String path) {
		this.path = path;
	}

	/**
	 * @return directory of music within assets folder of android project
	 */
	String getPath() {
		return path;
	}
}