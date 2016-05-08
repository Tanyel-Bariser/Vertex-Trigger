package com.vertextrigger.util;

enum ATLASES {
	CORE("atlas/core.pack"), MAIN_SCREEN("atlas/main_screen.pack"), LEVEL_ONE("atlas/level_one.pack"), LEVEL_TWO("atlas/level_two.pack"), PROTOTYPE(
			"atlas/prototype.pack"), PORTAL("atlas/portal.pack");

	private final String path;

	private ATLASES(final String path) {
		this.path = path;
	}

	/**
	 * @return directory of atlas within assets folder of android project
	 */
	String getPath() {
		return path;
	}
}
