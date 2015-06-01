package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.player.PlayerInteractEvent;

public interface OnPlayerInteract
{
  public void onPlayerInteract(PlayerInteractEvent event, LoreEnchantment ench, String[] data);
}
