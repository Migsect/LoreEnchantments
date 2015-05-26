package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.player.PlayerItemHeldEvent;

public interface OnPlayerItemHeld
{
  public void onPlayerItemHeld(PlayerItemHeldEvent event, String[] data);
}
