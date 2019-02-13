package com.asyncjay.kitgui.kit;

import com.asyncjay.kitgui.KitGuiPlugin;
import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.Kit;
import com.earth2me.essentials.MetaItemStack;
import com.earth2me.essentials.textreader.SimpleTextInput;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;
import java.util.logging.Level;

public final class KitRegistry {

    private final KitGuiPlugin plugin;
    private final Map<Kit, ItemStack[]> kits = new LinkedHashMap<>();
    private final Map<UUID, BukkitTask> tasks = new HashMap<>();

    public KitRegistry(KitGuiPlugin plugin) {
        this.plugin = plugin;
    }

    public void init() {
        ConfigurationSection section = this.getKitsSection();

        if (section == null) {
            this.plugin.getLogger().severe("Could not retrieve kits from Essentials, disabling...");
            this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
            return;
        }

        section.getKeys(false).forEach(kit -> {
            try {
                Kit essKit = new Kit(kit.toLowerCase(), this.plugin.getEssentials());
                this.kits.put(essKit, this.parseItems(essKit.getItems()));
            } catch (Exception e) {
                this.plugin.getLogger().log(Level.WARNING, "Failed to load kit: " + kit, e);
            }
        });
    }

    private ItemStack[] parseItems(List<String> values) {
        Essentials essentials = this.plugin.getEssentials();
        ItemStack[] items = new ItemStack[values.size()];
        int itemIdx = 0;

        try {
            for (String line : new SimpleTextInput(values).getLines()) {
                String[] parts = line.split(" +");
                ItemStack stack = essentials.getItemDb().get(parts[0], parts.length <= 1 ? 1 : Integer.parseInt(parts[1]));

                if (stack != null && stack.getType() != Material.AIR) {
                    MetaItemStack mStack = new MetaItemStack(stack);
                    if (parts.length > 2) {
                        mStack.parseStringMeta(null, essentials.getSettings().allowUnsafeEnchantments(), parts, 2, essentials);
                    }
                    items[itemIdx++] = mStack.getItemStack();
                }
            }
        } catch (Exception e) {
            this.plugin.getLogger().log(Level.WARNING, "Failed to parse kit items", e);
        }

        return items;
    }

    private ConfigurationSection getKitsSection() {
        Essentials essentials = this.plugin.getEssentials();
        try {
            if (essentials.getClass().getMethod("getKits") != null) {
                return essentials.getKits().getConfig().getConfigurationSection("kits");
            }
        } catch (NoSuchMethodException e) {
            return essentials.getConfig().getConfigurationSection("kits");
        }
        return null;
    }
}