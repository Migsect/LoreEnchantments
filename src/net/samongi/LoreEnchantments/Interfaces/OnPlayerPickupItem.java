package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.player.PlayerPickupItemEvent;

public interface OnPlayerPickupItem
{
  public void onPlayerPickupItem(PlayerPickupItemEvent event, LoreEnchantment ench, String[] data);
}
