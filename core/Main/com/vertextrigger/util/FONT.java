package com.vertextrigger.util;

enum FONT {
	REGULAR("font/regular-12.fnt"), THIN("font/thin-12.fnt");

	private final String path;

	private FONT(final String path) {
		this.path = path;
	}

	/**
	 * @return directory of font within assets folder of android project
	 */
	String getPath() {
		return path;
	}
}
