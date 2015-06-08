package net.samongi.LoreEnchantments;

import java.util.logging.Logger;

import net.samongi.LoreEnchantments.EventHandling.EnchantmentHandler;
import net.samongi.LoreEnchantments.EventHandling.Handlers.ArrowListener;
import net.samongi.LoreEnchantments.EventHandling.Handlers.BlockListener;
import net.samongi.LoreEnchantments.EventHandling.Handlers.EntityListener;
import net.samongi.LoreEnchantments.EventHandling.Handlers.ItemListener;
import net.samongi.LoreEnchantments.Interfaces.OnBlockArrowHit;
import net.samongi.LoreEnchantments.Interfaces.OnBlockBreak;
import net.samongi.LoreEnchantments.Interfaces.OnBlockDamage;
import net.samongi.LoreEnchantments.Interfaces.OnEntityArrowHitEntity;
import net.samongi.LoreEnchantments.Interfaces.OnEntityArrowHitPlayer;
import net.samongi.LoreEnchantments.Interfaces.OnEntityDamageEntity;
import net.samongi.LoreEnchantments.Interfaces.OnEntityShootBow;
import net.samongi.LoreEnchantments.Interfaces.OnItemBreak;
import net.samongi.LoreEnchantments.Interfaces.OnItemDamage;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerArrowHitEntity;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerArrowHitPlayer;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerDamageEntity;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerDamagePlayer;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerDropItem;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerInteract;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerInteractEntity;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerItemConsume;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerItemHeld;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerPickupItem;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerShootBow;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerSneak;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerSprint;
import net.samongi.LoreEnchantments.Interfaces.OnServerTick;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LoreEnchantments extends JavaPlugin
{
  static private boolean debug = false;
  static private Logger logger;
  
  private static EnchantmentHandler handler;
  private static LoreEnchantmentsAPI api;
  
  public void onEnable()
  {
    // Getting the plugin's logger
    logger = this.getLogger();
    
    // config handling.
    this.getConfig().options().copyDefaults(true);
    this.saveConfig();
    debug = this.getConfig().getBoolean("debug"); // Grabbing the debug state.
    
    // Enchantment Handler
    handler = new EnchantmentHandler(this);
    // registering all the interfaces we will be 
    handler.registerInterface(OnBlockArrowHit.class);
    handler.registerInterface(OnBlockBreak.class);
    handler.registerInterface(OnBlockDamage.class);
    handler.registerInterface(OnEntityArrowHitEntity.class);
    handler.registerInterface(OnEntityArrowHitPlayer.class);
    handler.registerInterface(OnEntityDamageEntity.class);
    handler.registerInterface(OnEntityShootBow.class);
    handler.registerInterface(OnItemBreak.class);
    handler.registerInterface(OnItemDamage.class);
    handler.registerInterface(OnPlayerArrowHitEntity.class);
    handler.registerInterface(OnPlayerArrowHitPlayer.class);
    handler.registerInterface(OnPlayerDamageEntity.class);
    handler.registerInterface(OnPlayerDamagePlayer.class);
    handler.registerInterface(OnPlayerDropItem.class);
    handler.registerInterface(OnPlayerInteract.class);
    handler.registerInterface(OnPlayerInteractEntity.class);
    handler.registerInterface(OnPlayerItemConsume.class);
    handler.registerInterface(OnPlayerItemHeld.class);
    handler.registerInterface(OnPlayerPickupItem.class);
    handler.registerInterface(OnPlayerShootBow.class);
    handler.registerInterface(OnPlayerSneak.class);
    handler.registerInterface(OnPlayerSprint.class);
    handler.registerInterface(OnServerTick.class);
    
    // Listener Registering
    PluginManager pm = this.getServer().getPluginManager();
    pm.registerEvents(new BlockListener(handler), this);
    pm.registerEvents(new ArrowListener(handler), this);
    pm.registerEvents(new EntityListener(handler), this);
    pm.registerEvents(new ItemListener(handler), this);
   
  }
  
  public void onDisable()
  {
    
  }
  
  static final public void log(String to_log)
  {
    logger.info(to_log);
  }
  static final public void debugLog(String to_log)
  {
    if(debug == true) logger.info(to_log);
  }
  static final public boolean debug(){return debug;}
  
  
  public final static LoreEnchantmentsAPI getAPI(JavaPlugin plugin)
  {
    if(api == null) api = new LoreEnchantmentsAPI(handler);
    if(!api.accessedByPlugin(plugin)) api.addPlugin(plugin);
    return api;
  }
}
