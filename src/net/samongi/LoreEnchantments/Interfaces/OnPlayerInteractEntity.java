package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.player.PlayerInteractEntityEvent;

public interface OnPlayerInteractEntity
{
  public void onPlayerInteractEntity(PlayerInteractEntityEvent event, String[] data);
}
