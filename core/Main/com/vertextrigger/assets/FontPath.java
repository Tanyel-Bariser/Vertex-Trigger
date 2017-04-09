package com.vertextrigger.assets;

public enum FontPath {
	REGULAR("font/regular-12.fnt"),
	THIN("font/thin-12.fnt");

	private final String path;

	private FontPath(final String path) {
		this.path = path;
	}

	/**
	 * @return directory of font within assets folder of android project
	 */
	public String getPath() {
		return path;
	}
}
