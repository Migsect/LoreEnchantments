package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.player.PlayerDropItemEvent;

public interface OnPlayerDrop
{
  public void onPlayerDropItem(PlayerDropItemEvent event, LoreEnchantment ench, String[] data);
}
