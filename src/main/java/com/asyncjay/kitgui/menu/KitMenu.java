package com.asyncjay.kitgui.menu;

import com.asyncjay.kitgui.KitGuiPlugin;

public final class KitMenu extends Menu {

    private final KitGuiPlugin plugin;

    public KitMenu(KitGuiPlugin plugin) {
        super(plugin.getConfig().getConfigurationSection("settings.inventory"));
        this.plugin = plugin;
    }

    @Override
    public void draw() {

    }
}