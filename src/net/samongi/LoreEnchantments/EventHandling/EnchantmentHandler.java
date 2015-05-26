package net.samongi.LoreEnchantments.EventHandling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class EnchantmentHandler
{
  private final JavaPlugin plugin;
  
  private final Map<Class<?>, List<LoreEnchantment>> enchantments = new HashMap<>();
  
  public EnchantmentHandler(JavaPlugin plugin)
  {
    this.plugin = plugin;
  }
  
  public JavaPlugin getPlugin(){return this.plugin;}
  
  /**Registers an Interface that enchantments implement.
   * 
   * @param c The interface to register
   */
  public void registerInterface(Class<?> c)
  { 
    // merely creates a new list
    enchantments.put(c, new ArrayList<LoreEnchantment>());
  }
  
  /**Registers the lore enchantment
   * Checks to see which interfaces the enchantment has implemented and
   * adds it to each of the interfaces' lists.
   * 
   * @param ench The enchantment to register.
   */
  public void registerEnchantment(LoreEnchantment ench)
  {
    for(Class<?> c : enchantments.keySet())
    {
      // Check to see if the enchantment is an instance of the class
      // if it is, add it to that class (interface)'s list.
      if(c.isInstance(ench)) enchantments.get(c).add(ench); 
    }
  }
  
  /**Returns a list of the lorenchants that need to handled by the interface
   * 
   * @param interfaze The interface to get the enchantments for
   * @return The list of enchantments that share the interfaces
   */
  public List<LoreEnchantment> getEnchantments(Class<?> interfaze)
  {
    return enchantments.get(interfaze);
  }
  
  /**Gets a list of enchantments based on the interface and if they are represented with the item.
   * 
   * @param interfaze
   * @param item
   * @return The list of enchantments that are on the item and share the interface. List is empty if none found.
   */
  public List<LoreEnchantment> getEnchantments(Class<?> interfaze, ItemStack item)
  {
    // Getting the item's lore
    List<String> lore = item.getItemMeta().getLore();
    if(lore == null) return new ArrayList<LoreEnchantment>();
    if(lore.size() == 0) return new ArrayList<LoreEnchantment>();
    List<LoreEnchantment> ret_enchs = new ArrayList<LoreEnchantment>();
    List<LoreEnchantment> enchs = this.getEnchantments(interfaze);
    for(LoreEnchantment e : enchs)
    {
      // Strips the color and lowercases the head.
      String expected_head = ChatColor.stripColor(e.getName()).toLowerCase();
      for(String line : lore)
      {
        if(ChatColor.stripColor(line).toLowerCase().startsWith(expected_head))
        {
          ret_enchs.add(e);
          break; // Continue on to testing the next enchantment
        }  
      }
    }
    // return the found enchantments on the item.
    return ret_enchs;
  }
}
