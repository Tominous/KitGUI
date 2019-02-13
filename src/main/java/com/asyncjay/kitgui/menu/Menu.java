package com.asyncjay.kitgui.menu;

import com.asyncjay.kitgui.util.StringUtil;
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
    private final boolean autoSize;
    private final Map<Integer, MenuItem> items = new HashMap<>();

    private Menu parent;

    public Menu(String title, int size) {
        this.autoSize = size % 9 == 0;
        this.inventory = Bukkit.createInventory(this, (this.autoSize ? 9 : size), StringUtil.toColor(title));
    }

    public Menu(ConfigurationSection section) {
        this(section.getString("title"), section.getInt("size", -1));
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    public void openInventory(Player player) {
        player.openInventory(this.inventory);
    }

    public MenuItem getItem(int slot) {
        return this.items.get(slot);
    }

    public Menu getParent() {
        return this.parent;
    }

    public void setItem(int slot, ItemStack item, ClickListener listener) {
        this.items.put(slot, new MenuItem(item, listener));
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
            return listener;
        }
    }

    @FunctionalInterface
    public interface ClickListener {

        void onClick(Player player, ClickType type);
    }
}