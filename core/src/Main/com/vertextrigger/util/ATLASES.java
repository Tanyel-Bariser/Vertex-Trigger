package com.vertextrigger.util;

enum ATLASES {
	CORE("atlas/core.png"),
	MAIN_SCREEN("atlas/main_screen.png"),
	LEVEL_ONE("atlas/level_one.png"),
	LEVEL_TWO("atlas/level_two.png");
	
	private final String path;
	
	private ATLASES(String path) {
		this.path = path;
	}
	
	/**
	 * @return directory of atlas within assets folder of android project
	 */
	String getPath() {
		return path;
	}
}
