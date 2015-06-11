package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.entity.Arrow;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

public interface OnServerArrowTick
{
  public void onServerArrowTick(Arrow arrow, LoreEnchantment ench, String[] data);
}
