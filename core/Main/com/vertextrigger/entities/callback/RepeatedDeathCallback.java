package com.vertextrigger.entities.callback;

import com.vertextrigger.entities.Mortal;
import com.vertextrigger.entities.player.Player;

/**
 * Implementation of {@link Callback} that triggers after a {@link Mortal} has died a specific number of times
 *
 * A good use for this would be dynamic difficulty - e.g. drop a powerup or or stop spawning an enemy if Player has died x times
 */
public class RepeatedDeathCallback extends Callback {

    final private int deathThreshold;

    private int prevDeaths = Player.getDeaths();

    public RepeatedDeathCallback(final Runnable runnable, final int deathThreshold) {
        super(RunLimit.single(), runnable);
        this.deathThreshold = deathThreshold;
    }

    @Override
    public boolean isRunnable() {
        final int currentDeaths = Player.getDeaths();

        // this logic prevents callback executing twice as it naturally would, e.g.
        // player dies, making deaths equal to $threshold (exec #1), and then again when player respawns and deaths is still equal to $threshold (exec #2)
        // RunLimit can't help here as a new 'single' instance is created each time, whereas the death count is static and persists
        return currentDeaths - prevDeaths == 1 && currentDeaths == deathThreshold;
    }
}
