package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface OnPlayerDamageEntity
{
  public void onPlayerDamageEntity(EntityDamageByEntityEvent event, LoreEnchantment ench, String[] data);
}
