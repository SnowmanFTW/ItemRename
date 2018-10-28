package me.snowman.prename.Commands;

import me.snowman.prename.ItemRename;
import me.snowman.prename.Utils.ConfigManager;
import me.snowman.prename.Utils.Items;
import me.snowman.prename.Utils.MessageUtils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Rename implements CommandExecutor {
    private ItemRename plugin = ItemRename.getPlugin(ItemRename.class);
    private static Economy econ = null;
    private Items i = new Items();
    private MessageUtils msgUtils = new MessageUtils();
    ConfigManager c = new ConfigManager();
    private ArrayList<String> keys = new ArrayList<>();


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {


        Inventory invren = Bukkit.createInventory(null, plugin.getConfig().getInt("GUI.Rename.Size"), msgUtils.colorize(plugin.getConfig().getString("Messages.RenameTitle")));
        Inventory invcol = Bukkit.createInventory(null, plugin.getConfig().getInt("GUI.Colorize.Size"), msgUtils.colorize(plugin.getConfig().getString("Messages.ColorTitle")));

        List<String> lorerenametag = plugin.getConfig().getStringList("TagRenameLore");
        lorerenametag.replaceAll(string -> msgUtils.colorize(string));

        List<String> lorecolortag = plugin.getConfig().getStringList("TagColorLore");
        lorecolortag.replaceAll(string -> msgUtils.colorize(string));

        List<String> dyelore = plugin.getConfig().getStringList("DyeLore");
        dyelore.replaceAll(string -> msgUtils.colorize(string));

        List<String> lockedlore = plugin.getConfig().getStringList("LockedLore");
        lockedlore.replaceAll(string -> msgUtils.colorize(string));

        if (cmd.getName().equalsIgnoreCase("rename")) {
            if (sender instanceof Player) {

                Player player = (Player) sender;
                if(!player.hasPermission("itemrename.bypass") || player.hasPermission("itemrename.*")){
                for(String world: plugin.getConfig().getStringList("DisabledWorlds")) {
                    if (player.getWorld().getName().equalsIgnoreCase(world)) {
                        player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DWorld")));
                        return true;
                    }
                }
                }
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
//                invren.setItem(4, i.waiting());
//                invren.setItem(0, i.empty());
//                invren.setItem(2, i.empty());
//                invren.setItem(6, i.empty());
//                invren.setItem(5, i.empty());
//                invren.setItem(8, i.empty());

                player.openInventory(invren);
                plugin.rename(player);
                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("colorize")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if(!player.hasPermission("itemrename.bypass") || player.hasPermission("itemrename.*")){
                    for(String world: plugin.getConfig().getStringList("DisabledWorlds")) {
                        if (player.getWorld().getName().equalsIgnoreCase(world)) {
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DWorld")));
                            return true;
                        }
                    }
                }
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
            }
            return true;
        }
        if(args.length == 0){
            sender.sendMessage(msgUtils.colorize("&9&lItem rename help 1\n&9/ir help &7- &fShows this\n&9/ir rename &7- &fRename an item using a colored tag\n&9/ir colorize &7- &fColor a tag using dyes\n&9/ir tag [name] &7- &fGet a custom name tag\n&9/ir lockedtag [name] &7- &fGet a custom locked name tag\n&9/ir dye [dye-name/all] &7- &fGet a custom dye\n \n&9Use &f/ir help 2 &9for page 2"));
            return true;
        }
        if(args[0].equalsIgnoreCase("help") && args.length == 1){
            sender.sendMessage(msgUtils.colorize("&9&lItem rename help 1\n&9/ir help &7- &fShows this\n&9/ir rename &7- &fRename an item using a colored tag\n&9/ir colorize &7- &fColor a tag using dyes\n&9/ir tag [name] &7- &fGet a custom name tag\n&9/ir lockedtag [name] &7- &fGet a custom locked name tag\n&9/ir dye [dye-name/all] &7- &fGet a custom dye\n \n&9Use &f/ir help 2 &9for page 2"));
            return true;
        }
        if(args.length == 2 && args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("2")){
            sender.sendMessage(msgUtils.colorize("&9&lItem rename help 2\n&9/ir setblock <name> <colorize/rename> &7- &fSet the block to open a menu\n&9/ir reload &7- &fReloads the config\n&9/ir update &7- &f Checks for updates."));
            return true;
        }
        if (args[0].equalsIgnoreCase("rename")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if(!player.hasPermission("itemrename.bypass") || player.hasPermission("itemrename.*")){
                    for(String world: plugin.getConfig().getStringList("DisabledWorlds")) {
                        if (player.getWorld().getName().equalsIgnoreCase(world)) {
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DWorld")));
                            return true;
                        }
                    }
                }
                for(int sl = 0; sl < plugin.getConfig().getInt("GUI.Rename.Size"); sl++){
                    if(plugin.getConfig().getString("GUI.Rename.Color").equalsIgnoreCase("black"))
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
                return true;
            }
        }
        if (args[0].equalsIgnoreCase("colorize")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if(!player.hasPermission("itemrename.bypass") || player.hasPermission("itemrename.*")){
                    for(String world: plugin.getConfig().getStringList("DisabledWorlds")) {
                        if (player.getWorld().getName().equalsIgnoreCase(world)) {
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DWorld")));
                            return true;
                        }
                    }
                }
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


            }
            return true;
        }

        if (args[0].equalsIgnoreCase("dye")) {

            if (args.length == 1) {
                sender.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.NoDye") + "\n&0BLACK&7, &1DARKBLUE&7, &2GREEN&7, &3CYAN&7, &4DARKRED&7, &5MAGENTA&7, &6ORANGE, &7GRAY&7, &8DARKGRAY&7, &9BLUE&7, &aLIME, &bAQUA&7, &cLIGHTRED&7, &dPINK&7, &eYELLOW&7, &fWHITE&7, &r&lBOLD&7, &r&oITALIC&7, &8LOCKED"));
            }

            if (args.length == 2) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(msgUtils.colorize("&1The console can't have dyes :("));
                } else {
                    Player player = (Player) sender;
                    if (player.hasPermission("itemrename.dye") || player.hasPermission("itemrename.*")) {
                        int amount = 1;
                        if (args[1].equalsIgnoreCase("all")) {
                            player.getInventory().addItem(i.black(amount));
                            player.getInventory().addItem(i.darkred(amount));
                            player.getInventory().addItem(i.orange(amount));
                            player.getInventory().addItem(i.yellow(amount));
                            player.getInventory().addItem(i.lime(amount));
                            player.getInventory().addItem(i.green(amount));
                            player.getInventory().addItem(i.cyan(amount));
                            player.getInventory().addItem(i.aqua(amount));
                            player.getInventory().addItem(i.lightred(amount));
                            player.getInventory().addItem(i.darkblue(amount));
                            player.getInventory().addItem(i.blue(amount));
                            player.getInventory().addItem(i.magenta(amount));
                            player.getInventory().addItem(i.pink(amount));
                            player.getInventory().addItem(i.white(amount));
                            player.getInventory().addItem(i.gray(amount));
                            player.getInventory().addItem(i.darkgray(amount));
                            player.getInventory().addItem(i.bold(amount));
                            player.getInventory().addItem(i.italic(amount));
                            player.getInventory().addItem(i.locked(amount));
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGiveAll")));
                        } else {
                            try {
                                player.getInventory().addItem(i.dyeColor(args[1], amount));
                                player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGive")).replace("%dye%", args[1]).replace("%amt%", "1"));
                            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                                sender.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.NoDye") + "\n&0BLACK&7, &1DARKBLUE&7, &2GREEN&7, &3CYAN&7, &4DARKRED&7, &5MAGENTA&7, &6ORANGE, &7GRAY&7, &8DARKGRAY&7, &9BLUE&7, &aLIME, &bAQUA&7, &cLIGHTRED&7, &dPINK&7, &eYELLOW&7, &fWHITE&7, &r&lBOLD&7, &r&oITALIC&7, &8LOCKED"));
                            }
                        }
                    } else {
                        sender.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.NoPerm")));

                    }
                }
            }
        }

        if (args[0].equalsIgnoreCase("tag")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(msgUtils.colorize("&1The console can't have tags :("));
                return true;
            }
            Player player = (Player) sender;
            if (player.hasPermission("itemrename.tag") || player.hasPermission("itemrename.*")) {
                if (args.length >= 2) {
                    String name = "";
                    for (int i = 1; i < args.length; i++) {
                        //noinspection StringConcatenationInLoop
                        name = name + args[i] + " ";
                    }
                    ItemStack tag = new ItemStack(Material.NAME_TAG, 1);
                    ItemMeta tagmeta = tag.getItemMeta();
                    lorecolortag.replaceAll(string -> msgUtils.colorize(string));
                    tagmeta.setLore(lorerenametag);
                    tagmeta.setDisplayName(msgUtils.colorize(name));
                    tag.setItemMeta(tagmeta);
                    player.getInventory().addItem(tag);
                    player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.TagGiveCustom") + " &f" + name));
                    return true;

                } else {

                    ItemStack tag = new ItemStack(Material.NAME_TAG, 1);
                    ItemMeta tagmeta = tag.getItemMeta();
                    lorecolortag.replaceAll(string -> msgUtils.colorize(string));
                    tagmeta.setLore(lorecolortag);
                    tagmeta.setDisplayName("Rename Tag");
                    tag.setItemMeta(tagmeta);
                    player.getInventory().addItem(tag);
                    player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.TagGive")));
                    return true;
                }
            } else {
                sender.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.NoPerm")));
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("lockedtag")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(msgUtils.colorize("&1The console can't have tags :("));
                return true;
            }
            Player player = (Player) sender;
            if (player.hasPermission("itemrename.tag") || player.hasPermission("itemrename.*")) {
                if (args.length >= 2) {
                    String name = "";
                    for (int i = 1; i < args.length; i++) {
                        //noinspection StringConcatenationInLoop
                        name = name + args[i] + " ";
                    }
                    ItemStack tag = new ItemStack(Material.NAME_TAG, 1);
                    ItemMeta tagmeta = tag.getItemMeta();
                    lorecolortag.replaceAll(string -> msgUtils.colorize(string));
                    tagmeta.setLore(lockedlore);
                    tagmeta.setDisplayName(msgUtils.colorize(name));
                    tag.setItemMeta(tagmeta);
                    player.getInventory().addItem(tag);
                    player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.TagGiveCustom") + " &f" + name));
                    return true;

                } else {

                    ItemStack tag = new ItemStack(Material.NAME_TAG, 1);
                    ItemMeta tagmeta = tag.getItemMeta();
                    lorecolortag.replaceAll(string -> msgUtils.colorize(string));
                    tagmeta.setLore(lockedlore);
                    tagmeta.setDisplayName("Rename Tag");
                    tag.setItemMeta(tagmeta);
                    player.getInventory().addItem(tag);
                    player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.TagGive")));
                    return true;
                }
            } else {
                sender.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.NoPerm")));
                return true;
            }
        }

        if(args[0].equalsIgnoreCase("setblock")){
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("itemrename.setblock") || player.hasPermission("itemrename.*")) {
                    if (args.length == 1 || args.length == 2) {
                        sender.sendMessage(msgUtils.colorize("&9Usage: &f/ir setblock <name> <rename/colorize>"));
                        return true;
                    }
                    c.reloadData();
                    for(String key : c.getData().getKeys(false)){
                        keys.add(key);
                    }
                    if (!keys.contains(args[1])) {
                        c.reloadData();
                        Block block = player.getTargetBlock(null, 5);
                        int r;
                        for(r = 0; r < c.getData().getKeys(false).size(); r++){
                            if(block.getWorld().getName() == Bukkit.getWorld(c.getData().getString(keys.get(r) + ".World")).getName() && block.getX() == Integer.valueOf(c.getData().getString(keys.get(r) + ".X")) && block.getY() == Integer.valueOf(c.getData().getString(keys.get(r) + ".Y")) && block.getZ() == Integer.valueOf(c.getData().getString(keys.get(r)+ ".Z"))){
                                player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.SameSetBlock")));
                                return true;
                            }
                        }
                        keys.removeAll(keys);
                        if (args[2].equalsIgnoreCase("colorize")) {
                            c.getData().set(args[1], "");
                            c.getData().set(args[1] + ".Type", "colorize");
                            c.getData().set(args[1] + ".World", block.getWorld().getName());
                            c.getData().set(args[1] + ".X", block.getX());
                            c.getData().set(args[1] + ".Y", block.getY());
                            c.getData().set(args[1] + ".Z", block.getZ());
                            c.saveData();
                            c.reloadData();
                            Location loc = block.getLocation();
                            loc.setX(loc.getX() + 0.5);
                            loc.setY(loc.getY() - 0.5);
                            loc.setZ(loc.getZ() + 0.5);
                            ArmorStand holo = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
                            holo.setGravity(false);
                            holo.setCanPickupItems(false);
                            holo.setCustomName(msgUtils.colorize(plugin.getConfig().getString("Messages.ColorizeHolo")));
                            holo.setCustomNameVisible(true);
                            holo.setVisible(false);
                            return true;
                        }
                        if (args[2].equalsIgnoreCase("rename")) {
                            c.getData().set(args[1], "");
                            c.getData().set(args[1] + ".Type", "rename");
                            c.getData().set(args[1] + ".World", block.getWorld().getName());
                            c.getData().set(args[1] + ".X", block.getX());
                            c.getData().set(args[1] + ".Y", block.getY());
                            c.getData().set(args[1] + ".Z", block.getZ());
                            c.saveData();
                            c.reloadData();
                            Location loc = block.getLocation();
                            loc.setX(loc.getX() + 0.5);
                            loc.setY(loc.getY() - 0.5);
                            loc.setZ(loc.getZ() + 0.5);
                            ArmorStand holo = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
                            holo.setGravity(false);
                            holo.setCanPickupItems(false);
                            holo.setCustomName(msgUtils.colorize(plugin.getConfig().getString("Messages.RenameHolo")));
                            holo.setCustomNameVisible(true);
                            holo.setVisible(false);
                            return true;
                        }
                    } else {
                        player.sendMessage(msgUtils.colorize("&cThat name is already taken."));
                        keys.removeAll(keys);
                    }
                }else{
                    player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.NoPerm")));
                }
            }
        }

        if (args[0].equalsIgnoreCase("update")) {
            if (sender.hasPermission("itemrename.update") || sender.hasPermission("itemrename.*")) {
                try {
                    String spigotId = "58756";
                    // Open connection with spigot's web API
                    HttpsURLConnection connection = (HttpsURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=" + spigotId).openConnection();

                    // Versions
                    String latest = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine().toLowerCase();
                    String installed = plugin.getDescription().getVersion().toLowerCase();

                    // Check if it's outdated or not
                    if (!installed.equals(latest)) {
                        sender.sendMessage(msgUtils.colorize("&9Your plugin version &f(&9" + installed + "&f) &9is not the latest one &f(&9" + latest + "&f)\n&9You can download it here: &fhttps://www.spigotmc.org/resources/item-entity-rename-1-8-1-13.58756/"));
                    } else {
                        sender.sendMessage(msgUtils.colorize("&9Plugin version is up-to-date &f(&9" + installed + "&f)&9."));
                    }
                } catch (Exception e) {
                    sender.sendMessage("&cThere was an error connecting to SpigotMC API.");
                }
            }else{
                sender.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.NoPerm")));
            }
        }


        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("itemrename.reload") || sender.hasPermission("itemrename.*")) {
                i.matInit();
                plugin.saveDefaultConfig();
                plugin.reloadConfig();
                c.setupData();
                c.reloadData();
                sender.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.Reload")));
                return true;
            } else {
                sender.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.NoPerm")));
            }
        }
        return true;
    }
}

