package co.uk.jaycarr.kitgui.kit;

import com.earth2me.essentials.Kit;
import org.apache.commons.lang.WordUtils;
import org.bukkit.inventory.ItemStack;

public final class KitData {

    private final String name;
    private final Kit kit;
    private final ItemStack[] items;
    private final long cooldown;

    public KitData(Kit kit, ItemStack[] items, long cooldown) {
        this.name = WordUtils.capitalize(kit.getName());
        this.kit = kit;
        this.items = items;
        this.cooldown = cooldown;
    }

    public String getName() {
        return this.name;
    }

    public Kit getKit() {
        return this.kit;
    }

    public ItemStack[] getItems() {
        return this.items;
    }

    public long getCooldown() {
        return this.cooldown;
    }
}