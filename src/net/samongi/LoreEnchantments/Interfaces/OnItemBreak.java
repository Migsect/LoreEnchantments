package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.player.PlayerItemBreakEvent;

public interface OnItemBreak
{
  public void onItemBreak(PlayerItemBreakEvent event, LoreEnchantment ench, String[] data);
}
