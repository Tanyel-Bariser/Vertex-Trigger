package com.vertextrigger.util;

enum FONT {
	THIN("font/thin.ttf"),
	REGULAR("font/regular.ttf");
	
	private final String path;
	
	private FONT(String path) {
		this.path = path;
	}
	
	/**
	 * @return directory of font within assets folder of android project
	 */
	String getPath() {
		return path;
	}
}
