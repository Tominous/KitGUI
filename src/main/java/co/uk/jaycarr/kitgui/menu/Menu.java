package co.uk.jaycarr.kitgui.menu;

import co.uk.jaycarr.kitgui.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class Menu implements InventoryHolder {

    private final Inventory inventory;
    private final Map<Integer, MenuItem> items = new HashMap<>();

    private Menu parent;

    public Menu(String title, int size) {
        this.inventory = Bukkit.createInventory(this, safeInventorySize(size), StringUtil.toColor(title));
    }

    public Menu(ConfigurationSection section) {
        this(section.getString("title"), section.getInt("size", -1));
    }

    @Override
    public final Inventory getInventory() {
        return this.inventory;
    }

    public final void openInventory(Player player) {
        player.openInventory(this.inventory);
    }


    public final Menu getParent() {
        return this.parent;
    }

    public final void setParent(Menu parent) {
        this.parent = parent;
    }

    public final MenuItem getItem(int slot) {
        return this.items.get(slot);
    }

    public final void setItem(int slot, ItemStack item, ClickListener listener) {
        this.items.put(slot, new MenuItem(item, listener));
    }

    public final void fill(ItemStack item, ClickListener listener) {
        MenuItem menuItem = new MenuItem(item, listener);
        for (int i = 0; i < this.inventory.getSize(); i++) {
            this.items.put(i, menuItem);
        }
    }

    private static int safeInventorySize(int input) {
        return (input < 9) ? 9 : ((input > 54) ? 54 : ((input + 8) / 9 * 9));
    }

    public abstract void draw();

    public static class MenuItem {

        private final ItemStack item;
        private final ClickListener listener;

        public MenuItem(ItemStack item, ClickListener listener) {
            this.item = item;
            this.listener = listener;
        }

        public ClickListener getListener() {
            return this.listener;
        }
    }

    @FunctionalInterface
    public interface ClickListener {

        void onClick(Player player, ClickType type);
    }
}