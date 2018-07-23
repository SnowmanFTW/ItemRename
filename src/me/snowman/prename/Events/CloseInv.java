package me.snowman.prename.Events;

import me.snowman.prename.ItemRename;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class CloseInv implements Listener {
    private ItemRename plugin = ItemRename.getPlugin(ItemRename.class);

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        if (event.getInventory().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.RenameTitle"))) || event.getInventory().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.ColorTitle")))){
            if(event.getInventory().getItem(1) != null){
                event.getPlayer().getInventory().addItem(event.getInventory().getItem(1));
                event.getInventory().setItem(1, new ItemStack(Material.AIR));
            }

            if(event.getInventory().getItem(3) != null){
                event.getPlayer().getInventory().addItem(event.getInventory().getItem(3));
                event.getInventory().setItem(3, new ItemStack(Material.AIR));
            }
        }
    }


}
