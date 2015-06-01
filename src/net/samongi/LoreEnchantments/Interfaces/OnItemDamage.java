package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.player.PlayerItemDamageEvent;

public interface OnItemDamage
{
  public void onItemDamage(PlayerItemDamageEvent event, LoreEnchantment ench, String[] data);
}
