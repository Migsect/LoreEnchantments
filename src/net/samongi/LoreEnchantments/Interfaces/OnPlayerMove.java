package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.player.PlayerMoveEvent;

public interface OnPlayerMove
{
  public void onPlayerMove(PlayerMoveEvent event, LoreEnchantment ench, String[] data);
}
