package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.player.PlayerDropItemEvent;

public interface OnPlayerDrop
{
  public void onPlayerDropItem(PlayerDropItemEvent event, String[] data);
}
