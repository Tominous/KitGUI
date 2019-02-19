package co.uk.jaycarr.kitgui;

import co.uk.jaycarr.kitgui.kit.KitRegistry;
import co.uk.jaycarr.kitgui.listener.MenuListener;
import com.earth2me.essentials.Essentials;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class KitGuiPlugin extends JavaPlugin {

    private static final double CONFIG_VERSION = 1.1D;

    private Essentials essentials;
    private KitRegistry kitRegistry;

    @Override
    public void onEnable() {
        PluginManager pluginManager = this.getServer().getPluginManager();

        if (this.getConfig().getDouble("config-version") != KitGuiPlugin.CONFIG_VERSION) {
            this.getServer().getLogger().severe("Unsupported config, delete to regenerate. Disabling...");
            pluginManager.disablePlugin(this);
            return;
        }

        Plugin plugin = pluginManager.getPlugin("Essentials");

        if (plugin == null) {
            this.getServer().getLogger().severe("Essentials not found. Disabling...");
            pluginManager.disablePlugin(this);
            return;
        }

        this.essentials = (Essentials) plugin;

        this.kitRegistry = new KitRegistry(this);
        this.kitRegistry.init();

        pluginManager.registerEvents(new MenuListener(this), this);
    }

    @Override
    public void onDisable() {
        this.getServer().getScheduler().cancelTasks(this);
    }

    public Essentials getEssentials() {
        return this.essentials;
    }

    public KitRegistry getKitRegistry() {
        return this.kitRegistry;
    }
}