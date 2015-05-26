package net.samongi.LoreEnchantments;

import java.util.logging.Logger;

import net.samongi.LoreEnchantments.EventHandling.EnchantmentHandler;
import net.samongi.LoreEnchantments.EventHandling.Handlers.BlockListener;
import net.samongi.LoreEnchantments.Interfaces.OnBlockBreak;
import net.samongi.LoreEnchantments.Interfaces.OnBlockDamage;
import net.samongi.LoreEnchantments.Interfaces.OnItemBreak;
import net.samongi.LoreEnchantments.Interfaces.OnItemDamage;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerDamageEntity;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LoreEnchantments extends JavaPlugin
{
  static private boolean debug = false;
  static private Logger logger = Logger.getLogger("minecraft");
  
  
  public void onEnable()
  {
    // config handling.
    this.getConfig().options().copyDefaults(true);
    this.saveConfig();
    debug = this.getConfig().getBoolean("debug"); // Grabbing the debug state.
    
    // Enchantment Handler
    EnchantmentHandler handler = new EnchantmentHandler(this);
    // registering all the interfaces we will be 
    handler.registerInterface(OnBlockBreak.class);
    handler.registerInterface(OnBlockDamage.class);
    handler.registerInterface(OnItemBreak.class);
    handler.registerInterface(OnItemDamage.class);
    handler.registerInterface(OnPlayerDamageEntity.class);
    
    // Listener Registering
    PluginManager pm = this.getServer().getPluginManager();
    pm.registerEvents(new BlockListener(handler), this);
  }
  
  public void onDisable()
  {
    
  }
  
  static public void log(String to_log)
  {
    logger.info(to_log);
  }
  static public void debugLog(String to_log)
  {
    if(debug == true) logger.info(to_log);
  }
  static public boolean debug(){return debug;}
}
