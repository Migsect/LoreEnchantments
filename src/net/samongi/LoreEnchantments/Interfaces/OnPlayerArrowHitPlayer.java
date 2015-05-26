package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface OnPlayerArrowHitPlayer
{
  public void onPlayerArrowHitPlayer(EntityDamageByEntityEvent event, String[] data);
}
