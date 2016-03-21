package com.vertextrigger.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

import static com.vertextrigger.util.SOUND_FX.*;

/**
 * Manages the playing and stopping of music and sound effects
 */
public class AudioManager {
	static boolean gameIsMuted;
	final static Map<String, Music> gameMusic;
	final static Map<String, Sound> soundEffects;

	/**
	 * Initialize class including all sfx and music
	 */
	static {
		gameMusic = new HashMap<>();
		soundEffects = new HashMap<>();
	}

	/** mutes the game if it is unmuted, and vice-versa. plays a sound for feedback irrespective of whether game is muted */
	public static void toggleMute() {
		playSound(BUTTON, true);
		gameIsMuted ^= true;
	}

	/**
	 * Plays main menu music, while preventing other music from
	 * clashing with the main menu music and it only plays
	 * if the user has not muted the music in the setting
	 */
	public static void playMainScreenMusic() {
		// Stop other music from playing
		// If user has not muted the music in settings
		// And the main menu music is not already playing
				// Play main menu screen music
	}

	/**
	 * Plays level one music, while preventing other music from
	 * clashing with the level one music and it only plays
	 * if the user has not muted the music in the setting
	 */
	public static void playLevelOneMusic() {
		// Stop other music from playing
		// If user has not muted the music in settings
		// And the level one music is not already playing
				// Play main menu screen music
	}

	/**
	 * Plays level two music, while preventing other music from
	 * clashing with the level two music and it only plays
	 * if the user has not muted the music in the setting
	 */
	public static void playLevelTwoMusic() {
		// Stop other music from playing
		// If user has not muted the music in settings
		// And the level two music is not already playing
				// Play main menu screen music
	}
	
	/**
	 * Checks if each music is playing and stops it
	 */
	public static void stopMusic() {
		// For each music
				// If music is playing
						// Stop  music
	}
	
	/**
	 * Plays sound effect of player jumping as feedback
	 * for user signifying they have jumped 
	 */
	public static void playJumpSound() {
		playSound(JUMP);
	}
	
	/**
	 * Plays rewarding sound effect as feedback for user
	 * signifying they have pick up something advantageous 
	 */
	public static void playPickUpSound() {
		playSound(POWER_UP);
	}

	/**
	 * Plays sound of gun firing as feedback for user
	 * signifying they have shot their gun
	 */
	public static void playShootSound() {
		playSound(SHOOT);
	}

	/**
	 * Plays sound effect of bullet ricocheting
	 * as feedback for user
	 */
	public static void playRicochetSound() {
		// Play bullet richocheting sound effect
	}

	/**
	 * Plays sound effect of portal usage
	 */
	public static void playPortalSound() {
		playSound(PORTAL);
	}
	
	/**
	 * Play sound effect to inform user the player has
	 * been killed.
	 */
	public static void playPlayerKilledSound() {
		playSound(PLAYER_DEATH);
	}

	/**
	 * Play sound effect to inform user an enemy has
	 * been killed.
	 */
	public static void playEnemyKilledSound() {
		playSound(ENEMY_DEATH);
	}

	/**
	 * Lazily loads a sound effect into memory and returns a reference to it
	 *
	 * If sound effect is not loaded, load it into memory and cache it in the map of sound effects
	 */
	private static Sound getSoundEffect(SOUND_FX sound) {
		if (!soundEffects.containsKey(sound.name())) {
			soundEffects.put(sound.name(), Gdx.audio.newSound(Gdx.files.internal(sound.getPath())));
		}
		return soundEffects.get(sound.name());
	}

	/**
	 * Lazily returns a reference to a music file
	 *
	 * If music is not loaded, load it into memory and cache it in the map of sound effects
	 */
	private static Music getMusicFile(MUSIC music) {
		if (!gameMusic.containsKey(music.name())) {
			gameMusic.put(music.name(), Gdx.audio.newMusic(Gdx.files.internal(music.getPath())));
		}
		return gameMusic.get(music.name());
	}

	/** play a sound effect if the game is not muted */
	private static void playSound(SOUND_FX sound) {
		playSound(sound, false);
	}

	/** play a sound effect. option to override muteness setting */
	private static void playSound(SOUND_FX sound, boolean overrideMute) {
		if (overrideMute || !gameIsMuted) {
			Sound effect = getSoundEffect(sound);
			effect.play();
		}
	}
}