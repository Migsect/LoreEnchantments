package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.player.PlayerToggleFlightEvent;

public interface OnPlayerToggleFlight
{
  public void onPlayerToggleFlight(PlayerToggleFlightEvent event, LoreEnchantment ench, String[] data);
}
