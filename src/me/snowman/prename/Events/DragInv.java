package me.snowman.prename.Events;

import me.snowman.prename.ItemRename;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class DragInv implements Listener {
    private ItemRename plugin = ItemRename.getPlugin(ItemRename.class);

    @EventHandler
    public void onDrag(InventoryDragEvent event){
        if(event.getInventory().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.RenameTitle"))) || event.getInventory().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.ColorTitle")))){
                event.setCancelled(true);
        }
    }
}
