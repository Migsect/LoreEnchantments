package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface OnPlayerDamagePlayer
{
  public void onPlayerDamagePlayer(EntityDamageByEntityEvent event, String[] data);
}
