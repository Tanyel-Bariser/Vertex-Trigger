package com.vertextrigger.assets;

enum AtlasPath {
	CORE("atlas/core.pack"), MAIN_SCREEN("atlas/main_screen.pack"), LEVEL_ONE("atlas/level_one.pack"), LEVEL_TWO("atlas/level_two.pack"), PROTOTYPE(
			"atlas/prototype.pack"), ENEMY("atlas/enemy.pack"), LEVEL_HUGH("atlas/level_hugh.pack"), LEVEL_BOSS("atlas/level_boss.pack");

	private final String path;

	private AtlasPath(final String path) {
		this.path = path;
	}

	/**
	 * @return directory of atlas within assets folder of android project
	 */
	String getPath() {
		return path;
	}
}
