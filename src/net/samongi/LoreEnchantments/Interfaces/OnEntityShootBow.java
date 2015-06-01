package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.entity.EntityShootBowEvent;

public interface OnEntityShootBow
{
  public void onEntityShootBow(EntityShootBowEvent event, LoreEnchantment ench, String[] data);
}
