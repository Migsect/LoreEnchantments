package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.player.PlayerToggleSprintEvent;

public interface OnPlayerSprint
{
  public void onPlayerSneak(PlayerToggleSprintEvent event, LoreEnchantment ench, String[] data);
}
