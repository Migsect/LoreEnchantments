package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.player.PlayerInteractEvent;

public interface OnPlayerInteract
{
  public void onPlayerInteract(PlayerInteractEvent event, String[] data);
}
