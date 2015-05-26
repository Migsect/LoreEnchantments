package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface OnEntityArrowHitEntity
{
  public void onEntityArrowHitEntity(EntityDamageByEntityEvent event, String[] data);
}
