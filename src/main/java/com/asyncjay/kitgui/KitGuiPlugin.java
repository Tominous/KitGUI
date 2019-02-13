package com.asyncjay.kitgui;

import com.asyncjay.kitgui.kit.KitRegistry;
import com.asyncjay.kitgui.listener.MenuListener;
import com.earth2me.essentials.Essentials;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class KitGuiPlugin extends JavaPlugin {

    private Essentials essentials;
    private KitRegistry kitRegistry;

    @Override
    public void onEnable() {
        Plugin plugin = this.getServer().getPluginManager().getPlugin("Essentials");

        if (plugin == null) {
            this.getServer().getLogger().severe("Essentials not found, disabling...");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.essentials = (Essentials) plugin;

        this.kitRegistry = new KitRegistry(this);
        this.kitRegistry.init();

        this.getServer().getPluginManager().registerEvents(new MenuListener(this), this);
    }

    @Override
    public void onDisable() {
        this.getServer().getScheduler().cancelTasks(this);
    }

    public Essentials getEssentials() {
        return this.essentials;
    }

    public KitRegistry getKitRegistry() {
        return this.kitRegistry;
    }
}