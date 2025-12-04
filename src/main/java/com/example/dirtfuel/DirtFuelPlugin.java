package com.example.dirtfuel;

import org.bukkit.plugin.java.JavaPlugin;

public class DirtFuelPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new DirtFuelListener(this), this);

        getServer().getScheduler().runTaskTimer(this, new HopperFuelTask(), 20L, 10L);

        getLogger().info("DirtFuel plugin enabled! Dirt can now be used as furnace fuel.");
    }

    @Override
    public void onDisable() {
        getLogger().info("DirtFuel plugin disabled.");
    }
}
