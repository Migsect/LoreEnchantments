package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.player.PlayerToggleSneakEvent;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

public interface OnPlayerToggleSneak
{
  public void onPlayerToggleSneak(PlayerToggleSneakEvent event, LoreEnchantment ench, String[] data);
}
