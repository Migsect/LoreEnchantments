package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.player.PlayerToggleSprintEvent;

public interface OnPlayerToggleSprint
{
  public void onPlayerToggleSprint(PlayerToggleSprintEvent event, LoreEnchantment ench, String[] data);
}
