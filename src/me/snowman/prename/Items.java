package me.snowman.prename;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Items {
    private ItemRename plugin = ItemRename.getPlugin(ItemRename.class);
    public static Map<String, String> mat = new HashMap<>();

    // 1.8 - 1.13 compatibility
    public void matInit(){
        String[] tmp = Bukkit.getVersion().split("MC: ");
        String version = tmp[tmp.length - 1].substring(0, 4);
        if (version.equals("1.8.") || version.equals("1.9.") || version.equals("1.10") || version.equals("1.11") || version.equals("1.12")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "Found server version: " + ChatColor.WHITE + "1.8 - 1.12.2");
            mat.put("black", "INK_SACK");
            mat.put("red", "INK_SACK");
            mat.put("dgreen", "INK_SACK");
            mat.put("blue", "INK_SACK");
            mat.put("dpurple", "INK_SACK");
            mat.put("daqua", "INK_SACK");
            mat.put("gray", "INK_SACK");
            mat.put("dgray", "INK_SACK");
            mat.put("pink", "INK_SACK");
            mat.put("green", "INK_SACK");
            mat.put("yellow", "INK_SACK");
            mat.put("aqua", "INK_SACK");
            mat.put("gold", "INK_SACK");
            mat.put("white", "INK_SACK");
            mat.put("glowstone_dust", "GLOWSTONE_DUST");
            mat.put("sugar", "SUGAR");
            mat.put("iron_fence", "IRON_FENCE");
            mat.put("red_glass_pane", "STAINED_GLASS_PANE");
            mat.put("black_glass_pane", "STAINED_GLASS_PANE");
            mat.put("orange_glass_pane", "STAINED_GLASS_PANE");
            mat.put("yellow_glass_pane", "STAINED_GLASS_PANE");
            mat.put("green_glass_pane", "STAINED_GLASS_PANE");
            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "Successfully switched to materials from version: " + ChatColor.WHITE + "1.8 - 1.12.2");
        }else{
            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "Found server version: " + ChatColor.WHITE + "1.13");
            mat.put("black", "INK_SAC");
            mat.put("red", "ROSE_RED");
            mat.put("dgreen", "CACTUS_GREEN");
            mat.put("blue", "LAPIS_LAZULI");
            mat.put("dpurple", "PURPLE_DYE");
            mat.put("daqua", "CYAN_DYE");
            mat.put("gray", "LIGHT_GRAY_DYE");
            mat.put("dgray", "GRAY_DYE");
            mat.put("pink", "PINK_DYE");
            mat.put("green", "LIME_DYE");
            mat.put("yellow", "DANDELION_YELLOW");
            mat.put("aqua", "LIGHT_BLUE_DYE");
            mat.put("gold", "ORANGE_DYE");
            mat.put("white", "BONE_MEAL");
            mat.put("glowstone_dust", "GLOWSTONE_DUST");
            mat.put("sugar", "SUGAR");
            mat.put("iron_fence", "IRON_BARS");
            mat.put("red_glass_pane", "RED_STAINED_GLASS_PANE");
            mat.put("black_glass_pane", "BLACK_STAINED_GLASS_PANE");
            mat.put("orange_glass_pane", "ORANGE_STAINED_GLASS_PANE");
            mat.put("yellow_glass_pane", "YELLOW_STAINED_GLASS_PANE");
            mat.put("green_glass_pane", "LIME_STAINED_GLASS_PANE");
            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "Successfully switched to materials from version: " + ChatColor.WHITE + "1.13");
        }
    }

    List<String> lorerenametag = ItemRename.getPlugin(ItemRename.class).getConfig().getStringList("TagRenameLore");

    public List<String> getLorecolortag() {
        lorerenametag.replaceAll(string -> ChatColor.translateAlternateColorCodes('&', string));
        return lorecolortag;
    }

    List<String> lorecolortag = plugin.getConfig().getStringList("TagColorLore");

    public List<String> getLorerenametag(){
        lorecolortag.replaceAll(string -> ChatColor.translateAlternateColorCodes('&', string));
    return lorerenametag;
    }

    List<String> dyelore = plugin.getConfig().getStringList("DyeLore");

    public List<String> getDyelore() {
        dyelore.replaceAll(string -> ChatColor.translateAlternateColorCodes('&', string));
        return dyelore;
    }

    List<String> lockedlore = plugin.getConfig().getStringList("LockedLore");
    public List<String> getLockedlore() {
        lockedlore.replaceAll(string -> ChatColor.translateAlternateColorCodes('&', string));
        return lockedlore;
    }

        public ItemStack black () {
            ItemStack black = new ItemStack(Material.matchMaterial(mat.get("black")), 1, (byte) 0);
            ItemMeta bmeta = black.getItemMeta();
            bmeta.setLore(getDyelore());
            black.setItemMeta(bmeta);
            return black;
        }
        public ItemStack red(){
            ItemStack red = new ItemStack(Material.matchMaterial(mat.get("red")), 1, (byte) 1);
            ItemMeta rmeta = red.getItemMeta();
            rmeta.setLore(getDyelore());
            red.setItemMeta(rmeta);
            return red;
        }
        public ItemStack dgreen(){
            ItemStack dgreen = new ItemStack(Material.matchMaterial(mat.get("dgreen")), 1, (byte) 2);
            ItemMeta dgmeta = dgreen.getItemMeta();
            dgmeta.setLore(getDyelore());
            dgreen.setItemMeta(dgmeta);
            return dgreen;
        }
        public ItemStack blue(){
            ItemStack blue = new ItemStack(Material.matchMaterial(mat.get("blue")), 1, (byte) 4);
            ItemMeta blmeta = blue.getItemMeta();
            blmeta.setLore(getDyelore());
            blue.setItemMeta(blmeta);
            return blue;
        }
        public ItemStack dpurple(){
            ItemStack dpurple = new ItemStack(Material.matchMaterial(mat.get("dpurple")), 1, (byte) 5);
            ItemMeta dpmeta = dpurple.getItemMeta();
            dpmeta.setLore(getDyelore());
            dpurple.setItemMeta(dpmeta);
            return dpurple;
        }
        public ItemStack daqua(){
            ItemStack daqua = new ItemStack(Material.matchMaterial(mat.get("daqua")), 1, (byte) 6);
            ItemMeta dameta = daqua.getItemMeta();
            dameta.setLore(getDyelore());
            daqua.setItemMeta(dameta);
            return daqua;
        }
        public ItemStack gray(){
            ItemStack gray = new ItemStack(Material.matchMaterial(mat.get("gray")), 1, (byte) 7);
            ItemMeta gmeta = gray.getItemMeta();
            gmeta.setLore(getDyelore());
            gray.setItemMeta(gmeta);
            return gray;
        }
        public ItemStack dgray(){
            ItemStack dgray = new ItemStack(Material.matchMaterial(mat.get("dgray")), 1, (byte) 8);
            ItemMeta dgrmeta = dgray.getItemMeta();
            dgrmeta.setLore(getDyelore());
            dgray.setItemMeta(dgrmeta);
            return dgray;
        }
        public ItemStack lpurple(){
            ItemStack lpurple = new ItemStack(Material.matchMaterial(mat.get("pink")), 1, (byte) 9);
            ItemMeta lpmeta = lpurple.getItemMeta();
            lpmeta.setLore(getDyelore());
            lpurple.setItemMeta(lpmeta);
            return lpurple;
        }
        public ItemStack green(){
            ItemStack green = new ItemStack(Material.matchMaterial(mat.get("green")), 1, (byte) 10);
            ItemMeta grmeta = green.getItemMeta();
            grmeta.setLore(getDyelore());
            green.setItemMeta(grmeta);
            return green;
        }
        public ItemStack yellow(){
            ItemStack yellow = new ItemStack(Material.matchMaterial(mat.get("yellow")), 1, (byte) 11);
            ItemMeta ymeta = yellow.getItemMeta();
            ymeta.setLore(getDyelore());
            yellow.setItemMeta(ymeta);
            return yellow;
        }
        public ItemStack aqua(){
            ItemStack aqua = new ItemStack(Material.matchMaterial(mat.get("aqua")), 1, (byte) 12);
            ItemMeta ameta = aqua.getItemMeta();
            ameta.setLore(getDyelore());
            aqua.setItemMeta(ameta);
            return aqua;
        }
        public ItemStack gold(){
            ItemStack gold = new ItemStack(Material.matchMaterial(mat.get("gold")), 1, (byte) 14);
            ItemMeta gometa = gold.getItemMeta();
            gometa.setLore(getDyelore());
            gold.setItemMeta(gometa);
            return gold;
        }
        public ItemStack white(){
            ItemStack white = new ItemStack(Material.matchMaterial(mat.get("white")), 1, (byte) 15);
            ItemMeta whmeta = white.getItemMeta();
            whmeta.setLore(getDyelore());
            white.setItemMeta(whmeta);
            return white;
        }
        public ItemStack bold(){
            ItemStack bold = new ItemStack(Material.matchMaterial(mat.get("glowstone_dust")), 1);
            ItemMeta bldmeta = bold.getItemMeta();
            bldmeta.setLore(getDyelore());
            bold.setItemMeta(bldmeta);
            return bold;
        }
        public ItemStack italic(){
            ItemStack italic = new ItemStack(Material.matchMaterial(mat.get("sugar")), 1);
            ItemMeta imeta = italic.getItemMeta();
            imeta.setLore(getDyelore());
            italic.setItemMeta(imeta);
            return italic;
        }
        public ItemStack locked(){
            ItemStack locked = new ItemStack(Material.matchMaterial(mat.get("iron_fence")), 1);
            ItemMeta lmeta = locked.getItemMeta();
            lmeta.setLore(getDyelore());
            locked.setItemMeta(lmeta);
            return locked;
        }
        public ItemStack error(){
            ItemStack error = new ItemStack(Material.matchMaterial(mat.get("red_glass_pane")), 1, (byte) 14);
            ItemMeta emeta = error.getItemMeta();
            emeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.Error")));
            error.setItemMeta(emeta);
            return error;
        }
        public ItemStack nomoneyr(){
            ItemStack nomoneyr = new ItemStack(Material.matchMaterial(mat.get("orange_glass_pane")), 1, (byte) 1);
            ItemMeta normmeta = nomoneyr.getItemMeta();
            normmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.NoMoney")).replace("%required%", plugin.getConfig().getString("RenameCost")));
            nomoneyr.setItemMeta(normmeta);
            return nomoneyr;
        }
        public ItemStack nomoneyc(){
            ItemStack nomoneyc = new ItemStack(Material.matchMaterial(mat.get("orange_glass_pane")), 1, (byte) 1);
            ItemMeta nocmmeta = nomoneyc.getItemMeta();
            nocmmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.NoMoney")).replace("%required%", plugin.getConfig().getString("ColorizeCost")));
            nomoneyc.setItemMeta(nocmmeta);
            return nomoneyc;
        }
        public ItemStack waiting(){
            ItemStack waiting = new ItemStack(Material.matchMaterial(mat.get("yellow_glass_pane")), 1, (byte) 4);
            ItemMeta wmeta = waiting.getItemMeta();
            wmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.Waiting")));
            waiting.setItemMeta(wmeta);
            return waiting;
        }
        public ItemStack readyr(){
            ItemStack readyr = new ItemStack(Material.matchMaterial(mat.get("green_glass_pane")), 1, (byte) 13);
            ItemMeta rrmeta = readyr.getItemMeta();
            rrmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.ReadyRename")));
            ArrayList<String> lore = new ArrayList<>();
            lore.add(" ");
            lore.add(ChatColor.BLUE + "" + ChatColor.BOLD + "CLICK");
            rrmeta.setLore(lore);
            readyr.setItemMeta(rrmeta);
            return readyr;
        }
        public ItemStack readyc(){
            ItemStack readyc = new ItemStack(Material.matchMaterial(mat.get("green_glass_pane")), 1, (byte) 13);
            ItemMeta rcmeta = readyc.getItemMeta();
            rcmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.ReadyColor")));
            ArrayList<String> lore = new ArrayList<>();
            lore.add(" ");
            lore.add(ChatColor.BLUE + "" + ChatColor.BOLD + "CLICK");
            rcmeta.setLore(lore);
            readyc.setItemMeta(rcmeta);
            return readyc;
        }
        public ItemStack empty(){
        ItemStack empty = new ItemStack(Material.matchMaterial(mat.get("black_glass_pane")), 1, (byte) 15);
        return empty;
    }
}
