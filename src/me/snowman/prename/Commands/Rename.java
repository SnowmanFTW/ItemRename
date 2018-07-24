package me.snowman.prename.Commands;

import me.snowman.prename.ItemRename;
import me.snowman.prename.Items;
import me.snowman.prename.Utils.MessageUtils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Rename implements CommandExecutor {
    private ItemRename plugin = ItemRename.getPlugin(ItemRename.class);
    private static Economy econ = null;
    private Items i = new Items();
    private MessageUtils msgUtils = new MessageUtils();

    public boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        Inventory invren = Bukkit.createInventory(null, 9, msgUtils.colorize(plugin.getConfig().getString("Messages.RenameTitle")));
        Inventory invcol = Bukkit.createInventory(null, 9, msgUtils.colorize(plugin.getConfig().getString("Messages.ColorTitle")));

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
                invren.setItem(4, i.waiting());
                invren.setItem(0, i.empty());
                invren.setItem(2, i.empty());
                invren.setItem(6, i.empty());
                invren.setItem(5, i.empty());
                invren.setItem(8, i.empty());

                player.openInventory(invren);
                plugin.rename(player);
                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("colorize")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                invcol.setItem(4, i.waiting());
                invcol.setItem(0, i.empty());
                invcol.setItem(2, i.empty());
                invcol.setItem(6, i.empty());
                invcol.setItem(5, i.empty());
                invcol.setItem(8, i.empty());

                player.openInventory(invcol);
                plugin.colorize(player);
            }
            return true;
        }

        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(msgUtils.colorize("&9&lItem rename commands\n&9/ir help &7- &fShows this\n&9/ir rename &7- &fRename an item using a colored tag\n&9/ir colorize &7- &fColor a tag using dyes\n&9/ir tag [name] &7- &fGet a custom name tag\n&9/ir lockedtag [name] &7- &fGet a custom locked name tag\n&9/ir dye [dye-name/all] &7- &fGet a custom dye"));
            return true;
        }
        if (args[0].equalsIgnoreCase("rename")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                ItemStack glassu = new ItemStack(Material.matchMaterial(i.mat.get("glass_pane")), 1, (byte) 15);
                invren.setItem(4, i.waiting());
                invren.setItem(0, glassu);
                invren.setItem(2, glassu);
                invren.setItem(6, glassu);
                invren.setItem(5, glassu);
                invren.setItem(8, glassu);

                player.openInventory(invren);
                plugin.rename(player);
                return true;
            }
        }
        if (args[0].equalsIgnoreCase("colorize")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                ItemStack glassu = new ItemStack(Material.matchMaterial(i.mat.get("glass_pane")), 1, (byte) 15);
                invcol.setItem(4, i.waiting());
                invcol.setItem(0, glassu);
                invcol.setItem(2, glassu);
                invcol.setItem(6, glassu);
                invcol.setItem(5, glassu);
                invcol.setItem(8, glassu);

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
                    if (player.hasPermission("itemrename.dye")) {
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
            if (player.hasPermission("itemrename.tag")) {
                if (args.length >= 2) {
                    String name = "";
                    for (int i = 1; i < args.length; i++) {
                        //noinspection StringConcatenationInLoop
                        name = name + args[i] + " ";
                    }
                    ItemStack tag = new ItemStack(Material.NAME_TAG, 1);
                    ItemMeta tagmeta = tag.getItemMeta();
                    lorecolortag.replaceAll(string -> msgUtils.colorize(string));
                    tagmeta.setLore(lorecolortag);
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
            if (player.hasPermission("itemrename.tag")) {
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


        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("itemrename.reload")) {
                i.matInit();
                plugin.reloadConfig();
                sender.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.Reload")));
                return true;
            } else {
                sender.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.NoPerm")));
            }
        }
        return true;
    }
}

