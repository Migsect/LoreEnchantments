package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.player.PlayerItemConsumeEvent;

public interface OnPlayerItemConsume
{
  public void onPlayerItemConsume(PlayerItemConsumeEvent event, LoreEnchantment ench, String[] data);
}
