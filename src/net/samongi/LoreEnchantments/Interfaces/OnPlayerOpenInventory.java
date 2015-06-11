package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.inventory.InventoryOpenEvent;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

public interface OnPlayerOpenInventory
{
  public void onPlayerOpenInventory(InventoryOpenEvent event, LoreEnchantment ench, String[] data);
}
