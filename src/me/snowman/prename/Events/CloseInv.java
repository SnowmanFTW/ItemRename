package me.snowman.prename.Events;

import me.snowman.prename.ItemRename;
import me.snowman.prename.Utils.MessageUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class CloseInv implements Listener {
    private ItemRename plugin = ItemRename.getPlugin(ItemRename.class);
    private static MessageUtils msgUtils = new MessageUtils();

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        if (event.getInventory().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.RenameTitle"))) || event.getInventory().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.ColorTitle")))){
            if(event.getInventory().getTitle().equalsIgnoreCase(msgUtils.colorize(plugin.getConfig().getString("Messages.RenameTitle")))) {
                if(event.getInventory().getItem(plugin.getConfig().getInt("GUI.Rename.Slot1")) != null){
                    event.getPlayer().getInventory().addItem(event.getInventory().getItem(plugin.getConfig().getInt("GUI.Rename.Slot1")));
                    event.getInventory().setItem(plugin.getConfig().getInt("GUI.Rename.Slot1"), new ItemStack(Material.AIR));
                }
                if (event.getInventory().getItem(plugin.getConfig().getInt("GUI.Rename.Slot2")) != null) {
                    event.getPlayer().getInventory().addItem(event.getInventory().getItem(plugin.getConfig().getInt("GUI.Rename.Slot2")));
                    event.getInventory().setItem(plugin.getConfig().getInt("GUI.Rename.Slot2"), new ItemStack(Material.AIR));
                }
            }

            if(event.getInventory().getTitle().equalsIgnoreCase(msgUtils.colorize(plugin.getConfig().getString("Messages.ColorTitle")))) {
                if (event.getInventory().getItem(plugin.getConfig().getInt("GUI.Colorize.Slot2")) != null) {
                    event.getPlayer().getInventory().addItem(event.getInventory().getItem(plugin.getConfig().getInt("GUI.Colorize.Slot2")));
                    event.getInventory().setItem(plugin.getConfig().getInt("GUI.Colorize.Slot2"), new ItemStack(Material.AIR));
                }
                if(event.getInventory().getItem(plugin.getConfig().getInt("GUI.Colorize.Slot1")) != null){
                    event.getPlayer().getInventory().addItem(event.getInventory().getItem(plugin.getConfig().getInt("GUI.Colorize.Slot1")));
                    event.getInventory().setItem(plugin.getConfig().getInt("GUI.Colorize.Slot1"), new ItemStack(Material.AIR));
                }
           }
        }
    }


}
