package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.player.PlayerInteractEntityEvent;

public interface OnPlayerInteractEntity
{
  public void onPlayerInteractEntity(PlayerInteractEntityEvent event, LoreEnchantment ench,  String[] data);
}
