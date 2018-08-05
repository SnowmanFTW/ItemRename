package me.snowman.prename.Events;

import me.snowman.prename.ItemRename;
import me.snowman.prename.Utils.Items;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ClickInv implements Listener {
    private ItemRename plugin = ItemRename.getPlugin(ItemRename.class);
    private static Economy econ = null;
    Items i = new Items();

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        List<String> lorerenametag = plugin.getConfig().getStringList("TagRenameLore");
        lorerenametag.replaceAll(string -> ChatColor.translateAlternateColorCodes('&', string));

        List<String> lorecolortag = plugin.getConfig().getStringList("TagColorLore");
        lorecolortag.replaceAll(string -> ChatColor.translateAlternateColorCodes('&', string));

        List<String> dyelore = plugin.getConfig().getStringList("DyeLore");
        dyelore.replaceAll(string -> ChatColor.translateAlternateColorCodes('&', string));

        List<String> lockedlore = plugin.getConfig().getStringList("LockedLore");
        lockedlore.replaceAll(string -> ChatColor.translateAlternateColorCodes('&', string));

        ItemStack item = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();
        if(event.getInventory().getType() == InventoryType.ANVIL){
            if(event.getSlotType() == InventoryType.SlotType.RESULT){
                if(event.getInventory().getItem(0).getItemMeta().getLore().equals(lorerenametag) || event.getInventory().getItem(0).getItemMeta().getLore().equals(lockedlore) || event.getInventory().getItem(0).getItemMeta().getLore().equals(dyelore)){
                    event.setCancelled(true);
                }
            }
        }
        if (player.getOpenInventory().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.RenameTitle"))) || player.getOpenInventory().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.ColorTitle")))) {
            if (event.getClickedInventory() == null) {
                return;
            }
            if (item == new ItemStack(Material.AIR)) {
                event.setCancelled(true);
            }
            if(event.getClickedInventory() != player.getInventory() && event.getRawSlot() == 7){
                event.setCancelled(true);
            }
            if(event.isShiftClick()){
                if(event.getRawSlot() == 3 || event.getRawSlot() == 1){
                    return;
                }
            }
            if(event.isShiftClick()){
                if(event.getInventory().firstEmpty() == 7){
                    event.setCancelled(true);
                }
            }
            if (event.getCurrentItem().equals(i.empty()) || event.getCurrentItem().equals(i.waiting()) || event.getCurrentItem().equals(i.error()) || event.getCurrentItem().equals(i.nomoneyc()) || event.getCurrentItem().equals(i.nomoneyr())) {
                event.setCancelled(true);
            }
            if (event.getInventory().getItem(3) == null) {
                return;
           }
            if(event.getInventory().getItem(1) == null){
                return;
           }
            if(event.getCurrentItem().equals(i.readyc()) && player.getOpenInventory().getItem(1).getItemMeta().getLore().equals(i.getLorerenametag()) && player.getOpenInventory().getItem(3).equals(i.bold(1)) || player.getOpenInventory().getItem(3).equals(i.italic(1)) || player.getOpenInventory().getItem(3).equals(i.locked(1))){
                if(event.getInventory().getItem(7) == null){
                    return;
                }
                player.getInventory().addItem(event.getInventory().getItem(7));
                event.getInventory().setItem(3, new ItemStack(Material.AIR));
                event.getInventory().setItem(1, new ItemStack(Material.AIR));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.SuccesColor")));
                player.closeInventory();
                event.setCancelled(true);
                return;
            }
            if(event.getCurrentItem().equals(i.readyc())){
                if(player.getOpenInventory().getItem(1).hasItemMeta() || player.getOpenInventory().getItem(1).getItemMeta().getLore().equals(i.getLorecolortag()) || player.getOpenInventory().getItem(1).getItemMeta().getLore().equals(i.getLorerenametag())) {
                    player.getInventory().addItem(event.getInventory().getItem(7));
                    event.getInventory().setItem(3, new ItemStack(Material.AIR));
                    event.getInventory().setItem(1, new ItemStack(Material.AIR));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.SuccesColor")));
                    player.closeInventory();
                    if (plugin.getConfig().getString("ColorizeCostEnabled").equalsIgnoreCase("true")) {
                        plugin.economy.withdrawPlayer(player.getName(), Double.valueOf(plugin.getConfig().getString("ColorizeCost")));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.PaidColor")).replace("%required%", plugin.getConfig().getString("ColorizeCost")));
                    }
                }
                event.setCancelled(true);
                return;
            }
            if(!(event.getInventory().getItem(1) == null) && !(event.getInventory().getItem(3) == null) && event.getInventory().getItem(3).hasItemMeta()) {
                if (event.getCurrentItem().equals(i.readyr()) &&player.getOpenInventory().getItem(3).hasItemMeta() || player.getOpenInventory().getItem(3).getItemMeta().getLore().equals(i.getLorerenametag()) || player.getOpenInventory().getItem(3).getItemMeta().getLore().equals(i.getLockedlore()) || player.getOpenInventory().getItem(3).getItemMeta().getLore().equals(i.getLorecolortag())) {
                    if(event.getInventory().getItem(7) == null){
                        return;
                    }
                    player.getInventory().addItem(event.getInventory().getItem(7));
                    event.getInventory().setItem(3, new ItemStack(Material.AIR));
                    event.getInventory().setItem(1, new ItemStack(Material.AIR));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.SuccesRename")));
                    if(plugin.getConfig().getString("RenameCostEnabled").equalsIgnoreCase("true")){
                        plugin.economy.withdrawPlayer(player, Double.valueOf(plugin.getConfig().getString("RenameCost")));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.PaidRename")).replace("%required%", plugin.getConfig().getString("RenameCost")));
                    }
                    player.closeInventory();
                    event.setCancelled(true);
                }
            }
        }
    }
}