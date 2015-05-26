package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.player.PlayerPickupItemEvent;

public interface OnPlayerPickup
{
  public void onPlayerPickupItem(PlayerPickupItemEvent event, String[] data);
}
