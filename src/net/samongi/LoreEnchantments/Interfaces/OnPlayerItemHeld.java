package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.player.PlayerItemHeldEvent;

public interface OnPlayerItemHeld
{
  public void onPlayerItemHeld(PlayerItemHeldEvent event, LoreEnchantment ench, String[] data);
}
