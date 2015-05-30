package com.vertextrigger.util;

enum BACKGROUND {
	MAIN_SCREEN("background/main_screen.png"),
	LEVEL_ONE("background/level_one.png"),
	LEVEL_TWO("background/level_two.png");
	
	private final String path;
	
	private BACKGROUND(String path) {
		this.path = path;
	}
	
	/**
	 * @return directory of background image within assets folder of android project
	 */
	String getPath() {
		return path;
	}
}