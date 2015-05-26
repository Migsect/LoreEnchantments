package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.entity.EntityShootBowEvent;

public interface OnEntityShootBow
{
  public void onEntityShootBow(EntityShootBowEvent event, String[] data);
}
