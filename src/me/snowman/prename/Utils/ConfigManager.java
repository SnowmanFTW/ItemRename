package me.snowman.prename.Utils;

import me.snowman.prename.ItemRename;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private static ItemRename plugin = ItemRename.getPlugin(ItemRename.class);

    public FileConfiguration datacfg;
    public File datafile;

    public void setupData() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        datafile = new File(plugin.getDataFolder(), "data.yml");
        if (!datafile.exists()) {
            try {
                datafile.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "The data.yml file has been created");
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not create the data.yml file");
            }
        }
        datacfg = YamlConfiguration.loadConfiguration(datafile);
    }

    public FileConfiguration getData() {
        if(datafile == null || datacfg == null) {
            datafile = new File(plugin.getDataFolder(), "data.yml");
            datacfg = YamlConfiguration.loadConfiguration(datafile);
        }
        return datacfg;
    }
    public void saveData(){
        try{
            datacfg.save(datafile);
            Bukkit.getConsoleSender().sendMessage("salvat");
        }catch(IOException e){
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "'data.yml' could not be saved.");
        }
    }
    public void reloadData(){
        datafile = new File(plugin.getDataFolder(), "data.yml");
        datacfg = YamlConfiguration.loadConfiguration(datafile);
    }
}
