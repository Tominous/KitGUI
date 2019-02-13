package com.asyncjay.kitgui.listener;

import com.asyncjay.kitgui.KitGuiPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public final class CommandListener implements Listener {

    private final KitGuiPlugin plugin;

    public CommandListener(KitGuiPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {

    }
}