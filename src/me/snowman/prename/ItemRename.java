package me.snowman.prename;

import me.snowman.prename.Commands.Rename;
import me.snowman.prename.Events.BlockInteract;
import me.snowman.prename.Events.ClickInv;
import me.snowman.prename.Events.CloseInv;
import me.snowman.prename.Events.DragInv;
import me.snowman.prename.Utils.ConfigManager;
import me.snowman.prename.Utils.Items;
import me.snowman.prename.Utils.MessageUtils;
import me.snowman.prename.Utils.Metrics;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class ItemRename extends JavaPlugin {
    public static Economy economy = null;

    MessageUtils msgUtils = new MessageUtils();

    public void onEnable() {
        Items i = new Items();
        Metrics metrics = new Metrics(this);
        if(Bukkit.getServer().getPluginManager().isPluginEnabled("Vault")){
            setupEconomy();
            getServer().getConsoleSender().sendMessage(msgUtils.colorize("&9Economy plugin found."));
        }else{
            getServer().getConsoleSender().sendMessage(msgUtils.colorize("&fVault &9not found. Economy will not work."));
            getConfig().set("ColorizeCostEnabled", false);
            getConfig().set("RenameCostEnabled", false);
            saveConfig();
        }

        i.matInit();
        Config();
        ConfigManager c = new ConfigManager();
        c.setupData();

        getCommand("itemrename").setExecutor(new Rename());
        getCommand("rename").setExecutor(new Rename());
        getCommand("colorize").setExecutor(new Rename());
        getServer().getPluginManager().registerEvents(new ClickInv(), this);
        getServer().getPluginManager().registerEvents(new CloseInv(), this);
        getServer().getPluginManager().registerEvents(new DragInv(), this);
        getServer().getPluginManager().registerEvents(new BlockInteract(), this);
        getServer().getConsoleSender().sendMessage(msgUtils.colorize("&9Item Rename has been enabled! &f(V" + this.getDescription().getVersion() + ")"));
        updatePlugin();
    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage(msgUtils.colorize("&9Item Rename has been disabled! &f(V" + this.getDescription().getVersion() + ")"));
    }

    public void Config() {
        saveDefaultConfig();
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    public void rename(Player player) {
        Items i = new Items();
        new BukkitRunnable() {

            @Override
            public void run() {
                if (!player.getOpenInventory().getTitle().equalsIgnoreCase(msgUtils.colorize(getConfig().getString("Messages.RenameTitle")))) {
                    cancel();
                }
                if (player.getOpenInventory().getTitle().equalsIgnoreCase(msgUtils.colorize(getConfig().getString("Messages.RenameTitle")))) {
                    if (player.getOpenInventory().getItem(3).getType().equals(Material.AIR) && player.getOpenInventory().getItem(1).getType().equals(Material.AIR)) {
                        player.getOpenInventory().setItem(4, i.waiting());
                        player.getOpenInventory().setItem(7, new ItemStack(Material.AIR));
                        return;
                    } else if (player.getOpenInventory().getItem(3).getType().equals(Material.AIR)) {
                        player.getOpenInventory().setItem(4, i.error());
                        player.getOpenInventory().setItem(7, new ItemStack(Material.AIR));
                        return;
                    } else if (player.getOpenInventory().getItem(1).getType().equals(Material.AIR)) {
                        player.getOpenInventory().setItem(4, i.error());
                        player.getOpenInventory().setItem(7, new ItemStack(Material.AIR));
                        return;
                    } else if (!player.getOpenInventory().getItem(3).getType().equals(Material.AIR)) {
                        if (player.getOpenInventory().getItem(3).hasItemMeta()) {
                            if (player.getOpenInventory().getItem(3).getItemMeta().getLore().equals(i.getLorecolortag())) {
                                player.getOpenInventory().setItem(4, i.error());
                                player.sendMessage(player.getOpenInventory().getItem(3).getItemMeta().getLore().toString());
                                return;
                            }
                        } else {
                            player.getOpenInventory().setItem(4, i.error());
                            return;
                        }
                        if (getConfig().getString("RenameCostEnabled").equalsIgnoreCase("true")) {
                            if(economy.has(player, 0)){
                                player.getOpenInventory().setItem(4, i.nomoneyr());
                                return;
                            }
                            if (economy.getBalance(player) < Integer.valueOf(getConfig().getString("RenameCost"))) {
                                player.getOpenInventory().setItem(4, i.nomoneyr());
                                return;
                            }
                        }
                        if (player.getOpenInventory().getItem(3).getItemMeta().getLore().equals(i.getLorecolortag()) && !player.getOpenInventory().getItem(1).getType().equals(Material.AIR)) {
                            ItemStack item3 = player.getOpenInventory().getItem(3);
                            if (item3.getItemMeta().hasDisplayName()) {
                                player.getOpenInventory().setItem(4, i.readyr());
                                ItemStack item1 = new ItemStack(player.getOpenInventory().getItem(1));
                                ItemStack item7 = new ItemStack(item1);
                                ItemMeta meta = item7.getItemMeta();
                                meta.setDisplayName(ChatColor.WHITE + item3.getItemMeta().getDisplayName());
                                item7.setItemMeta(meta);
                                player.getOpenInventory().setItem(7, item7);
                                return;
                            }
                        }
                        if (player.getOpenInventory().getItem(3).getItemMeta().getLore().equals(i.getLorerenametag()) || player.getOpenInventory().getItem(3).getItemMeta().getLore().equals(i.getLockedlore()) && !player.getOpenInventory().getItem(1).getType().equals(Material.AIR)) {
                            ItemStack item3 = player.getOpenInventory().getItem(3);
                            if (item3.getItemMeta().hasDisplayName()) {
                                player.getOpenInventory().setItem(4, i.readyr());
                                ItemStack item1 = new ItemStack(player.getOpenInventory().getItem(1));
                                ItemStack item7 = new ItemStack(item1);
                                ItemMeta meta = item7.getItemMeta();
                                meta.setDisplayName(item3.getItemMeta().getDisplayName());
                                item7.setItemMeta(meta);
                                player.getOpenInventory().setItem(7, item7);
                                return;
                            }
                        }
                    }
                }
            }
        }.runTaskTimerAsynchronously(this, 0, 1);
    }

    public void colorize(Player player) {
        Items i = new Items();
        new BukkitRunnable() {

            @Override
            public void run() {
                if (!player.getOpenInventory().getTitle().equalsIgnoreCase(msgUtils.colorize(getConfig().getString("Messages.ColorTitle")))) {
                    cancel();
                }
                if (player.getOpenInventory().getTitle().equalsIgnoreCase(msgUtils.colorize(getConfig().getString("Messages.ColorTitle")))) {
                    if (player.getOpenInventory().getItem(3).getType().equals(Material.AIR) && player.getOpenInventory().getItem(1).getType().equals(Material.AIR)) {
                        player.getOpenInventory().setItem(4, i.waiting());
                        player.getOpenInventory().setItem(7, new ItemStack(Material.AIR));
                        return;
                    }
                    if (player.getOpenInventory().getItem(3).getType().equals(Material.AIR)) {
                        player.getOpenInventory().setItem(4, i.error());
                        player.getOpenInventory().setItem(7, new ItemStack(Material.AIR));
                        return;
                    }
                    if (player.getOpenInventory().getItem(1).getType().equals(Material.AIR)) {
                        player.getOpenInventory().setItem(4, i.error());
                        player.getOpenInventory().setItem(7, new ItemStack(Material.AIR));
                        return;
                    }
                    if (!player.getOpenInventory().getItem(1).getType().equals(Material.AIR)) {
                        if (player.getOpenInventory().getItem(1).hasItemMeta()) {
                            if (player.getOpenInventory().getItem(1).getItemMeta().getLore().equals(i.getLockedlore())) {
                                player.getOpenInventory().setItem(4, i.error());
                                return;
                            }
                        } else {
                            return;
                        }
                        if (player.getOpenInventory().getItem(1).getItemMeta().getLore() == null) {
                            player.getOpenInventory().setItem(4, i.error());
                            return;
                        }
                        if (getConfig().getString("ColorizeCostEnabled").equalsIgnoreCase("true")) {
                            if (economy.getBalance(player) < Integer.valueOf(getConfig().getString("ColorizeCost"))) {
                                player.getOpenInventory().setItem(4, i.nomoneyc());
                                return;
                            }
                        }
                        if (player.getOpenInventory().getItem(1).getItemMeta().getLore().equals(i.getLorecolortag()) && player.getOpenInventory().getItem(3).hasItemMeta() || player.getOpenInventory().getItem(3).equals(i.darkred(1)) || player.getOpenInventory().getItem(3).equals(i.black(1)) || player.getOpenInventory().getItem(3).equals(i.green(1)) || player.getOpenInventory().getItem(3).equals(i.darkblue(1)) || player.getOpenInventory().getItem(3).equals(i.magenta(1)) || player.getOpenInventory().getItem(3).equals(i.aqua(1)) || player.getOpenInventory().getItem(3).equals(i.gray(1)) || player.getOpenInventory().getItem(3).equals(i.darkgray(1)) || player.getOpenInventory().getItem(3).equals(i.pink(1)) || player.getOpenInventory().getItem(3).equals(i.lime(1)) || player.getOpenInventory().getItem(3).equals(i.yellow(1)) || player.getOpenInventory().getItem(3).equals(i.cyan(1)) || player.getOpenInventory().getItem(3).equals(i.orange(1)) || player.getOpenInventory().getItem(3).equals(i.blue(1)) || player.getOpenInventory().getItem(3).equals(i.lightred(1)) || player.getOpenInventory().getItem(3).equals(i.white(1)) || player.getOpenInventory().getItem(3).equals(i.bold(1)) || player.getOpenInventory().getItem(3).equals(i.italic(1)) || player.getOpenInventory().getItem(3).equals(i.locked(1))) {
                            player.getOpenInventory().setItem(4, i.readyc());
                            List<String> rename = getConfig().getStringList("TagRenameLore");
                            rename.replaceAll(string -> msgUtils.colorize(string));
                            ItemStack item1 = player.getOpenInventory().getItem(1);

                            if (item1.getItemMeta().hasDisplayName()) {
                                if (player.getOpenInventory().getItem(3).getItemMeta().getLore().equals(i.getDyelore())) {
                                    ItemStack item7 = new ItemStack(item1);
                                    ItemMeta meta = item7.getItemMeta();
                                    if (player.getOpenInventory().getItem(3).equals(i.locked(1))) {
                                        meta.setLore(i.getLockedlore());
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.italic(1))) {
                                        meta.setDisplayName(ChatColor.getLastColors(item1.getItemMeta().getDisplayName()) + ChatColor.ITALIC + ChatColor.stripColor(item1.getItemMeta().getDisplayName()));
                                        meta.addEnchant(Enchantment.LURE, 0, true);
                                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.bold(1))) {
                                        meta.setDisplayName(ChatColor.getLastColors(item1.getItemMeta().getDisplayName()) + ChatColor.BOLD + ChatColor.stripColor(item1.getItemMeta().getDisplayName()));
                                        meta.addEnchant(Enchantment.LURE, 0, true);
                                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.black(1))) {
                                        meta.setDisplayName(msgUtils.colorize("&0" + ChatColor.stripColor(item1.getItemMeta().getDisplayName())));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.darkblue(1))) {
                                        meta.setDisplayName(msgUtils.colorize("&1" + ChatColor.stripColor(item1.getItemMeta().getDisplayName())));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.green(1))) {
                                        meta.setDisplayName(msgUtils.colorize("&2" + ChatColor.stripColor(item1.getItemMeta().getDisplayName())));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.cyan(1))) {
                                        meta.setDisplayName(msgUtils.colorize("&3" + ChatColor.stripColor(item1.getItemMeta().getDisplayName())));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.darkred(1))) {
                                        meta.setDisplayName(msgUtils.colorize("&4" + ChatColor.stripColor(item1.getItemMeta().getDisplayName())));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.magenta(1))) {
                                        meta.setDisplayName(msgUtils.colorize("&5" + ChatColor.stripColor(item1.getItemMeta().getDisplayName())));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.orange(1))) {
                                        meta.setDisplayName(msgUtils.colorize("&6" + ChatColor.stripColor(item1.getItemMeta().getDisplayName())));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.gray(1))) {
                                        meta.setDisplayName(msgUtils.colorize("&7" + ChatColor.stripColor(item1.getItemMeta().getDisplayName())));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.darkgray(1))) {
                                        meta.setDisplayName(msgUtils.colorize("&8" + ChatColor.stripColor(item1.getItemMeta().getDisplayName())));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.blue(1))) {
                                        meta.setDisplayName(msgUtils.colorize("&9" + ChatColor.stripColor(item1.getItemMeta().getDisplayName())));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.lime(1))) {
                                        meta.setDisplayName(msgUtils.colorize("&a" + ChatColor.stripColor(item1.getItemMeta().getDisplayName())));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.aqua(1))) {
                                        meta.setDisplayName(msgUtils.colorize("&b" + ChatColor.stripColor(item1.getItemMeta().getDisplayName())));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.lightred(1))) {
                                        meta.setDisplayName(msgUtils.colorize("&c" + ChatColor.stripColor(item1.getItemMeta().getDisplayName())));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.pink(1))) {
                                        meta.setDisplayName(msgUtils.colorize("&d" + ChatColor.stripColor(item1.getItemMeta().getDisplayName())));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.yellow(1))) {
                                        meta.setDisplayName(msgUtils.colorize("&e" + ChatColor.stripColor(item1.getItemMeta().getDisplayName())));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.white(1))) {
                                        meta.setDisplayName(msgUtils.colorize("&f" + ChatColor.stripColor(item1.getItemMeta().getDisplayName())));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }.runTaskTimerAsynchronously(this, 0, 1);
    }

    public void updatePlugin(){
        new BukkitRunnable(){

            @Override
            public void run(){
                try {
                    String spigotId = "58756";
                    // Open connection with spigot's web API
                    HttpsURLConnection connection = (HttpsURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=" + spigotId).openConnection();

                    // Versions
                    String latest = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine().toLowerCase();
                    String installed = getDescription().getVersion().toLowerCase();

                    // Check if it's outdated or not
                    if (!installed.equals(latest)) {
                        getServer().getConsoleSender().sendMessage(msgUtils.colorize("&9Your plugin version &f(&9" + installed + "&f) &9is not the latest one &f(&9" + latest + "&f)\n&9You can download it here: &fhttps://www.spigotmc.org/resources/item-entity-rename-1-8-1-13.58756/\n&9Or by typing &f/ir update &9in game."));
                    } else {
                        getServer().getConsoleSender().sendMessage(msgUtils.colorize("&9Plugin version is up-to-date &f(&9" + installed + "&f)&9."));
                    }
                } catch (Exception e) {
                    getServer().getConsoleSender().sendMessage(msgUtils.colorize("&cThere was an error connecting to SpigotMC API."));
                }
            }
        }.runTaskLaterAsynchronously(this, 40);
    }
}
