package net.samongi.LoreEnchantments;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.plugin.java.JavaPlugin;

import net.samongi.LoreEnchantments.EventHandling.EnchantmentHandler;
import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

public class LoreEnchantmentsAPI
{
  private final Set<JavaPlugin> requesting_plugins = new HashSet<JavaPlugin>();
  private EnchantmentHandler handler;
  
  protected LoreEnchantmentsAPI(EnchantmentHandler handler)
  {
    this.handler = handler;
  }
  public final void registerEnchantment(LoreEnchantment ench, JavaPlugin plugin)
  {
    LoreEnchantments.debugLog("'" + plugin.getName() + "' has registered the enchantment '" + ench.getName() + "'");
    handler.registerEnchantment(ench, plugin);
  }
  
  /**This adds the plugin to the list for tracking purposes.
   * 
   * @param plugin The plugin to track.
   */
  protected final void addPlugin(JavaPlugin plugin)
  {
    LoreEnchantments.debugLog("'" + plugin.getName() + "' accessed the API.");
    requesting_plugins.add(plugin);
  }
  /**Checks to see if the API was requested by another plugin.
   * 
   * @param plugin The plugin to check
   * @return true if the plugin was.
   */
  protected final boolean accessedByPlugin(JavaPlugin plugin){return requesting_plugins.contains(plugin);}
  
}
