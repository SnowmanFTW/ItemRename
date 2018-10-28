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
                    for(Entity e : player.getWorld().getEntities()){
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
                    for(int sl = 0; sl < plugin.getConfig().getInt("GUI.Colorize.Size"); sl++){
                        if(plugin.getConfig().getString("GUI.Colorize.Color").equalsIgnoreCase("black")){
                            invcol.setItem(sl, i.blackp());
                        }
                        if(plugin.getConfig().getString("GUI.Colorize.Color").equalsIgnoreCase("brown")){
                            invcol.setItem(sl, i.brownp());
                        }
                        if(plugin.getConfig().getString("GUI.Colorize.Color").equalsIgnoreCase("blue")){
                            invcol.setItem(sl, i.bluep());
                        }
                        if(plugin.getConfig().getString("GUI.Colorize.Color").equalsIgnoreCase("cyan")){
                            invcol.setItem(sl, i.cyanp());
                        }
                        if(plugin.getConfig().getString("GUI.Colorize.Color").equalsIgnoreCase("magenta")){
                            invcol.setItem(sl, i.magentap());
                        }
                        if(plugin.getConfig().getString("GUI.Colorize.Color").equalsIgnoreCase("orange")){
                            invcol.setItem(sl, i.goldp());
                        }
                        if(plugin.getConfig().getString("GUI.Colorize.Color").equalsIgnoreCase("gray")){
                            invcol.setItem(sl, i.grayp());
                        }
                        if(plugin.getConfig().getString("GUI.Colorize.Color").equalsIgnoreCase("darkgray")){
                            invcol.setItem(sl, i.darkgrayp());
                        }
                        if(plugin.getConfig().getString("GUI.Colorize.Color").equalsIgnoreCase("purple")){
                            invcol.setItem(sl, i.purplep());
                        }
                        if(plugin.getConfig().getString("GUI.Colorize.Color").equalsIgnoreCase("lime")){
                            invcol.setItem(sl, i.limep());
                        }
                        if(plugin.getConfig().getString("GUI.Colorize.Color").equalsIgnoreCase("aqua")){
                            invcol.setItem(sl, i.aquap());
                        }
                        if(plugin.getConfig().getString("GUI.Colorize.Color").equalsIgnoreCase("red")){
                            invcol.setItem(sl, i.redp());
                        }
                        if(plugin.getConfig().getString("GUI.Colorize.Color").equalsIgnoreCase("pink")){
                            invcol.setItem(sl, i.pinkp());
                        }
                        if(plugin.getConfig().getString("GUI.Colorize.Color").equalsIgnoreCase("white")){
                            invcol.setItem(sl, i.whitep());
                        }
                        if(plugin.getConfig().getString("GUI.Colorize.Color").equalsIgnoreCase("yellow")){
                            invcol.setItem(sl, i.yellowp());
                        }
                        if(plugin.getConfig().getString("GUI.Colorize.Color").equalsIgnoreCase("green")){
                            invcol.setItem(sl, i.greenp());
                        }
                        if(sl == plugin.getConfig().getInt("GUI.Colorize.Slot1") || sl == plugin.getConfig().getInt("GUI.Colorize.Slot2") || sl == plugin.getConfig().getInt("GUI.Colorize.Slot3")){
                            invcol.setItem(sl,  i.air());
                        }
                        if(sl == plugin.getConfig().getInt("GUI.Colorize.Changer")){
                            invcol.setItem(sl, i.waiting());
                        }
                    }
                    player.openInventory(invcol);
                    plugin.colorize(player);

                    event.setCancelled(true);
                    keys.removeAll(keys);
                    return;
                }else if(c.getData().getString(keys.get(r) + ".Type").equalsIgnoreCase("rename")){
                    Inventory invren = Bukkit.createInventory(null, 9, msgUtils.colorize(plugin.getConfig().getString("Messages.RenameTitle")));
                    for(int sl = 0; sl < plugin.getConfig().getInt("GUI.Rename.Size"); sl++){
                        if(plugin.getConfig().getString("GUI.Rename.Color").equalsIgnoreCase("black")){
                            invren.setItem(sl, i.blackp());
                        }
                        if(plugin.getConfig().getString("GUI.Rename.Color").equalsIgnoreCase("brown")){
                            invren.setItem(sl, i.brownp());
                        }
                        if(plugin.getConfig().getString("GUI.Rename.Color").equalsIgnoreCase("blue")){
                            invren.setItem(sl, i.bluep());
                        }
                        if(plugin.getConfig().getString("GUI.Rename.Color").equalsIgnoreCase("cyan")){
                            invren.setItem(sl, i.cyanp());
                        }
                        if(plugin.getConfig().getString("GUI.Rename.Color").equalsIgnoreCase("magenta")){
                            invren.setItem(sl, i.magentap());
                        }
                        if(plugin.getConfig().getString("GUI.Rename.Color").equalsIgnoreCase("orange")){
                            invren.setItem(sl, i.goldp());
                        }
                        if(plugin.getConfig().getString("GUI.Rename.Color").equalsIgnoreCase("gray")){
                            invren.setItem(sl, i.grayp());
                        }
                        if(plugin.getConfig().getString("GUI.Rename.Color").equalsIgnoreCase("darkgray")){
                            invren.setItem(sl, i.darkgrayp());
                        }
                        if(plugin.getConfig().getString("GUI.Rename.Color").equalsIgnoreCase("purple")){
                            invren.setItem(sl, i.purplep());
                        }
                        if(plugin.getConfig().getString("GUI.Rename.Color").equalsIgnoreCase("lime")){
                            invren.setItem(sl, i.limep());
                        }
                        if(plugin.getConfig().getString("GUI.Rename.Color").equalsIgnoreCase("aqua")){
                            invren.setItem(sl, i.aquap());
                        }
                        if(plugin.getConfig().getString("GUI.Rename.Color").equalsIgnoreCase("red")){
                            invren.setItem(sl, i.redp());
                        }
                        if(plugin.getConfig().getString("GUI.Rename.Color").equalsIgnoreCase("pink")){
                            invren.setItem(sl, i.pinkp());
                        }
                        if(plugin.getConfig().getString("GUI.Rename.Color").equalsIgnoreCase("white")){
                            invren.setItem(sl, i.whitep());
                        }
                        if(plugin.getConfig().getString("GUI.Rename.Color").equalsIgnoreCase("yellow")){
                            invren.setItem(sl, i.yellowp());
                        }
                        if(plugin.getConfig().getString("GUI.Rename.Color").equalsIgnoreCase("green")){
                            invren.setItem(sl, i.greenp());
                        }
                        if(sl == plugin.getConfig().getInt("GUI.Rename.Slot1") || sl == plugin.getConfig().getInt("GUI.Rename.Slot2") || sl == plugin.getConfig().getInt("GUI.Rename.Slot3")){
                            invren.setItem(sl,  i.air());
                        }
                        if(sl == plugin.getConfig().getInt("GUI.Rename.Changer")){
                            invren.setItem(sl, i.waiting());
                        }
                    }

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
