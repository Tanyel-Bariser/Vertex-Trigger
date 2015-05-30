package com.vertextrigger.util;

enum SOUND_FX {
	JUMP("sound/jump.ogg"),
	DIE("sound/die.ogg"),
	SHOOT("sound/shoot.ogg"),
	ENEMY("sound/enemy.ogg"),
	GAMEOVER("sound/game_over.ogg"),
	BUTTON("sound/button.ogg"),
	DOOR("sound/door.ogg");
	
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
