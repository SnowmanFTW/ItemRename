package me.snowman.prename.Utils;


import me.snowman.prename.ItemRename;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Items {
    private ItemRename plugin = ItemRename.getPlugin(ItemRename.class);
    public static Map<String, String> mat = new HashMap<>();
    private MessageUtils msgUtils = new MessageUtils();

    // 1.8 - 1.13 compatibility here, I hate it so much
    public void matInit() {
        String[] tmp = Bukkit.getVersion().split("MC: ");
        String version = tmp[tmp.length - 1].substring(0, 4);
        if (version.equals("1.8.") || version.equals("1.9.") || version.equals("1.10") || version.equals("1.11") || version.equals("1.12")) {
            Bukkit.getConsoleSender().sendMessage(msgUtils.colorize("&9Found server version: &f1.8 - 1.12.2"));
            mat.put("black", "INK_SACK");
            mat.put("blue", "INK_SACK");
            mat.put("green", "INK_SACK");
            mat.put("cyan", "INK_SACK");
            mat.put("red", "NETHER_STALK");
            mat.put("magenta", "INK_SACK");
            mat.put("gold", "INK_SACK");
            mat.put("gray", "INK_SACK");
            mat.put("darkgray", "INK_SACK");
            mat.put("purple", "INK_SACK");
            mat.put("lime", "INK_SACK");
            mat.put("aqua", "INK_SACK");
            mat.put("lightred", "INK_SACK");
            mat.put("pink", "INK_SACK");
            mat.put("yellow", "INK_SACK");
            mat.put("white", "INK_SACK");
            mat.put("bold", "GLOWSTONE_DUST");
            mat.put("italic", "SUGAR");
            mat.put("locked", "IRON_FENCE");
            mat.put("red_glass_pane", "STAINED_GLASS_PANE");
            mat.put("black_glass_pane", "STAINED_GLASS_PANE");
            mat.put("orange_glass_pane", "STAINED_GLASS_PANE");
            mat.put("yellow_glass_pane", "STAINED_GLASS_PANE");
            mat.put("green_glass_pane", "STAINED_GLASS_PANE");
            Bukkit.getConsoleSender().sendMessage(msgUtils.colorize("&9Successfully switched to materials from version: &f1.8 - 1.12.2"));
        } else {
            Bukkit.getConsoleSender().sendMessage(msgUtils.colorize("&9Found server version: &f1.13"));
            mat.put("black", "INK_SAC");
            mat.put("blue", "LAPIS_LAZULI");
            mat.put("green", "CACTUS_GREEN");
            mat.put("cyan", "CYAN_DYE");
            mat.put("red", "NETHER_WART");
            mat.put("magenta", "MAGENTA_DYE");
            mat.put("gold", "ORANGE_DYE");
            mat.put("gray", "LIGHT_GRAY_DYE");
            mat.put("darkgray", "GRAY_DYE");
            mat.put("purple", "PURPLE_DYE");
            mat.put("lime", "LIME_DYE");
            mat.put("aqua", "LIGHT_BLUE_DYE");
            mat.put("lightred", "ROSE_RED");
            mat.put("pink", "PINK_DYE");
            mat.put("yellow", "DANDELION_YELLOW");
            mat.put("white", "BONE_MEAL");
            mat.put("bold", "GLOWSTONE_DUST");
            mat.put("italic", "SUGAR");
            mat.put("locked", "IRON_BARS");
            mat.put("red_glass_pane", "RED_STAINED_GLASS_PANE");
            mat.put("black_glass_pane", "BLACK_STAINED_GLASS_PANE");
            mat.put("orange_glass_pane", "ORANGE_STAINED_GLASS_PANE");
            mat.put("yellow_glass_pane", "YELLOW_STAINED_GLASS_PANE");
            mat.put("green_glass_pane", "LIME_STAINED_GLASS_PANE");
            Bukkit.getConsoleSender().sendMessage(msgUtils.colorize("&9Successfully switched to materials from version: &f1.13"));
        }
    }

    List<String> lorerenametag = ItemRename.getPlugin(ItemRename.class).getConfig().getStringList("TagRenameLore");

    public List<String> getLorecolortag() {
        lorerenametag.replaceAll(string -> msgUtils.colorize(string));
        return lorecolortag;
    }

    List<String> lorecolortag = plugin.getConfig().getStringList("TagColorLore");

    public List<String> getLorerenametag() {
        lorecolortag.replaceAll(string -> msgUtils.colorize(string));
        return lorerenametag;
    }

    List<String> dyelore = plugin.getConfig().getStringList("DyeLore");

    public List<String> getDyelore() {
        dyelore.replaceAll(string -> msgUtils.colorize(string));
        return dyelore;
    }

    List<String> lockedlore = plugin.getConfig().getStringList("LockedLore");

    public List<String> getLockedlore() {
        lockedlore.replaceAll(string -> msgUtils.colorize(string));
        return lockedlore;
    }

    //Items Generated via Plug
    public ItemStack black(int amount) {
        ItemStack black = new ItemStack(Material.matchMaterial(mat.get("black")), amount, (byte) 0);
        ItemMeta bmeta = black.getItemMeta();
        bmeta.setLore(getDyelore());
        black.setItemMeta(bmeta);
        return black;
    }

    public ItemStack darkblue(int amount) {
        ItemStack blue = new ItemStack(Material.matchMaterial(mat.get("blue")), amount, (byte) 4);
        ItemMeta blmeta = blue.getItemMeta();
        blmeta.setLore(getDyelore());
        blue.setItemMeta(blmeta);
        return blue;
    }

    public ItemStack green(int amount) {
        ItemStack green = new ItemStack(Material.matchMaterial(mat.get("green")), amount, (byte) 2);
        ItemMeta dgmeta = green.getItemMeta();
        dgmeta.setLore(getDyelore());
        green.setItemMeta(dgmeta);
        return green;
    }

    public ItemStack cyan(int amount) {
        ItemStack aqua = new ItemStack(Material.matchMaterial(mat.get("cyan")), amount, (byte) 6);
        ItemMeta dameta = aqua.getItemMeta();
        dameta.setLore(getDyelore());
        aqua.setItemMeta(dameta);
        return aqua;
    }

    public ItemStack darkred(int amount) {
        ItemStack red = new ItemStack(Material.matchMaterial(mat.get("red")), amount);
        ItemMeta rmeta = red.getItemMeta();
        rmeta.setLore(getDyelore());
        red.setItemMeta(rmeta);
        return red;
    }

    public ItemStack magenta(int amount) {
        ItemStack magenta = new ItemStack(Material.matchMaterial(mat.get("magenta")), amount, (byte) 13);
        ItemMeta dpmeta = magenta.getItemMeta();
        dpmeta.setLore(getDyelore());
        magenta.setItemMeta(dpmeta);
        return magenta;
    }

    public ItemStack orange(int amount) {
        ItemStack gold = new ItemStack(Material.matchMaterial(mat.get("gold")), amount, (byte) 14);
        ItemMeta gometa = gold.getItemMeta();
        gometa.setLore(getDyelore());
        gold.setItemMeta(gometa);
        return gold;
    }

    public ItemStack gray(int amount) {
        ItemStack gray = new ItemStack(Material.matchMaterial(mat.get("gray")), amount, (byte) 7);
        ItemMeta gmeta = gray.getItemMeta();
        gmeta.setLore(getDyelore());
        gray.setItemMeta(gmeta);
        return gray;
    }

    public ItemStack darkgray(int amount) {
        ItemStack darkgray = new ItemStack(Material.matchMaterial(mat.get("darkgray")), amount, (byte) 8);
        ItemMeta dgrmeta = darkgray.getItemMeta();
        dgrmeta.setLore(getDyelore());
        darkgray.setItemMeta(dgrmeta);
        return darkgray;
    }

    public ItemStack blue(int amount) {
        ItemStack purple = new ItemStack(Material.matchMaterial(mat.get("purple")), amount, (byte) 5);
        ItemMeta ameta = purple.getItemMeta();
        ameta.setLore(getDyelore());
        purple.setItemMeta(ameta);
        return purple;
    }

    public ItemStack lime(int amount) {
        ItemStack lime = new ItemStack(Material.matchMaterial(mat.get("lime")), amount, (byte) 10);
        ItemMeta grmeta = lime.getItemMeta();
        grmeta.setLore(getDyelore());
        lime.setItemMeta(grmeta);
        return lime;
    }

    public ItemStack aqua(int amount) {
        ItemStack lightblue = new ItemStack(Material.matchMaterial(mat.get("aqua")), amount, (byte) 12);
        ItemMeta ameta = lightblue.getItemMeta();
        ameta.setLore(getDyelore());
        lightblue.setItemMeta(ameta);
        return lightblue;
    }

    public ItemStack lightred(int amount) {
        ItemStack lightred = new ItemStack(Material.matchMaterial(mat.get("lightred")), amount, (byte) 1);
        ItemMeta ameta = lightred.getItemMeta();
        ameta.setLore(getDyelore());
        lightred.setItemMeta(ameta);
        return lightred;
    }

    public ItemStack pink(int amount) {
        ItemStack pink = new ItemStack(Material.matchMaterial(mat.get("pink")), amount, (byte) 9);
        ItemMeta lpmeta = pink.getItemMeta();
        lpmeta.setLore(getDyelore());
        pink.setItemMeta(lpmeta);
        return pink;
    }

    public ItemStack yellow(int amount) {
        ItemStack yellow = new ItemStack(Material.matchMaterial(mat.get("yellow")), amount, (byte) 11);
        ItemMeta ymeta = yellow.getItemMeta();
        ymeta.setLore(getDyelore());
        yellow.setItemMeta(ymeta);
        return yellow;
    }

    public ItemStack white(int amount) {
        ItemStack white = new ItemStack(Material.matchMaterial(mat.get("white")), amount, (byte) 15);
        ItemMeta whmeta = white.getItemMeta();
        whmeta.setLore(getDyelore());
        white.setItemMeta(whmeta);
        return white;
    }

    public ItemStack bold(int amount) {
        ItemStack bold = new ItemStack(Material.matchMaterial(mat.get("bold")), amount);
        ItemMeta bldmeta = bold.getItemMeta();
        bldmeta.setLore(getDyelore());
        bold.setItemMeta(bldmeta);
        return bold;
    }

    public ItemStack italic(int amount) {
        ItemStack italic = new ItemStack(Material.matchMaterial(mat.get("italic")), amount);
        ItemMeta imeta = italic.getItemMeta();
        imeta.setLore(getDyelore());
        italic.setItemMeta(imeta);
        return italic;
    }

    public ItemStack locked(int amount) {
        ItemStack locked = new ItemStack(Material.matchMaterial(mat.get("locked")), amount);
        ItemMeta lmeta = locked.getItemMeta();
        lmeta.setLore(getDyelore());
        locked.setItemMeta(lmeta);
        return locked;
    }

    public ItemStack error() {
        ItemStack error = new ItemStack(Material.matchMaterial(mat.get("red_glass_pane")), 1, (byte) 14);
        ItemMeta emeta = error.getItemMeta();
        emeta.setDisplayName(msgUtils.colorize(plugin.getConfig().getString("Messages.Error")));
        error.setItemMeta(emeta);
        return error;
    }

    public ItemStack nomoneyr() {
        ItemStack nomoneyr = new ItemStack(Material.matchMaterial(mat.get("orange_glass_pane")), 1, (byte) 1);
        ItemMeta normmeta = nomoneyr.getItemMeta();
        normmeta.setDisplayName(msgUtils.colorize(plugin.getConfig().getString("Messages.NoMoney")).replace("%required%", plugin.getConfig().getString("RenameCost")));
        nomoneyr.setItemMeta(normmeta);
        return nomoneyr;
    }

    public ItemStack nomoneyc() {
        ItemStack nomoneyc = new ItemStack(Material.matchMaterial(mat.get("orange_glass_pane")), 1, (byte) 1);
        ItemMeta nocmmeta = nomoneyc.getItemMeta();
        nocmmeta.setDisplayName(msgUtils.colorize(plugin.getConfig().getString("Messages.NoMoney")).replace("%required%", plugin.getConfig().getString("ColorizeCost")));
        nomoneyc.setItemMeta(nocmmeta);
        return nomoneyc;
    }

    public ItemStack waiting() {
        ItemStack waiting = new ItemStack(Material.matchMaterial(mat.get("yellow_glass_pane")), 1, (byte) 4);
        ItemMeta wmeta = waiting.getItemMeta();
        wmeta.setDisplayName(msgUtils.colorize(plugin.getConfig().getString("Messages.Waiting")));
        waiting.setItemMeta(wmeta);
        return waiting;
    }

    public ItemStack readyr() {
        ItemStack readyr = new ItemStack(Material.matchMaterial(mat.get("green_glass_pane")), 1, (byte) 13);
        ItemMeta rrmeta = readyr.getItemMeta();
        rrmeta.setDisplayName(msgUtils.colorize(plugin.getConfig().getString("Messages.ReadyRename")));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(msgUtils.colorize("&1&lCLICK"));
        rrmeta.setLore(lore);
        readyr.setItemMeta(rrmeta);
        return readyr;
    }

    public ItemStack readyc() {
        ItemStack readyc = new ItemStack(Material.matchMaterial(mat.get("green_glass_pane")), 1, (byte) 13);
        ItemMeta rcmeta = readyc.getItemMeta();
        rcmeta.setDisplayName(msgUtils.colorize(plugin.getConfig().getString("Messages.ReadyColor")));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(msgUtils.colorize("&1&lCLICK"));
        rcmeta.setLore(lore);
        readyc.setItemMeta(rcmeta);
        return readyc;
    }

    public ItemStack empty() {
        ItemStack empty = new ItemStack(Material.matchMaterial(mat.get("black_glass_pane")), 1, (byte) 15);
        return empty;
    }

    public ItemStack dyeColor(String color, int amount) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Items getItem = new Items();
        Method method = Items.class.getMethod(color, int.class);
        ItemStack output = (ItemStack) method.invoke(getItem, amount);
        return output;

    }
}