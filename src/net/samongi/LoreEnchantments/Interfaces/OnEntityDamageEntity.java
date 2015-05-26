package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface OnEntityDamageEntity
{
  public void onEntityDamageEntity(EntityDamageByEntityEvent event, String[] data);
}
