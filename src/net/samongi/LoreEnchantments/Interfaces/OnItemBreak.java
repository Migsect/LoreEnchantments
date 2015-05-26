package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.player.PlayerItemBreakEvent;

public interface OnItemBreak
{
  public void onItemBreak(PlayerItemBreakEvent event, String[] data);
}
