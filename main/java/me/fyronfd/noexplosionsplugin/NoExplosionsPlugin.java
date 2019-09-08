package me.fyronfd.noexplosionsplugin;

import me.fyronfd.noexplosionsplugin.Events.NoExplosions;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoExplosionsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new NoExplosions(), this);
    }
}
