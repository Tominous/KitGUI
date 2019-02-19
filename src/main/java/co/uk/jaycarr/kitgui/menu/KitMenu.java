package co.uk.jaycarr.kitgui.menu;

import co.uk.jaycarr.kitgui.KitGuiPlugin;
import co.uk.jaycarr.kitgui.kit.KitData;

public final class KitMenu extends Menu {

    private final KitGuiPlugin plugin;

    public KitMenu(KitGuiPlugin plugin) {
        super(plugin.getConfig().getConfigurationSection("settings.inventory"));
        this.plugin = plugin;
    }

    @Override
    public void draw() {
        for (KitData kit : this.plugin.getKitRegistry().getKits().values()) {

        }
    }
}