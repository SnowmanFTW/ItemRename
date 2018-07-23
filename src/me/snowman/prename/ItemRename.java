package me.snowman.prename;

import me.snowman.prename.Commands.Rename;
import me.snowman.prename.Events.ClickInv;
import me.snowman.prename.Events.CloseInv;
import me.snowman.prename.Events.DragInv;
import net.milkbowl.vault.economy.Economy;
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

import java.util.List;

public class ItemRename extends JavaPlugin {
    public static Economy economy = null;

    public void onEnable(){
        Items i = new Items();
        Metrics metrics = new Metrics(this);
        setupEconomy();
        Config();
        getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "Item Rename has been enabled!" + ChatColor.WHITE + "(V" + this.getDescription().getVersion() + ")");
        getCommand("itemrename").setExecutor(new Rename());
        getCommand("rename").setExecutor(new Rename());
        getCommand("colorize").setExecutor(new Rename());
        getServer().getPluginManager().registerEvents(new ClickInv(), this);
        getServer().getPluginManager().registerEvents(new CloseInv(), this);
        getServer().getPluginManager().registerEvents(new DragInv(), this);
        i.matInit();
    }

    public void onDisable(){
        getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "Item Rename has been disabled!" + ChatColor.WHITE + "(V" + this.getDescription().getVersion() + ")");
    }

    public void Config(){
        saveDefaultConfig();
    }
    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
    public void rename(Player player){
        Items i = new Items();
        new BukkitRunnable(){

            @Override
            public void run(){
                if(!player.getOpenInventory().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.RenameTitle")))){
                    cancel();
                }
                if(player.getOpenInventory().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.RenameTitle")))) {
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
                        if(player.getOpenInventory().getItem(3).hasItemMeta()) {
                            if (player.getOpenInventory().getItem(3).getItemMeta().getLore().equals(i.getLorecolortag())) {
                                player.getOpenInventory().setItem(4, i.error());
                                player.sendMessage(player.getOpenInventory().getItem(3).getItemMeta().getLore().toString());
                                return;
                            }
                        }else{
                            player.getOpenInventory().setItem(4, i.error());
                            return;
                        }
                        if(getConfig().getString("RenameCostEnabled").equalsIgnoreCase("true")) {
                            if (economy.getBalance(player) < Integer.valueOf(getConfig().getString("RenameCost"))) {
                                player.getOpenInventory().setItem(4, i.nomoneyr());
                                return;
                            }
                        }
                        if (player.getOpenInventory().getItem(3).getItemMeta().getLore().equals(i.getLorecolortag()) && !player.getOpenInventory().getItem(1).getType().equals(Material.AIR)) {
                            ItemStack item3 = player.getOpenInventory().getItem(3);
                            if(item3.getItemMeta().hasDisplayName()) {
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
                            if(item3.getItemMeta().hasDisplayName()) {
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

    public void colorize(Player player){
        Items i = new Items();
        new BukkitRunnable(){

            @Override
            public void run(){
                if(!player.getOpenInventory().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.ColorTitle")))){
                    cancel();
                }
                if (player.getOpenInventory().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.ColorTitle")))) {
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
                        if (player.getOpenInventory().getItem(1).getItemMeta().getLore().equals(i.getLorecolortag()) && player.getOpenInventory().getItem(3).hasItemMeta() || player.getOpenInventory().getItem(3).equals(i.red()) || player.getOpenInventory().getItem(3).equals(i.black()) || player.getOpenInventory().getItem(3).equals(i.dgreen()) || player.getOpenInventory().getItem(3).equals(i.blue()) || player.getOpenInventory().getItem(3).equals(i.dpurple()) || player.getOpenInventory().getItem(3).equals(i.daqua()) || player.getOpenInventory().getItem(3).equals(i.gray()) || player.getOpenInventory().getItem(3).equals(i.dgray()) || player.getOpenInventory().getItem(3).equals(i.lpurple()) || player.getOpenInventory().getItem(3).equals(i.green()) || player.getOpenInventory().getItem(3).equals(i.yellow()) || player.getOpenInventory().getItem(3).equals(i.aqua()) || player.getOpenInventory().getItem(3).equals(i.gold()) || player.getOpenInventory().getItem(3).equals(i.white()) || player.getOpenInventory().getItem(3).equals(i.bold()) || player.getOpenInventory().getItem(3).equals(i.italic()) || player.getOpenInventory().getItem(3).equals(i.locked())) {
                            player.getOpenInventory().setItem(4, i.readyc());
                            List<String> rename = getConfig().getStringList("TagRenameLore");
                            rename.replaceAll(string -> ChatColor.translateAlternateColorCodes('&', string));
                            ItemStack item1 = player.getOpenInventory().getItem(1);
                            if (item1.getItemMeta().hasDisplayName()) {
                                if (player.getOpenInventory().getItem(3).getItemMeta().getLore().equals(i.getDyelore())) {
                                    ItemStack item7 = new ItemStack(item1);
                                    ItemMeta meta = item7.getItemMeta();
                                    if (player.getOpenInventory().getItem(3).equals(i.locked())) {
                                        meta.setLore(i.getLockedlore());
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.italic())) {
                                        meta.setDisplayName(ChatColor.getLastColors(item1.getItemMeta().getDisplayName()) + ChatColor.ITALIC + ChatColor.stripColor(item1.getItemMeta().getDisplayName()));
                                        meta.addEnchant(Enchantment.LURE, 0, true);
                                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.bold())) {
                                        meta.setDisplayName(ChatColor.getLastColors(item1.getItemMeta().getDisplayName()) + ChatColor.BOLD + ChatColor.stripColor(item1.getItemMeta().getDisplayName()));
                                        meta.addEnchant(Enchantment.LURE, 0, true);
                                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.black())) {
                                        meta.setDisplayName(ChatColor.BLACK + ChatColor.stripColor(item1.getItemMeta().getDisplayName()));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.red())) {
                                        meta.setDisplayName(ChatColor.RED + ChatColor.stripColor(item1.getItemMeta().getDisplayName()));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.dgreen())) {
                                        meta.setDisplayName(ChatColor.DARK_GREEN + ChatColor.stripColor(item1.getItemMeta().getDisplayName()));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.blue())) {
                                        meta.setDisplayName(ChatColor.BLUE + ChatColor.stripColor(item1.getItemMeta().getDisplayName()));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.dpurple())) {
                                        meta.setDisplayName(ChatColor.DARK_PURPLE + ChatColor.stripColor(item1.getItemMeta().getDisplayName()));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.daqua())) {
                                        meta.setDisplayName(ChatColor.DARK_AQUA + ChatColor.stripColor(item1.getItemMeta().getDisplayName()));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.gray())) {
                                        meta.setDisplayName(ChatColor.GRAY + ChatColor.stripColor(item1.getItemMeta().getDisplayName()));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.dgray())) {
                                        meta.setDisplayName(ChatColor.DARK_GRAY + ChatColor.stripColor(item1.getItemMeta().getDisplayName()));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.lpurple())) {
                                        meta.setDisplayName(ChatColor.LIGHT_PURPLE + ChatColor.stripColor(item1.getItemMeta().getDisplayName()));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.green())) {
                                        meta.setDisplayName(ChatColor.GREEN + ChatColor.stripColor(item1.getItemMeta().getDisplayName()));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.yellow())) {
                                        meta.setDisplayName(ChatColor.YELLOW + ChatColor.stripColor(item1.getItemMeta().getDisplayName()));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.aqua())) {
                                        meta.setDisplayName(ChatColor.AQUA + ChatColor.stripColor(item1.getItemMeta().getDisplayName()));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.gold())) {
                                        meta.setDisplayName(ChatColor.GOLD + ChatColor.stripColor(item1.getItemMeta().getDisplayName()));
                                        meta.setLore(rename);
                                        item7.setItemMeta(meta);
                                        player.getOpenInventory().setItem(7, item7);
                                        return;
                                    }
                                    if (player.getOpenInventory().getItem(3).equals(i.white())) {
                                        meta.setDisplayName(ChatColor.WHITE + ChatColor.stripColor(item1.getItemMeta().getDisplayName()));
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
}
