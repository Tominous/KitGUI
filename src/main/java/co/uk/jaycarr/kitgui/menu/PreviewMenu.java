package co.uk.jaycarr.kitgui.menu;

import co.uk.jaycarr.kitgui.KitGuiPlugin;
import co.uk.jaycarr.kitgui.kit.KitData;
import org.bukkit.inventory.ItemStack;

public final class PreviewMenu extends Menu {

    private final KitGuiPlugin plugin;
    private final KitData kit;

    public PreviewMenu(KitGuiPlugin plugin, KitData kit) {
        super(plugin.getConfig().getString("settings.previewer.title"), kit.getItems().length);
        this.plugin = plugin;
        this.kit = kit;
    }

    @Override
    public void draw() {
        ItemStack[] items = this.kit.getItems();

        for (int i = 0; i < items.length; i++) {
            this.setItem(i, items[i], null);
        }

        // TODO: Fill with spacer
    }
}