package com.asyncjay.kitgui.listener;

import com.asyncjay.kitgui.KitGuiPlugin;
import com.asyncjay.kitgui.menu.Menu;
import com.asyncjay.kitgui.menu.Menu.MenuItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;

public final class MenuListener implements Listener {

    private final KitGuiPlugin plugin;

    public MenuListener(KitGuiPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();

        if (!(holder instanceof Menu)) {
            return;
        }

        event.setCancelled(true);

        Menu menu = (Menu) holder;
        Player player = (Player) event.getWhoClicked();
        int clickedSlot = event.getRawSlot();

        if (event.getInventory().getSize() <= clickedSlot) {
            return;
        }

        MenuItem clickedItem = menu.getItem(clickedSlot);

        if (clickedItem != null && clickedItem.getListener() != null) {
            clickedItem.getListener().onClick(player, event.getClick());
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();

        if (!(holder instanceof Menu)) {
            return;
        }

        Menu menu = (Menu) holder;
        Player player = (Player) event.getPlayer();

        if (menu.getParent() == null) {
            return;
        }

        this.plugin.getServer().getScheduler().runTaskLater(this.plugin, () -> {
            menu.getParent().openInventory(player);
        }, 2L);
    }
}