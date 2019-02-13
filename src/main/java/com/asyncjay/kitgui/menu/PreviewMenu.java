package com.asyncjay.kitgui.menu;

import com.asyncjay.kitgui.KitGuiPlugin;

public final class PreviewMenu extends Menu {

    private final KitGuiPlugin plugin;

    public PreviewMenu(KitGuiPlugin plugin) {
        super(plugin.getConfig().getConfigurationSection("settings.previewer"));
        this.plugin = plugin;
    }

    @Override
    public void draw() {

    }
}