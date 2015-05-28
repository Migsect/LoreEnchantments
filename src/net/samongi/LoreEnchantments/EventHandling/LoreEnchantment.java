package net.samongi.LoreEnchantments.EventHandling;

import java.util.List;

import net.md_5.bungee.api.ChatColor;
import net.samongi.LoreEnchantments.LoreEnchantments;

import org.bukkit.inventory.ItemStack;

public class LoreEnchantment
{
  private final String name;
  
  protected LoreEnchantment(String name)
  {
    this.name = name;
  }
  
  /**Gets the name of the enchantment
   * This is an unformatted string and should have correct casings
   * 
   * @return The name of the enchantments
   */
  public final String getName(){return this.name;}
  
  /**Will seperate the data from the item's lore and return it as an array.
   * 
   * @param item The item to seperate the data from
   * @return The enchantment data as a string array.  Null if item happened to not have the enchantment.
   */
  public final String[] seperateData(ItemStack item)
  {
    LoreEnchantments.debugLog("Seperating Data from '" + item.getItemMeta().getDisplayName() + "'");
    List<String> lore = item.getItemMeta().getLore();
    if(lore == null) LoreEnchantments.debugLog("  Lore Returned Null, ducking out!");
    if(lore == null) return null;
    if(lore.size() == 0) LoreEnchantments.debugLog("  Lore Returned Null, ducking out!");
    if(lore.size() == 0) return null;
    String expected_head = this.getName().toLowerCase() + " ";
    LoreEnchantments.debugLog("  Expecting Head: '" + expected_head + "'");
    for(String line : lore)
    {
      String edit_line = ChatColor.stripColor(line).toLowerCase();
      LoreEnchantments.debugLog("    Checking line for head: '" + edit_line + "'");
      if(edit_line.startsWith(expected_head))
      {
        LoreEnchantments.debugLog("    Splitting headless line '" + edit_line.replace(ChatColor.stripColor(expected_head).toLowerCase(), "") + "'");
        return edit_line.replace(ChatColor.stripColor(expected_head).toLowerCase(), "").split("\\s+");
      }
    }
    return null;
  }
  
  /**Checks to see if the item has the LoreEnchantment
   * 
   * @param item The item to check
   * @return True if it does have the LoreEnchantment, otherwise false
   */
  public final boolean hasEnchantment(ItemStack item)
  {
    List<String> lore = item.getItemMeta().getLore();
    if(lore == null) return false;
    if(lore.size() == 0) return false;
    String expected_head = this.getName().toLowerCase();
    for(String line : lore)
    {
      if(ChatColor.stripColor(line).toLowerCase().startsWith(expected_head))
      {
        return true;
      }  
    }
    return false;
  }
}
