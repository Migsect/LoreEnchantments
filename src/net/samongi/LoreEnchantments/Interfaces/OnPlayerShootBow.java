package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.entity.EntityShootBowEvent;

public interface OnPlayerShootBow
{
  public void onPlayerShootBow(EntityShootBowEvent event, LoreEnchantment ench, String[] data);
}
