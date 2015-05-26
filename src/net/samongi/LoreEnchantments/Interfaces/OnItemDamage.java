package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.player.PlayerItemDamageEvent;

public interface OnItemDamage
{
  public void onItemDamage(PlayerItemDamageEvent event, String[] data);
}
