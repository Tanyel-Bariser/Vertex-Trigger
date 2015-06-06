package com.vertextrigger.util;

enum SOUND_FX {
    BUTTON("sound/button.wav"),
    DIE("sound/die.wav"),
    DOOR("sound/door.wav"),                     // TODO find door asset
    ENEMY("sound/enemy.wav"),
    JUMP("sound/jump.wav"),
    PORTAL("sound/portal.wav"),
    SHOOT("sound/shoot.wav");

	private final String path;
	
	private SOUND_FX(String path) {
		this.path = path;
	}
	
	/**
	 * @return directory of sound effects within assets folder of android project
	 */
	String getPath() {
		return path;
	}
}
