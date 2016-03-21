package com.vertextrigger.util;

enum SOUND_FX {
    BUTTON("sound/button.ogg"),
    DIE("sound/die.wav"),
    // DOOR("sound/door.wav"),// TODO find door asset
    ENEMY_DEATH("sound/enemy.wav"),
    JUMP("sound/jump.wav"),
	PLAYER_DEATH("sound/die.wav"),
	PORTAL("sound/portal.ogg"),
	POWER_UP("sound/powerup.wav"),
    SHOOT("sound/shoot.wav");

	private final String path;
	
	SOUND_FX(String path) {
		this.path = path;
	}
	
	/**
	 * @return directory of sound effects within assets folder of android project
	 */
	String getPath() {
		return path;
	}
}
