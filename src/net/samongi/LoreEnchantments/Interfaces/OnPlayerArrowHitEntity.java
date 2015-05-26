package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface OnPlayerArrowHitEntity
{
  public void onPlayerArrowHitEntity(EntityDamageByEntityEvent event, String[] data);
}
