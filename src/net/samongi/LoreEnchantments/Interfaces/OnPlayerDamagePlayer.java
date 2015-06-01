package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface OnPlayerDamagePlayer
{
  public void onPlayerDamagePlayer(EntityDamageByEntityEvent event, LoreEnchantment ench, String[] data);
}
