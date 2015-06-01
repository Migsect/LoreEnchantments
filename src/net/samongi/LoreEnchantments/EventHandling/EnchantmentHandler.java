package net.samongi.LoreEnchantments.EventHandling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.md_5.bungee.api.ChatColor;
import net.samongi.LoreEnchantments.LoreEnchantments;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class EnchantmentHandler
{
  private final JavaPlugin plugin;
  
  private final Map<Class<?>, List<LoreEnchantment>> enchantments = new HashMap<>();
  private final Map<LoreEnchantment, JavaPlugin> enchantment_ownership = new HashMap<>();
  private final Map<String, LoreEnchantment> owned_names = new HashMap<>();
  
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
    LoreEnchantments.debugLog("[HANDLER] Registered Interface '" + c.getName() + "'");
    enchantments.put(c, new ArrayList<LoreEnchantment>());
  }
  
  /**Registers the lore enchantment
   * Checks to see which interfaces the enchantment has implemented and
   * adds it to each of the interfaces' lists.
   * 
   * @param ench The enchantment to register.
   */
  public boolean registerEnchantment(LoreEnchantment ench, JavaPlugin j_plugin)
  {
    if(owned_names.keySet().contains(ench.getName())) return false; // Ducking out because it's already used.
    owned_names.put(ench.getName(), ench); // adding the name to the owned_names map.
    // Attributing ownership of the enchantment to a plugin in case something goes wrong.
    enchantment_ownership.put(ench, j_plugin);

    LoreEnchantments.debugLog("[HANDLER] Registerng Enchantment '" + ench.getName() + "'");
    
    // Add the enchantment to all the lists under the interfaces
    for(Class<?> c : enchantments.keySet())
    {
      // Check to see if the enchantment is an instance of the class
      // if it is, add it to that class (interface)'s list.
      if(c.isAssignableFrom(ench.getClass())) enchantments.get(c).add(ench); 
      if(c.isAssignableFrom(ench.getClass())) LoreEnchantments.debugLog("[HANDLER]   Found Interface for enchantment'" + c.getName() + "'");
    }
    return true;
  }
  /**Will attempt to deregister the name enchantment from the handler
   * If the enchantmant isn't actually found, nothing will come from it
   * 
   * @param ench The enchantment to register.
   */
  public void deregisterEnchantment(LoreEnchantment ench)
  {
    for(Class<?> c : enchantments.keySet()) enchantments.get(c).remove(ench);
    enchantment_ownership.remove(ench);
    owned_names.remove(ench.getName());
  }
  
  public boolean isRegistered(LoreEnchantment ench)
  {
    return owned_names.containsKey(ench.getName()) && enchantment_ownership.containsKey(ench);
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
  public List<EnchantmentPackage> getEnchantments(Class<?> interfaze, ItemStack item)
  {
    // Getting the item's lore
    if(item == null || item.getItemMeta() == null) return new ArrayList<EnchantmentPackage>();
    List<String> lore = item.getItemMeta().getLore();
    if(lore == null) return new ArrayList<EnchantmentPackage>();
    if(lore.size() == 0) return new ArrayList<EnchantmentPackage>();
    
    // The list of enchantments to return.
    List<EnchantmentPackage> ret_enchs = new ArrayList<EnchantmentPackage>();
    
    List<LoreEnchantment> enchs = this.getEnchantments(interfaze);
    for(String line : lore)
    {
      String edit_line = ChatColor.stripColor(line).toLowerCase();
      LoreEnchantments.debugLog("    Checking line for heads: '" + edit_line + "'");
      for(LoreEnchantment e : enchs)
      {
        String expected_head = ChatColor.stripColor(e.getName()).toLowerCase() + " ";
        LoreEnchantments.debugLog("Checking item '" + item.getItemMeta().getDisplayName() + "' for head '" + expected_head + "'");
        if(edit_line.startsWith(expected_head))
        {
          LoreEnchantments.debugLog("    Splitting headless line '" + edit_line.replace(ChatColor.stripColor(expected_head).toLowerCase(), "") + "'");
          String[] data = edit_line.replace(ChatColor.stripColor(expected_head).toLowerCase(), "").split("\\s+");
          EnchantmentPackage p = new EnchantmentPackage(e, data);
          ret_enchs.add(p);
        }
      }
    }
    /*
    List<LoreEnchantment> enchs = this.getEnchantments(interfaze);
    for(LoreEnchantment e : enchs)
    {
      // Strips the color and lowercases the head.
      String expected_head = ChatColor.stripColor(e.getName()).toLowerCase() + " ";
      LoreEnchantments.debugLog("Checking item '" + item.getItemMeta().getDisplayName() + "' for head '" + expected_head + "'");
      for(String line : lore)
      {
        String edit_line = ChatColor.stripColor(line).toLowerCase();
        LoreEnchantments.debugLog("    Checking line for head: '" + edit_line + "'");
        if(edit_line.startsWith(expected_head))
        {
          LoreEnchantments.debugLog("    Splitting headless line '" + edit_line.replace(ChatColor.stripColor(expected_head).toLowerCase(), "") + "'");
          String[] data = edit_line.replace(ChatColor.stripColor(expected_head).toLowerCase(), "").split("\\s+");
          EnchantmentPackage p = new EnchantmentPackage(e, data);
          ret_enchs.add(p);
        }  
      }
    }
    */
    // return the found enchantments on the item.
    return ret_enchs;
  }
  /**Used to transfer both the enchantment and the data associated.
   * 
   * @author Migsect
   *
   */
  public class EnchantmentPackage
  {
    private LoreEnchantment ench;
    private String[] data;
    
    public EnchantmentPackage(LoreEnchantment ench, String[] data)
    {
      this.ench = ench;
      this.data = data;
    }
    public LoreEnchantment getEnchantment(){return this.ench;}
    public String[] getData(){return this.data;}
  }
}
