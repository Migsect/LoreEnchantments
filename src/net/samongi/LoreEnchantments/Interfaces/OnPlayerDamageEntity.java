package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface OnPlayerDamageEntity
{
  public void onPlayerDamageEntity(EntityDamageByEntityEvent event, String[] data);
}
