package com.vertextrigger.util;

enum MUSIC {
	MAIN_SCREEN("music/main_screen.ogg"),
	LEVEL_ONE("music/level_one.ogg"),
	LEVEL_TWO("music/level_one.ogg");
	
	private final String path;
	
	private MUSIC(String path) {
		this.path = path;
	}
	
	/**
	 * @return directory of music within assets folder of android project
	 */
	String getPath() {
		return path;
	}
}