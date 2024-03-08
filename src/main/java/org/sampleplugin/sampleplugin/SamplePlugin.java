package org.sampleplugin.sampleplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class SamplePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getCommand("kit").setExecutor(new KitCommand());
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable has been invoked!");
    }
}
