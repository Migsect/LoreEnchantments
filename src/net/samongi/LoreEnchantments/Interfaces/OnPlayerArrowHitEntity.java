package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface OnPlayerArrowHitEntity
{
  public void onPlayerArrowHitEntity(EntityDamageByEntityEvent event, LoreEnchantment ench, String[] data);
}
