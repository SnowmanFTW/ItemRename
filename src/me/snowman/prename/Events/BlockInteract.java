package me.snowman.prename.Events;

import me.snowman.prename.ItemRename;
import me.snowman.prename.Utils.ConfigManager;
import me.snowman.prename.Utils.Items;
import me.snowman.prename.Utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class BlockInteract implements Listener {
    private ItemRename plugin = ItemRename.getPlugin(ItemRename.class);
    Items i = new Items();
    MessageUtils msgUtils = new MessageUtils();
    ConfigManager c = new ConfigManager();
    int r;
    ArrayList<String> keys = new ArrayList<>();
    @EventHandler
    public void onClick(PlayerInteractEvent event){
        c.reloadData();
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if(event.getHand().equals(EquipmentSlot.OFF_HAND)) return;
        if(block == null) return;

        for(String key : c.getData().getKeys(false)){
            keys.add(key);
        }

        for(r = 0; r < c.getData().getKeys(false).size(); r++){
            if(block.getWorld() == Bukkit.getWorld(c.getData().getString(keys.get(r) + ".World")) && block.getX() == Integer.valueOf(c.getData().getString(keys.get(r) + ".X")) && block.getY() == Integer.valueOf(c.getData().getString(keys.get(r) + ".Y")) && block.getZ() == Integer.valueOf(c.getData().getString(keys.get(r) + ".Z"))){
                if(player.isSneaking() && event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                    Location loc = block.getLocation();
                    loc.setX(loc.getX() + 0.5);
                    loc.setY(loc.getY() - 0.5);
                    loc.setZ(loc.getZ() + 0.5);
                    block.breakNaturally();
                    c.getData().set(keys.get(r), null);
                    c.saveData();
                    keys.remove(keys);
                    for(Entity e : player.getLocation().getChunk().getEntities()){
                       if(e.getName().equalsIgnoreCase(msgUtils.colorize(plugin.getConfig().getString("Messages.RenameHolo"))) || e.getName().equalsIgnoreCase(msgUtils.colorize(plugin.getConfig().getString("Messages.ColorizeHolo")))){
                           if(e.getLocation().equals(loc)) {
                               e.remove();
                               return;
                           }
                       }
                    }
                    return;
                }
                if(c.getData().getString(keys.get(r) + ".Type").equalsIgnoreCase("colorize")){
                    Inventory invcol = Bukkit.createInventory(null, 9, msgUtils.colorize(plugin.getConfig().getString("Messages.ColorTitle")));

                    invcol.setItem(4, i.waiting());
                    invcol.setItem(0, i.empty());
                    invcol.setItem(2, i.empty());
                    invcol.setItem(6, i.empty());
                    invcol.setItem(5, i.empty());
                    invcol.setItem(8, i.empty());

                    player.openInventory(invcol);
                    plugin.rename(player);

                    event.setCancelled(true);
                    keys.removeAll(keys);
                    return;
                }else if(c.getData().getString(keys.get(r) + ".Type").equalsIgnoreCase("rename")){
                    Inventory invren = Bukkit.createInventory(null, 9, msgUtils.colorize(plugin.getConfig().getString("Messages.RenameTitle")));

                    invren.setItem(4, i.waiting());
                    invren.setItem(0, i.empty());
                    invren.setItem(2, i.empty());
                    invren.setItem(6, i.empty());
                    invren.setItem(5, i.empty());
                    invren.setItem(8, i.empty());

                    player.openInventory(invren);
                    plugin.rename(player);

                    event.setCancelled(true);
                    keys.removeAll(keys);
                    return;
                }
            }
        }
        keys.removeAll(keys);
    }
}
