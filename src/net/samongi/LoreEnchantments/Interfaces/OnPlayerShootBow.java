package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.entity.EntityShootBowEvent;

public interface OnPlayerShootBow
{
  public void onPlayerShootBow(EntityShootBowEvent event, String[] data);
}
