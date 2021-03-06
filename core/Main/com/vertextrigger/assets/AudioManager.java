package com.vertextrigger.assets;

import static com.vertextrigger.assets.MusicPath.*;
import static com.vertextrigger.assets.SoundFxPath.*;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.*;

/**
 * Manages the playing and stopping of music and sound effects
 */
public class AudioManager {
	private static boolean gameIsMuted, gameIsPaused;
	private static final Map<String, Music> activeMusic = new HashMap<>();
	private static final Map<String, Music> gameMusic = new HashMap<>();
	private static final Map<String, Sound> soundEffects = new HashMap<>();

	static {
		for (final SoundFxPath sfx : SoundFxPath.values()) {
			soundEffects.put(sfx.name(), Gdx.audio.newSound(Gdx.files.internal(sfx.getPath())));
		}
	}

	/**
	 * mutes the game if it is unmuted, and vice-versa. plays a sound for feedback irrespective of whether game is muted
	 */
	public static void toggleMute() {
		playSound(BUTTON, true);
		gameIsMuted ^= true;

		if (gameIsMuted) {
			pauseAllMusic();
		} else {
			resumeAllMusic();
		}
	}

	/**
	 * called when level is paused. if we are unmuted then we wish to pause all music. either way we set gameIsPaused to true to disable SFX
	 */
	public static void onPause() {
		if (!gameIsMuted) {
			pauseAllMusic();
		}
		gameIsPaused = true;
	}

	/**
	 * called when level is unpaused. if we are unmuted then we wish to resume all music. either way we set gameIsPaused to false to re-enable SFX
	 */
	public static void onResume() {
		if (!gameIsMuted) {
			resumeAllMusic();
		}
		gameIsPaused = false;
	}

	/** stops any playing music and disposes of everything */
	public static void disposeAll() {
		stopAllMusic();
		for (final Sound s : soundEffects.values()) {
			s.dispose();
		}
		for (final Music m : gameMusic.values()) {
			m.dispose();
		}
	}

	/**
	 * Plays main menu music, while preventing other music from clashing with the main menu music and it only plays if the user has not muted the
	 * music in the setting
	 */
	public static void playMainScreenMusic() {
		stopAllMusic();
		playMusic(MAIN_SCREEN, true);
	}

	/**
	 * Plays level one music, while preventing other music from clashing with the level one music and it only plays if the user has not muted the
	 * music in the setting
	 */
	public static void playLevelOneMusic() {
		playMusic(LEVEL_ONE, true);
	}

	/**
	 * Plays level two music, while preventing other music from clashing with the level two music and it only plays if the user has not muted the
	 * music in the setting
	 */
	public static void playLevelTwoMusic() {
		playMusic(LEVEL_TWO, true);
	}

	/**
	 * Plays sound effect of player jumping as feedback for user signifying they have jumped
	 */
	public static void playJumpSound() {
		playSound(JUMP);
	}

	/**
	 * Plays rewarding sound effect as feedback for user signifying they have pick up something advantageous
	 */
	public static void playPickUpSound() {
		playSound(POWER_UP);
	}

	/**
	 * Plays sound of gun firing as feedback for user signifying they have shot their gun
	 */
	public static void playShootSound() {
		playSound(SHOOT);
	}

	/**
	 * Play sound of bee shooting it stinger/bullet
	 */
	public static void playBeeShootSound() {
		playSound(SHOOT);
	}

	/**
	 * Plays sound effect of bullet ricocheting as feedback for user
	 */
	public static void playRicochetSound() {
		// Play bullet ricocheting sound effect
	}

	/**
	 * Plays sound effect of portal usage
	 */
	public static void playPortalSound() {
		playSound(PORTAL);
	}

	/**
	 * Play sound effect to inform user the player has been killed.
	 */
	public static void playPlayerKilledSound() {
		playSound(PLAYER_DEATH);
	}

	/**
	 * Play sound effect to inform user an enemy has been killed.
	 */
	public static void playEnemyKilledSound() {
		playSound(ENEMY_DEATH);
	}

	/**
	 * Returns a reference to a sound effect
	 */
	private static Sound getSoundEffect(final SoundFxPath sound) {
		return soundEffects.get(sound.name());
	}

	/**
	 * Lazily returns a reference to a music file
	 *
	 * If music is not loaded, load it into memory and cache it in the map of sound effects
	 */
	private static Music getMusicFile(final MusicPath music) {
		if (!gameMusic.containsKey(music.name())) {
			gameMusic.put(music.name(), Gdx.audio.newMusic(Gdx.files.internal(music.getPath())));
		}
		return gameMusic.get(music.name());
	}

	/** play a sound effect if the game is not muted */
	private static void playSound(final SoundFxPath sound) {
		playSound(sound, false);
	}

	/**
	 * play a sound effect if game is unmuted and unpaused. option to override muteness setting
	 */
	private static void playSound(final SoundFxPath sound, final boolean overrideMute) {
		if ((overrideMute || !gameIsMuted) && !gameIsPaused) {
			final Sound effect = getSoundEffect(sound);
			effect.play();
		}
	}

	/**
	 * play a music file by either starting or resuming if it was paused. option to loop (e.g. for level background music)
	 */
	private static void playMusic(final MusicPath music, final boolean loop) {
		if (!activeMusic.containsKey(music.name())) {
			stopAllMusic(); // stop all other music from playing
			startMusic(music, loop);
		}
		resumeMusic(activeMusic.get(music.name()));
	}

	/**
	 * starts some music that is stopped. adds to the active music map to keep track of this. only starts playback if game is not muted
	 */
	private static void startMusic(final MusicPath music, final boolean loop) {
		final Music gameMusic = getMusicFile(music);
		gameMusic.setLooping(loop);
		activeMusic.put(music.name(), gameMusic);

		if (!gameIsMuted) {
			gameMusic.play();
		} else {
			gameMusic.pause();
		}
	}

	/** pauses a music file */
	private static void pauseMusic(final Music music) {
		if (music.isPlaying()) {
			music.pause();
		}
	}

	/** pauses all music files */
	private static void pauseAllMusic() {
		for (final Music m : activeMusic.values()) {
			pauseMusic(m);
		}
	}

	/** resumes a music file if the game is not muted */
	private static void resumeMusic(final Music music) {
		if (!music.isPlaying() && !gameIsMuted) {
			music.play();
		}
	}

	/** resumes all music files */
	private static void resumeAllMusic() {
		for (final Music m : activeMusic.values()) {
			resumeMusic(m);
		}
	}

	/** stops a music file */
	private static void stopMusic(final MusicPath music) {
		final Music stopped = activeMusic.remove(music.name());
		stopped.stop();
	}

	/** stops all music files */
	private static void stopAllMusic() {
		for (final Music m : activeMusic.values()) {
			m.stop();
		}
		activeMusic.clear();
	}
}