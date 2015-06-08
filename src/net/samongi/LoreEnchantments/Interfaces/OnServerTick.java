package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.entity.Player;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

public interface OnServerTick
{
  public void onServerTick(Player player, LoreEnchantment ench, String[] data);
}
