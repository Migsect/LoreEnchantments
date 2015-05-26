package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.player.PlayerItemConsumeEvent;

public interface OnPlayerItemConsume
{
  public void onPlayerItemConsume(PlayerItemConsumeEvent event, String[] data);
}
