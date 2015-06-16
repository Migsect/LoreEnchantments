package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.inventory.InventoryClickEvent;

public interface OnItemInventoryClick
{
  public void onItemInventoryClick(InventoryClickEvent event, LoreEnchantment ench, String[] data);
}
